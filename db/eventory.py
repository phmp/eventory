from datetime import datetime, timedelta

from sqlalchemy import DateTime, String, Integer
from sqlalchemy import Column, ForeignKey
from sqlalchemy.orm import relationship, backref
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy_utils.types import UUIDType, JSONType

Model = declarative_base()


class Player(Model):
    __tablename__ = 'players'
    id = Column(Integer, primary_key=True)
    email = Column(String, unique=True)
    nick = Column(String, unique=True)

    first_name = Column(String, nullable=False)
    last_name = Column(String, nullable=False)

    registrations = relationship("Registration")


class Registration(Model):
    __tablename__ = 'registrations'
    uuid = Column(UUIDType, unique=True)
    player_id = Column(Integer, ForeignKey('players.id'), primary_key=True)
    event_id = Column(Integer, ForeignKey('events.id'), primary_key=True)


class Event(Model):
    __tablename__ = 'events'
    id = Column(Integer, primary_key=True)
    schedule_id = Column(Integer, ForeignKey('schedules.id'), primary_key=True, nullable=True)

    start = Column(DateTime, nullable=False)


class Schedule(Model):
    __tablename__ = 'schedules'
    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True, nullable=False)


if __name__ == '__main__':
    import random
    import names

    from sqlalchemy import create_engine
    from sqlalchemy.orm import sessionmaker

    from sqlalchemy_schemadisplay import create_schema_graph

    engine = create_engine('sqlite:///eventory.sqlite', echo=True)
    Model.metadata.create_all(engine)

    USERS = 10
    EVENTS = 5

    now = datetime.now()

    session = sessionmaker()(bind=engine)

    for player_id in range(USERS):
        first_name, last_name = names.get_first_name(), names.get_last_name()
        session.add(Player(
            id=player_id+1,
            nick=(first_name[0] + last_name).lower(),
            email=f'{first_name.lower()}.{last_name.lower()}@gmail.com',
            first_name=first_name,
            last_name=last_name,
        ))

    for event_id in range(EVENTS):
        session.add(Event(
            id=event_id+1,
            start=datetime(now.year, now.month, now.day, random.choice([15, 16, 16]), 0) + timedelta(days=random.randint(1, 21))
        ))

    session.commit()

    graph = create_schema_graph(
        metadata=Model.metadata,
        show_datatypes=True,
        show_indexes=False,
        rankdir='LR',
        concentrate=False
    ).write_png('eventory.png')
