package com.proccorp.eventory.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.proccorp.eventory.model.internal.User;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long primaryKey;

    @Column
    private String userId;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String notes;

    public User buildInternalObject() {
        return new User(userId, name, phoneNumber, notes);
    }

    public long getPrimaryKey() {
        return primaryKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void updateWith(User element) {
        userId= element.getId();
        name= element.getName();
        phoneNumber= element.getPhoneNumber();
        notes= element.getNotes();
    }
}
