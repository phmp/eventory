package com.proccorp.eventory.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proccorp.eventory.service.reservation.BookingExecutor;
import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Reservation;
import com.proccorp.eventory.model.Schedule;
import com.proccorp.eventory.model.User;
import com.proccorp.eventory.storage.SchedulesRepository;

@Service
public class ReservationService {

    private final BookingExecutor bookingExecutor;
    private final SchedulesRepository schedulesRepository;

    @Autowired
    public ReservationService(BookingExecutor bookingExecutor, SchedulesRepository schedulesRepository) {
        this.bookingExecutor = bookingExecutor;
        this.schedulesRepository = schedulesRepository;
    }

    public Reservation addPerson(String scheduleId, String eventId, User user) {
        Schedule schedule = schedulesRepository.get(scheduleId);
        Event event = schedule.getEvent(eventId);
        return bookingExecutor.book(event, user);
    }

}
