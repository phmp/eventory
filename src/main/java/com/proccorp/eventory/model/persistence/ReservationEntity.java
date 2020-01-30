package com.proccorp.eventory.model.persistence;

import com.proccorp.eventory.model.internal.Reservation;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long primaryKey;

    @Column
    private String reservationId;

    @Column
    private String additionalNotes;

    @Column
    private String status;

    @Column
    private String subscriptionDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    public Reservation toInternal() {
        return new Reservation(reservationId, additionalNotes, status, null, user.toInternal(), event.toInternal());
    }
}
