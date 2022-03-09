package api;
import com.sun.tools.javac.Main;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class MainMenu {
    private static MainMenu MM;
    private static HotelResource HR = HotelResource.getInstance();
    private  static AdminMenu AM = AdminMenu.getInstance();
    private  static AdminResource AR = AdminResource.getInstance();
    private static Scanner scan = new Scanner(System.in);
    private static SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");


    /**
     * Provide a static reference
     * @return main menu
     */


    public static MainMenu getInstance(){
        if(MM == null){
            MM = new MainMenu();
        }
        return MM;
    }


    /**
     * display the menu
     */
    public static void start() {
        System.out.println("Welcome to Montgomery Hotel");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("Enter 1-5 to the next step: ");
        String stringMainOption = scan.nextLine();
        int intMainOption = mainInputChecker(stringMainOption);
        mainChoice(intMainOption);
    }

    /**
     * check the main menu option is correct or not
     * @param option option
     * @return number 1-5
     */
    private static int mainInputChecker(String option){
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

    /**
     * TO check the option is valid or not
     * @param option option
     */
    private static void mainChoice(int option) {
        switch(option){
            case 1: mainOption1();
                break;
            case 2: mainOption2();
                break;
            case 3: mainOption3();
                break;
            case 4: mainOption4();
                break;
            case 5: mainOption5();
                break;
        }
    }

    /**
     * To check the option is 1 or 2 or invalid
     * @param option option
     * @return 1 or 2
     */
    public static int subMenuOption(String option){
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

    /**
     * To get the number of the choice
     * @param choice choice
     * @param count maximum number
     * @return choice
     */
    public static int parseChoice(String choice, int count){
        boolean success = false;
        int result = -1;
        while(!success){
            try{
                result = Integer.parseInt(choice);
                if(result < 0 || result > count-1){
                    throw new NumberFormatException();
                }
            } catch(NumberFormatException e){
                result = -1;
                System.out.println("Invalid input!!!\nEnter the option number of the " +
                        "room (not the room number) to reserve:\nEnter 0 to back to Main menu: ");
                choice = scan.nextLine();
            }
            if(result > -1 && result < count){
                success = true;
            }
        }
        return result;
    }

    /**
     * To run the option 1 on the menu
     */
    public static void mainOption1(){
        String checkInDate = null;
        String checkOutDate = null;
        boolean success = false;
        Date in = null;
        Date out = null;
        int count = 1;

        System.out.println("\nPlease provide the check in date (mm-dd-yyyy): ");
        checkInDate = scan.nextLine();
        System.out.println("Please provide the check out date (mm-dd-yyyy): ");
        checkOutDate = scan.nextLine();
        do {
            if(HR.dateValidation(checkInDate) && HR.dateValidation(checkOutDate)){
                try {
                    in = format.parse(checkInDate);
                    out = format.parse(checkOutDate);
                    if(in.compareTo(out) >-1){
                        System.out.println("The check in date or check out date format is incorrect");
                        System.out.println("Please try again");
                        System.out.println("\nPlease provide the check in date (mm-dd-yyyy): ");
                        checkInDate = scan.nextLine();
                        System.out.println("Please provide the check out date (mm-dd-yyyy): ");
                        checkOutDate = scan.nextLine();
                    }else{
                        success = true;
                    }
                } catch(ParseException e){
                }
            } else{
                System.out.println("The check in date or check out date format is incorrect");
                System.out.println("Please try again");
                System.out.println("\nPlease provide the check in date (mm-dd-yyyy): ");
                checkInDate = scan.nextLine();
                System.out.println("Please provide the check out date (mm-dd-yyyy): ");
                checkOutDate = scan.nextLine();
            }
        } while(!success);

        Collection<model.IRoom> roomlist = HR.findARoom(in, out);
        if(roomlist.size() > 0) {
            System.out.println("\nThere are " + roomlist.size() + " rooms are found");
            for (model.IRoom room : roomlist) {
                System.out.println(count + ". " + room.toString());
                count++;
            }
            System.out.println("Enter the option number of the room (not the room number) to reserve: ");
            System.out.println("Enter 0 to back to the menu");
            String input = scan.nextLine();
            int option = parseChoice(input, count);
            if(option != 0){
                System.out.println("Please provide your email: ");
                String emailInput = scan.nextLine();
                while(AR.getCustomer(emailInput) == null){
                    System.out.println("Incorrect Email, Please enter your email again: ");
                    emailInput = scan.nextLine();
                }
                model.IRoom r = (model.IRoom)roomlist.toArray()[count-2];
                HR.bookARoom(emailInput, r, in, out);
                System.out.println("Successfully reserve a room. The reservation show below");
                System.out.println(r);
                System.out.println(HR.getCustomer(emailInput));
                System.out.println("\nNow return to the Main menu\n");
                start();

            } else{
                start();
            }


        } else{
            Date recommendIn = new Date(in.getTime() + Duration.ofDays(7).toMillis());
            Date recommendOut = new Date(out.getTime() + Duration.ofDays(7).toMillis());
            System.out.println("There is no room is matched");
            System.out.println("Here are some recommend rooms for " + recommendIn.toString() + " to "
            + recommendOut.toString());
            Collection<model.IRoom> rList = HR.findARoom(recommendIn, recommendOut);
            if(rList.size() == 0){
                System.out.println("\nSorry. There are no any rooms for " + recommendIn.toString() + " to "
                        + recommendOut.toString());
                System.out.println("Now return to the Main menu");
                start();
            }
            for (model.IRoom room : rList) {
                System.out.println(count + ". " + room.toString());
                count++;
            }
            System.out.println("Enter the option number of the room (not the room number) to reserve: ");
            System.out.println("Enter 0 to back to the menu");
            String input = scan.nextLine();
            int option = parseChoice(input, count);
            if(option != 0) {
                System.out.println("Please provide your email: ");
                String emailInput = scan.nextLine();
                while (AR.getCustomer(emailInput) == null) {
                    System.out.println("Incorrect Email, Please enter your email again: ");
                    emailInput = scan.nextLine();
                }
                model.IRoom r = (model.IRoom) rList.toArray()[count - 2];
                HR.bookARoom(emailInput, r, in, out);
                System.out.println("Successfully reserve a room. The reservation show below");
                System.out.println(r);
                System.out.println(HR.getCustomer(emailInput));
                System.out.println("\nNow return to the Main menu\n");
                start();

            } else{
                start();
            }
        }

    }

    /**
     * To run the option 2 on the menu
     */
    public static void mainOption2(){
        String email = null;
        Collection<model.Reservation> list;
        System.out.println("\nProvide your email: ");
        email = scan.nextLine();
        if(HR.getCustomer(email) == null){
            System.out.println("Invalid email. Now return to the Main menu\n");
            start();
        }
        list = HR.getCustomersReservations(email);
        if(list.size() == 0){
            System.out.println("You do not have any reservation");
            System.out.println("Now return to the Main menu\n");
        }else{
            Object[] array = list.toArray();
            for(int i = 0; i < array.length; i++){
                System.out.println("\nReservation " +(i+1) +": "+ (model.Reservation)array[i]);
            }
            System.out.println("\nNow return to the Main menu\n");
            start();
        }
    }

    /**
     * To run the option 3 on the menu
     */
    public static void mainOption3() {
        String email = null;
        String firstName = null;
        String lastName = null;
        boolean success  = false;
        do{
            System.out.println("\nPlease Provide your Email: ");
            email = scan.nextLine();
            System.out.println("Please Provide your first name: ");
            firstName= scan.nextLine();
            System.out.println("Please Provide your last name: ");
            lastName = scan.nextLine();
            if( HR.createACustomer(email, firstName, lastName)){
                success = true;
            } else{
                System.out.println("\nEmail format is not correct or email is already used, Please try again");
            }
        } while(!success);
        System.out.println("Your account is created");
        System.out.println("\n1. Return to the main menu");
        System.out.println("2. Exit");
        String stringSubOption = scan.nextLine();
        int intSubOption = subMenuOption(stringSubOption);
        switch (intSubOption){
            case 1: start();
                break;
            case 2: mainOption5();
                break;
        }
    }

    /**
     * To run the option 4 on the menu
     */
    public static void mainOption4(){
        AM.start();
    }

    /**
     * To run the option 5 on the menu
     */
    public static void mainOption5(){
        System.out.println("\n Goodbye. Have a good day!");
        System.exit(0);
    }








}
