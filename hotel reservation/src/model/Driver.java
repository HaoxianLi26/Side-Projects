package model;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
       // List<model.IRoom> roomList = new ArrayList<>();
        //roomList.add(new model.Room("room1", 999.0, model.RoomType.SINGLE));
       //roomList.add(new model.Room("room2", 999.0, model.RoomType.SINGLE));
       // roomList.add(new model.Room("room3", 999.0, model.RoomType.SINGLE));
        //roomList.add(new model.Room("room4", 999.0, model.RoomType.SINGLE));
        //roomList.add(new model.Room("room1", 999.0, model.RoomType.SINGLE));
        //api.AdminResource a = api.AdminResource.getInstance();
        //a.addRoom(roomList);


        api.MainMenu MM = new api.MainMenu();
        MM.start();

        /*
        Date day1 = new Date();
        Date day2 = new Date();
        List<model.IRoom> roomList = new ArrayList<>();
        roomList.add(new model.Room("room1", 999.0, model.RoomType.SINGLE));
        roomList.add(new model.Room("room2", 999.0, model.RoomType.SINGLE));
        roomList.add(new model.Room("room3", 999.0, model.RoomType.SINGLE));
        roomList.add(new model.Room("room4", 999.0, model.RoomType.SINGLE));
        roomList.add(new model.Room("room1", 999.0, model.RoomType.SINGLE));



        api.HotelResource HR = api.HotelResource.getInstance();
        api.AdminResource AR = api.AdminResource.getInstance();

        AR.addRoom(roomList);
        HR.createACustomer("123@gmail.com", "Haoxian", "Li");
        HR.createACustomer("456@gmail.com", "Xiaojing", "Su");

        HR.bookARoom("123@gmail.com", roomList.get(1), day1, day2);
        HR.bookARoom("123@gmail.com", roomList.get(2), day1, day2);

        AR.displayAllReservations();

         */









        /*
        service.ReservationService a = service.ReservationService.getInstance();
        service.ReservationService b = service.ReservationService.getInstance();

        double price = 998;
        a.addRoom(new model.Room("123", 999.0, model.RoomType.SINGLE));
        a.addRoom(new model.Room("12", price, model.RoomType.SINGLE));

        model.Customer haoxian = new model.Customer("haoxian", "Li", "123@gmail.com");
        model.Customer xiaojing = new model.Customer("haoxian", "Li", "456@gmail.com");

        Date day1 = new Date();
        Date day2 = new Date();

        a.reserveARoom(haoxian, a.getARoom("123"), day1, day2) ;
        a.reserveARoom(haoxian, a.getARoom("12"), day1, day2) ;

         */

    }
}
