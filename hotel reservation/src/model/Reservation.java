package model;
import java.util.*;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString(){
        return customer.toString() + "\n" + room.toString() + "\n" +
                 "Check In Date: " + checkInDate.toString() +
                ", Check Out Date: " + checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof Reservation)){
            return false;
        }
        Reservation o = (Reservation)other;
        return (o.customer.equals(this.customer) &&
                o.room.equals(this.room) &&
                o.checkInDate.equals(this.checkInDate) &&
                o.checkOutDate.equals(this.checkOutDate));
    }

    @Override
    public int hashCode(){
        int code = this.customer.hashCode() +
                    this.room.hashCode() +
                    this.checkInDate.hashCode() +
                    this.checkOutDate.hashCode();
        return code;
    }
}
