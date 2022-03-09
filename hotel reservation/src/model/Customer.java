package model;
import java.util.regex.*;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private Pattern pattern;
    private Matcher matcher;

    /**
     * If the email is invalid, do not store customer' information and throw
     * an IllegalArgumentException.
     * @param firstName customer's first name
     * @param lastName customer's last name
     * @param email customer's email
     */
    public Customer(String email,String firstName, String lastName){
        /*
        pattern = Pattern.compile("(.+)@(.+).(com)");  // Format: xxx@xxx.com
        matcher = pattern.matcher(email);
        boolean emailValid = matcher.matches();
        if(!emailValid){
            throw new IllegalArgumentException();
        } else{
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
         */
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString(){
        return "First Name: " + firstName + ", Last Name: " + lastName
                + ", Email: " + email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof Customer)){
            return false;
        }
        Customer o = (Customer)other;
        return o.email.equals(this.email);
    }

    @Override
    public int hashCode(){
        return this.email.hashCode();
    }

}
