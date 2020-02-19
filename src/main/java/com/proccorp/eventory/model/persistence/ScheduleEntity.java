package com.proccorp.eventory.model.persistence;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.proccorp.eventory.model.internal.Event;
import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.internal.User;

@Entity
@Table(name = "schedules")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long primaryKey;

    @Column
    private String scheduleId;

    @Column
    private int maxNumberOfPeople;

    @Column
    private String location;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "host_user_id", nullable = false)
    private UserEntity host;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<EventEntity> events = new ArrayList<>();

    public ScheduleEntity() {
    }

    public Schedule toInternal() {
        List<Event> events = this.events.stream()
                .map(EventEntity::toInternal)
                .collect(Collectors.toList());
        return new Schedule(scheduleId, events, maxNumberOfPeople, host.toInternal(), location, description);
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public long getPrimaryKey() {
        return primaryKey;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void updateWith(Schedule element) {
        scheduleId = element.getId();
        description = element.getDescription();
        location = element.getLocation();
        maxNumberOfPeople = element.getMaxNumberOfPeople();
    }

    public UserEntity getHost() {
        return host;
    }

    public List<EventEntity> getEvents() {
        return events;
    }
    public void setHost(UserEntity host) {
        this.host = host;
    }
}
