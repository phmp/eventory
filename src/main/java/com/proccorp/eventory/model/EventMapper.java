package com.proccorp.eventory.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.api.events.EventView;
import com.proccorp.eventory.model.api.reservation.ReservationView;
import com.proccorp.eventory.model.api.users.UserView;
import com.proccorp.eventory.model.internal.Event;

@Component
public class EventMapper {
    private final UserMapper userMapper;

    public EventMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public EventView toView(Event event){
        List<ReservationView> reservationViewList = event.getReservations().stream()
                .map(reservation -> {
                    UserView userView = userMapper.toView(reservation.getPerson());
                    return new ReservationView(reservation.getId(),
                            reservation.getStatus(),
                            userView,
                            reservation.getEvent().getId());
                }).collect(Collectors.toList());
        return new EventView(event.getId(), event.getZonedDateTime().toString(), reservationViewList);
    }
}
