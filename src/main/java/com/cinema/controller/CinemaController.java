package com.cinema.controller;

import com.cinema.layout.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController<T> {
    public static final int FIRST_FOUR_FRONT_ROWS = 4;

    CinemaRoom room = new CinemaRoom(9, 9);

    @GetMapping("/seats")
    public CinemaRoom getCinema() {
        return room;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestBody CinemaSeat seat) {
        if ((seat.getRow() >= 1 && seat.getRow() <= 9) && (seat.getColumn() >= 1 && seat.getColumn() <= 9)) {
            if (room.checkIfSeatIsAvailable(seat.getRow(), seat.getColumn())) {
                return new ResponseEntity<Object>(room.getTicket(seat), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<Object>(Map.of(
                    "error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<Object>(Map.of
                ("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/return")
    public ResponseEntity<Object> refundTicket(@RequestBody Token token) {
        if (room.refundTicketAndAddBack(token)) {
            return new ResponseEntity<Object>(new ReturnedTicket(room.getReturnedTicket(token)), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(Map.of
                ("error", "Wrong token!"), HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> returnStats(@RequestParam(required = false) String password) {
        if ("super_secret".equals(password)) {
            return new ResponseEntity<Object>(room.getStats(), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
    }
}
