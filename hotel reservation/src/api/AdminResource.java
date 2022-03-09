package api;
import java.util.*;

public class AdminResource {
    private static AdminResource AR;
    private static final service.CustomerService CS =
            service.CustomerService.getInstance();
    private static final service.ReservationService RS =
            service.ReservationService.getInstance();

    /**
     * Provide a static reference
     * @return Admin resource
     */
    public static AdminResource getInstance(){
        if(AR == null){
            AR = new AdminResource();
        }
        return AR;
    }

    /**
     * Call customer service to get a customer
     * @param email customer's email
     * @return customer
     */
    public model.Customer getCustomer(String email){
        return CS.getCustomer(email);
    }

    /**
     * To add a list of rooms
     * @param rooms rooms
     */
    public void addRoom(List<model.IRoom> rooms){
        for(model.IRoom room: rooms){
            if(! RS.addRoom(room)){
                System.out.println("Room Number: (" + room.getRoomNumber()
                + ") already exists and cannot be added");
            } else{
                System.out.println("Room Number: (" + room.getRoomNumber()
                + ") add successfully");
            }
        }
    }

    /**
     * Call reservation service to add a room
     * @param room room
     * @return return true if the room is added
     */
    public boolean addOneRoom(model.IRoom room){
        if(! RS.addRoom(room)){
            System.out.println("Room Number: (" + room.getRoomNumber()
                    + ") already exists and cannot be added");
            return false;
        }
        System.out.println("Room Number: (" + room.getRoomNumber()
                    + ") add successfully");
        return true;
    }

    /**
     * Call reservation service to get a list of all rooms
     * @return a list of all rooms
     */
    public Collection<model.IRoom> getAllRooms(){
        return RS.getRoomList();
    }

    /**
     * Call customer service to get a list of all customers
     * @return a list of all customers
     */
    public Collection<model.Customer> getAllCustomers(){
        return CS.getAllCustomers();
    }

    /**
     * print out all reservations
     */
    public void displayAllReservations(){
        RS.printAllReservation();
    }

    public Collection<model.Reservation> getAllReservation(){
        Collection<model.Customer> cList= CS.getAllCustomers();
        Collection<model.Reservation> result = new ArrayList<>();
        Object[] customerList = cList.toArray();
        for(int i = 0; i < customerList.length; i++){
            model.Customer temp = (model.Customer)customerList[i];
            Object[] tempReser = RS.getCustomersReservations(temp).toArray();
            for(int k = 0; k < tempReser.length; k++){
                result.add((model.Reservation)tempReser[k]);
            }
        }
        return result;
    }



}
