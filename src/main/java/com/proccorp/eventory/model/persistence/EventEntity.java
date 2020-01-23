package com.proccorp.eventory.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long primaryKey;

    @Column
    private String eventId;

    @Column
    private String schedulePrimaryKey;

    @Column
    private String zonedDateTime;
}
