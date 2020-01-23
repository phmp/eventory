package com.proccorp.eventory.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private boolean confirmedByHost;

    @Column
    private String subscriptionDateTime;

    @Column
    private String userPrimaryKey;

}
