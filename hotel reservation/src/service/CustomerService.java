package service;
import java.util.*;
import java.util.regex.*;

public class CustomerService {
    private static CustomerService CS;
    private Map<String, model.Customer> customerMap = new HashMap<>();
    private Pattern pattern;
    private Matcher matcher;

    /**
     * Provide a static reference
     * @return customer service
     */
    public static CustomerService getInstance(){
        if(CS == null){
            CS = new CustomerService();
        }
        return CS;
    }

    /**
     * Check the validation of the email.If it is valid, add the customer
     * in the customerMap
     * @param email customer's email
     * @param firstName customer' first name
     * @param lastName customer's last name
     * @return return true if the email is valid
     */
    public boolean addCustomer(String email, String firstName, String lastName){
        pattern = Pattern.compile("(.+)@(.+).(com)");  // Format: xxx@xxx.com
        matcher = pattern.matcher(email);
        boolean emailValid = matcher.matches();
        if(!emailValid){
            return false;
        } else if(customerMap.containsKey(email)){
            return false;
        }
        model.Customer newCustomer = new model.Customer(email, firstName, lastName);
        customerMap.put(email, newCustomer);
        return true;
    }

    /**
     * return the customer, if the email do not exist, return null
     * @param customerEmail customer's email
     * @return return the customer if the email exist.
     */
    public model.Customer getCustomer(String customerEmail){
        return customerMap.get(customerEmail);
    }

    /**
     * return the list of all customer
     * @return the list of all customer
     */
    public Collection<model.Customer> getAllCustomers(){
        return customerMap.values();
    }

}
