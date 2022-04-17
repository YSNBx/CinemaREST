package com.cinema.layout;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CinemaRoom {
    private int total_rows;
    private int total_columns;
    private List<CinemaSeat> available_seats;
    private List<TokenSeat> boughtTickets;
    private Stats stats;

    public CinemaRoom(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new CopyOnWriteArrayList<>();
        this.boughtTickets = new CopyOnWriteArrayList<>();
        fillCinema();

        this.stats = new Stats(0, this.available_seats.size(), this.boughtTickets.size());
    }

    public void fillCinema() {
        for (int i = 1; i <= this.total_rows; i++) {
            for (int j = 1; j <= this.total_columns; j++) {
                this.available_seats.add(new CinemaSeat(i, j));
            }
        }
    }

    public int getTotal_rows() {
        return this.total_rows;
    }

    public void setTotalRows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return this.total_columns;
    }

    public void setTotalColumns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<CinemaSeat> getAvailable_seats() {
        return this.available_seats;
    }

    @JsonIgnore
    public Stats getStats() {
        return this.stats;
    }

    public void buyTicketAndRemoveFromList(int row, int column) {
        for (CinemaSeat c : this.available_seats) {
            if (c.getRow() == row && c.getColumn() == column) {
                this.boughtTickets.add(new TokenSeat(new Token(), c));
                this.available_seats.remove(c);

                this.stats.setCurrentIncome(this.stats.getCurrent_income() + c.getPrice());
                this.stats.setNumberOfAvailableSeats(this.stats.getNumber_of_available_seats() - 1);
                this.stats.setNumberOfPurchasedTickets(this.stats.getNumber_of_purchased_tickets() + 1);
            }
        }
    }

    public boolean refundTicketAndAddBack(Token token) {
        for (TokenSeat t : this.boughtTickets) {
            if (t.getToken().equals(token.getToken())) {
                this.stats.setCurrentIncome(this.stats.getCurrent_income() - t.getTicket().getPrice());
                this.stats.setNumberOfAvailableSeats(this.stats.getNumber_of_available_seats() + 1);
                this.stats.setNumberOfPurchasedTickets(this.stats.getNumber_of_purchased_tickets() - 1);
                return true;
            }
        }
        return false;
    }

    public boolean checkIfSeatIsAvailable(int row, int column) {
        for (CinemaSeat c : this.available_seats) {
            if (c.getRow() == row && c.getColumn() == column) {
                buyTicketAndRemoveFromList(row, column);
                return true;
            }
        }
        return false;
    }

    public TokenSeat getTicket(CinemaSeat seat) {
        for (TokenSeat t : this.boughtTickets) {
            if (t.getTicket().getRow() == seat.getRow() && t.getTicket().getColumn() == seat.getColumn()) {
                return t;
            }
        }
        return null;
    }

    public CinemaSeat getReturnedTicket(Token token) {
        for (TokenSeat t : this.boughtTickets) {
            if (t.getToken().equals(token.getToken())) {
                this.available_seats.add(t.getTicket());
                CinemaSeat tmp = t.getTicket();
                this.boughtTickets.remove(t);
                return tmp;
            }
        }
        return null;
    }
}
