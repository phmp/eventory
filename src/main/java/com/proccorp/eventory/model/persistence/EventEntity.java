package com.proccorp.eventory.model.persistence;

import com.proccorp.eventory.model.internal.Event;
import com.proccorp.eventory.model.internal.Reservation;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long primaryKey;

    @Column
    private String eventId;

    @Column
    private String zonedDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private ScheduleEntity schedule;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReservationEntity> reservations;

    public Event toInternal() {
        List<Reservation> reservations = this.reservations.stream().map(ReservationEntity::toInternal).collect(Collectors.toList());
        return new Event(eventId, schedule.toInternal(), null, reservations);
    }
}
