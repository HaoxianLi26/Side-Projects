package api;

import model.Customer;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {
    private static AdminMenu AM;
    private HotelResource HR = HotelResource.getInstance();
    private MainMenu MM = new MainMenu();
    private AdminResource AR = AdminResource.getInstance();
    private Scanner scan = new Scanner(System.in);
    private SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

    /**
     * Provide a static reference
     * @return main menu
     */

    public static AdminMenu getInstance(){
        if(AM == null){
            AM = new AdminMenu();
        }
        return AM;
    }

    public void start(){
        System.out.println("\n   Admin Menu");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("Enter 1-5 to next step: ");
        String stringAdminOption = scan.nextLine();
        int intAdminOption = mainInputChecker(stringAdminOption);
        adminMenuChoice(intAdminOption);

    }

    private int mainInputChecker(String option){
        boolean found = false;
        int number = 0;
        while(! found) {
            if(option.equals("1") || option.equals("2") ||option.equals("3")
                    || option.equals("4") || option.equals("5")){
                number = Integer.parseInt(option);
                found = true;
            } else{
                System.out.println("Invalid Input, Please Enter 1-5 to the next step: ");
                option = scan.nextLine();
            }
        }
        return number;
    }

    public int subMenuOption(String option){
        boolean found = false;
        int number = 0;
        while(! found) {
            if(option.equals("1") || option.equals("2")){
                number = Integer.parseInt(option);
                found = true;
            } else{
                System.out.println("Invalid Input, Please Enter 1-2 to the next step: ");
                option = scan.nextLine();
            }
        }
        return number;
    }

    public void adminMenuChoice(int option){
        switch(option){
            case 1: adminOption1();
                break;
            case 2: adminOption2();
                break;
            case 3: adminOption3();
                break;
            case 4: adminOption4();
                break;
            case 5: adminOption5();
                break;
        }
    }

    public void adminOption1(){
        Collection<model.Customer> list = AR.getAllCustomers();
        if(list.size() == 0){
            System.out.println("\nThe hotel do not have any reservation.");
            System.out.println("Now return to Admin Menu");
            start();
        }
        Object[] array = list.toArray();
        for(int i = 0; i < array.length; i++){
            System.out.println("\nReservation " +(i+1) +": "+ (model.Customer)array[i]);
        }
        System.out.println("\nNow return to the Main menu\n");
        start();
    }

    public void adminOption2(){
        Collection<model.IRoom> list = AR.getAllRooms();
        Object[] array = list.toArray();
        if(list.size() == 0){
            System.out.println("\n The hotel do not have any room.");
            System.out.println("Now return to Admin Menu");
            start();
        }
        for(int i = 0; i < array.length; i++){
            System.out.println("Room " +(i+1) +": "+ (model.IRoom)array[i]);
        }
        System.out.println("\nNow return to the Main menu\n");
        start();
    }

    public void adminOption3(){
        Collection<model.Reservation> list = AR.getAllReservation();
        Object[] array = list.toArray();
        if(list.size() == 0){
            System.out.println("\n The hotel do not have any reservation.");
            System.out.println("Now return to Admin Menu");
            start();
        }
        for(int i = 0; i < array.length; i++){
            System.out.println("\nReservation " +(i+1) +": "+ (model.Reservation)array[i]);
        }
        System.out.println("\nNow return to the Main menu\n");
        start();
    }

    public void adminOption4(){
        String roomNumber = null;
        String stringPrice = null;
        Double price = null;
        String roomtype = null;
        model.RoomType type = null;
        boolean priceValid = false;
        System.out.println("\nPlease provide the room number: ");
        roomNumber = scan.nextLine();
        System.out.println("Please provide the price: ");
        stringPrice = scan.nextLine();
        while(!priceValid){
            try{
                price = Double.parseDouble(stringPrice);
            } catch(NumberFormatException e){
                price = null;
                System.out.println("Incorrect format, Please enter a number: ");
                stringPrice = scan.nextLine();
            }
            if(price != null){
                priceValid = true;
            }
        }
        System.out.println("Please enter 1 or 2 to choose the room type");
        System.out.println("1: Single    2: Double");
        roomtype = scan.nextLine();
        int checkType = subMenuOption(roomtype);
        while(checkType == 0){
            System.out.println("Invalid input. Please enter 1 or 2: ");
            roomtype = scan.nextLine();
            checkType = subMenuOption(roomtype);
        }
        if(checkType == 1){
            type = model.RoomType.SINGLE;
        }else{
            type = model.RoomType.DOUBLE;
        }

        model.IRoom newRoom = new model.Room(roomNumber, price, type);
        AR.addOneRoom(newRoom);
        System.out.println("Press 1 to add more room, Press 2 to Admin menu: ");
        String stringExit = scan.nextLine();
        int exit = subMenuOption(stringExit);
        while(exit == 0){
            System.out.println("Invalid input. Please enter 1 or 2: ");
            stringExit = scan.nextLine();
            exit = subMenuOption(stringExit);
        }
        if(exit == 1){
            adminOption4();
        } else{
            start();
        }

    }

    public void adminOption5(){
        MainMenu.start();
    }

}
