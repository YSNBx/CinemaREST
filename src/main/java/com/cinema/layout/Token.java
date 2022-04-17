package com.cinema.layout;

import java.util.UUID;

public class Token {
    private UUID token;

    public Token() {
        this.token = UUID.randomUUID();
    }

    public void setToken() {
        this.token = UUID.randomUUID();
    }

    public UUID getToken() {
        return this.token;
    }
}
