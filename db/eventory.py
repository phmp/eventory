from datetime import datetime, timedelta

from sqlalchemy import DateTime, String, Integer
from sqlalchemy import Column, ForeignKey
from sqlalchemy.orm import relationship, backref
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy_utils.types import UUIDType, JSONType

Model = declarative_base()

CHECK = "\N{HEAVY CHECK MARK}"


# TODO: player profile (notifications, preferences (game types, schedules, level), achievements)
class Player(Model):
    __tablename__ = 'players'

    id = Column(Integer, primary_key=True)
    email = Column(String, unique=True)
    nick = Column(String, unique=True)

    first_name = Column(String, nullable=False)
    last_name = Column(String, nullable=False)

    registrations = relationship("Registration", back_populates="player")

    def __str__(self):
        return f'{self.first_name} {self.last_name} ({self.nick})'


# TODO: cancellation, notes, maybe
class Registration(Model):
    __tablename__ = 'registrations'

    player_id = Column(Integer, ForeignKey('players.id'), primary_key=True)
    event_id = Column(Integer, ForeignKey('events.id'), primary_key=True)

    created = Column(DateTime, nullable=False, default=datetime.now())
    accepted = Column(DateTime, nullable=True)

    event = relationship("Event", back_populates="registrations")
    player = relationship("Player", back_populates="registrations")


# TODO: details, place (address, capacity, availability, cost), game (type, results), threshold
class Event(Model):
    __tablename__ = 'events'

    id = Column(Integer, primary_key=True)

    start = Column(DateTime, nullable=False)
    place = Column(String, nullable=False)
    limit = Column(Integer, nullable=True)

    registrations = relationship("Registration", back_populates="event", order_by=lambda: Registration.created)

    def __str__(self):
        return f'{self.place} @ {self.start:%Y.%m.%d %H:%M}'


if __name__ == '__main__':
    import random
    import names

    from sqlalchemy import create_engine
    from sqlalchemy.orm import sessionmaker

    from sqlalchemy_schemadisplay import create_schema_graph

    engine = create_engine('sqlite:///eventory.sqlite', echo=False)
    Model.metadata.create_all(engine)
    session = sessionmaker()(bind=engine)

    now = datetime.now()

    # create players

    for _ in range(1, random.randint(30, 50)+1):
        first_name, last_name = names.get_first_name(), names.get_last_name()
        provider = random.choice(['gmail.com', 'o2.pl', 'yahoo.com', 'protonmail.com'])
        session.add(Player(
            nick=(first_name[0] + last_name).lower(),
            email=f'{first_name.lower()}.{last_name.lower()}@{provider}',
            first_name=first_name,
            last_name=last_name,
        ))

    session.commit()

    PLAYERS = [*session.query(Player).order_by(Player.last_name).all()]

    # create events

    for _ in range(1, random.randint(5, 15)+1):
        session.add(Event(
            start=datetime(
                now.year, now.month, now.day,
                random.choice([8, 15, 19]), 0
            ) + timedelta(days=random.randint(1, 60)),
            place=random.choice(['orlik', 'boisko', 'stadion', 'hala']),
            limit=random.choice([None, 10, 22])
        ))

    session.commit()

    EVENTS = [*session.query(Event).filter(Event.start > now).order_by(Event.start).all()]

    # register players to events

    for event in EVENTS:
        for player in random.sample(
                PLAYERS,
                random.randint(*(
                    (int(0.5*event.limit), min(int(1.5*event.limit), len(PLAYERS))) if event.limit is not None
                    else (5, min(30, len(PLAYERS)))
                ))
        ):
            created = datetime(
                now.year, now.month, now.day,
            ) - timedelta(days=random.randint(1, 5))
            event.registrations.append(
                Registration(
                    player=player,
                    created=created,
                    accepted=None if random.random() < 0.2 else created + timedelta(
                        seconds=random.randint(600, int((now-created).total_seconds()))
                    )
                )
            )

    session.commit()

    for event in session.query(Event).filter(Event.start > now).order_by(Event.start).all():
        print(f'\n\N{VOLLEYBALL} {event}')
        for n, registration in enumerate(event.registrations):
            if n == event.limit:
                print(' '*3+'\N{BOX DRAWINGS LIGHT HORIZONTAL}'*30)
            print(f'   {n+1:2d} {CHECK if registration.accepted else " "}  {registration.player}')

    graph = create_schema_graph(
        metadata=Model.metadata,
        show_datatypes=True,
        show_indexes=False,
        rankdir='LR',
        concentrate=False
    ).write_png('eventory.png')
