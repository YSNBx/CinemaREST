package com.cinema.layout;

public class ReturnedTicket {
    private CinemaSeat seat;

    public ReturnedTicket() {}

    public ReturnedTicket(CinemaSeat seat) {
        this.seat = seat;
    }

    public void setSeat(CinemaSeat seat) {
        this.seat = seat;
    }

    public CinemaSeat getReturned_ticket() {
        return this.seat;
    }
}
