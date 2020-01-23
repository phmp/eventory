package com.proccorp.eventory.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proccorp.eventory.service.reservation.BookingExecutor;
import com.proccorp.eventory.model.internal.Event;
import com.proccorp.eventory.model.internal.Reservation;
import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.internal.User;
import com.proccorp.eventory.storage.schedule.SchedulesRepository;

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
