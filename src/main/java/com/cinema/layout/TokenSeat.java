package com.cinema.layout;

import java.util.UUID;

public class TokenSeat {
    private UUID token;
    private CinemaSeat seat;

    public TokenSeat() {}

    public TokenSeat(Token token, CinemaSeat seat) {
        this.token = token.getToken();
        this.seat = seat;
    }

    public void setToken(Token token) {
        this.token = token.getToken();
    }

    public UUID getToken() {
        return this.token;
    }

    public CinemaSeat getTicket() {
        return this.seat;
    }

    public void setTicket(CinemaSeat seat) {
        this.seat = seat;
    }
}
