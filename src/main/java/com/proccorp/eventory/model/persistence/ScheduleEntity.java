package com.proccorp.eventory.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.List;

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
    private String hostPrimaryKey;

    @Column
    private String location;

    @Column
    private String description;

    public Schedule buildInternalObject() {
        User host = new User("", hostPrimaryKey, "", "");
        List<Event> events = null;
        return new Schedule(scheduleId, events, maxNumberOfPeople, host, location, description);
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

    public String getHostPrimaryKey() {
        return hostPrimaryKey;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void updateWith(Schedule element) {
        description = element.getDescription();
        location = element.getLocation();
        maxNumberOfPeople = element.getMaxNumberOfPeople();
    }
}
