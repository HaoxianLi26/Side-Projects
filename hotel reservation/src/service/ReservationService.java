package service;
import model.IRoom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;

public class ReservationService{
    private static ReservationService RS;
    private Collection<model.IRoom> roomList = new ArrayList<>();
    private Collection<model.IRoom> leftRoomList = new ArrayList<>();
    private Map<String, ArrayList<model.Reservation>> reservationMap= new HashMap<>();
    private static final DateTimeFormatter dateFormat = new DateTimeFormatterBuilder()
            .parseStrict().appendPattern("MM-dd-uuuu").toFormatter().withResolverStyle(ResolverStyle.STRICT);

    /**
     * Provide a static reference
     * @return reservationService
     */
    public static ReservationService getInstance(){
        if(RS == null){
            RS = new ReservationService();
        }
        return  RS;
    }

    /**
     * check the room number exist or not, add the room to the room list
     * if it does not exist. Also add it to left room list
     * @param room new room
     * @return return true if the room is added
     */
    public boolean addRoom(model.IRoom room){
        for(model.IRoom r: roomList){
            if(room.getRoomNumber().equals(r.getRoomNumber())){
                return false;
            }
        }
        roomList.add(room);
        leftRoomList.add(room);
        return true;
    }

    /**
     * Given a room ID, return the room that is matched with the room ID,
     * return null if the room does not exist.
     * @param roomId room ID
     * @return return IRoom if it exists.
     */
    public model.IRoom getARoom(String roomId){
        for(model.IRoom r: roomList){
            if(roomId.equals(r.getRoomNumber())){
                return r;
            }
        }
        return null;
    }

    /**
     * Put the room to reservation room list
     * @param customer customer
     * @param room room to reserve
     * @param checkInDate check in date
     * @param checkOutDate check out date
     * @return reservation
     */
    public model.Reservation reserveARoom(model.Customer customer, model.IRoom room,
                                          Date checkInDate, Date checkOutDate){
        model.Reservation newReservation = new model.Reservation(customer, room,
                checkInDate, checkOutDate);
        if(! reservationMap.containsKey(customer.getEmail())) {
            reservationMap.put(customer.getEmail(), new ArrayList<model.Reservation>());
        }
        reservationMap.get(customer.getEmail()).add(newReservation);
        int size =  reservationMap.get(customer.getEmail()).size();
        leftRoomList.remove(room);
        return  reservationMap.get(customer.getEmail()).get(size - 1);
    }

    /**
     * return all left rooms, if any reserved room do not match the date, also return it
     * @param checkInDate check in date
     * @param checkOutDate check out date
     * @return a room list
     */
    public Collection<model.IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<model.IRoom> result = new ArrayList<>();
        for(model.IRoom room: leftRoomList){
            result.add(room);
        }
        for(ArrayList<model.Reservation> list: reservationMap.values()){
            for(model.Reservation r: list){
                if(checkOutDate.compareTo(r.getCheckInDate()) < 0 ||
                        checkInDate.compareTo(r.getCheckOutDate()) > 0){
                    result.add(r.getRoom());
                }
            }
        }
        return result;
    }

    /**
     * return all reservation of this customer
     * @param customer customer
     * @return a reservation list
     */
    public Collection<model.Reservation> getCustomersReservations(model.Customer customer){
        return reservationMap.get(customer.getEmail());
    }

    public void printAllReservation(){
        int count = 1;
        for(ArrayList<model.Reservation> list: reservationMap.values()){
            for(model.Reservation r: list){
                System.out.println("Reservation: " + count);
                count++;
                System.out.println(r + "\n");
            }
        }
    }

    public boolean checkDateValidation(String date){
        try{
            LocalDate.parse(date, dateFormat);
        } catch(DateTimeParseException e){
            return false;
        }
        return true;
    }

    public Collection<IRoom> getRoomList() {
        return roomList;
    }


    // default method to call testPrivate()
    Collection<IRoom> getLeftRoomList() {
       return testPrivate();
    }

    //private method was called by getLeftRoomList()
    private Collection<IRoom> testPrivate(){
        return leftRoomList;
    }


}
