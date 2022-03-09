package api;
import java.util.*;

public class HotelResource {
    private static HotelResource HR;
    private static final service.CustomerService CS =
            service.CustomerService.getInstance();
    private static final service.ReservationService RS =
            service.ReservationService.getInstance();

    /**
     * Provide a static reference
     * @return hotel resource
     */
    public static HotelResource getInstance(){
        if(HR == null){
            HR = new HotelResource();
        }
        return HR;
    }

    /**
     * Call reservations service to get customer
     * @param email customer's email
     * @return customer
     */
    public model.Customer getCustomer(String email){
       return CS.getCustomer(email);
    }

    /**
     * Call customer service to create a customer
     * @param email customer's email
     * @param firstName customer' first name
     * @param lastNeme customer' last name
     * @return return true if create successfully
     */
    public boolean createACustomer(String email, String firstName, String lastNeme){
        if(! CS.addCustomer(email, firstName, lastNeme)){
            return false;
        }
        return true;
    }

    /**
     * Call reservation service to get the room
     * @param roomNumber room's number
     * @return room
     */
    public model.IRoom getRoom(String roomNumber){
        return RS.getARoom(roomNumber);
    }

    /**
     * Call customer service to check the customer exist or not. Call reservation
     * service to reserve a room
     * @param customerEmail customer's email
     * @param room room that want to book
     * @param checkInDate check in date
     * @param checkOutDate check out date
     * @return the reservation
     */
    public model.Reservation bookARoom(String customerEmail, model.IRoom room,
                                       Date checkInDate, Date checkOutDate){
        if(CS.getCustomer(customerEmail) == null){
            return null;
        }
        model.Customer customer = CS.getCustomer(customerEmail);
        return RS.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    /**
     * Call customer service to get all reservations of this customer
     * @param customerEmail customer's email
     * @return a list of reservations
     */
    public Collection<model.Reservation> getCustomersReservations(String customerEmail){
        if(CS.getCustomer(customerEmail) == null){
            return null;
        }
        model.Customer customer = CS.getCustomer(customerEmail);
        return RS.getCustomersReservations(customer);
    }

    /**
     * Call reservation service to get the list of all available room
     * @param checkIn check in day
     * @param checkOut check out day
     * @return a list of all available room
     */
    public Collection<model.IRoom> findARoom(Date checkIn, Date checkOut){
        return RS.findRooms(checkIn, checkOut);
    }

    public boolean dateValidation(String date){
        return RS.checkDateValidation(date);
    }

}
