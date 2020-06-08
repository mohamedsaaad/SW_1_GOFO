package gofo;

import java.util.ArrayList;
/**
 * <h1> Authentication Class </h1>
 * <P>
 * class to validate the input data to allow the 
 * user to go with his functionalities
 * <p>
 * @author mostafa_darwish
 *  @since   2020-5-16
 */
public class Authentication {


    public Authentication() {
        super();
    }
    /**
     * function to create new account to new user 
     * with check if the email and phone number
     * not used before
     *email
     *phoneNumber
     *systemUser 
     */
    public static boolean signUp( String email,
             String phoneNumber, ArrayList<User> systemUser) {
        for (User user : systemUser) {
            if (email.equals(user.getEmail())) {
                System.out.println("this Email used from another user !");
                return false;
            }
            if (phoneNumber.equals(user.getPhoneNumber())) {
                System.out.println("this phone number used from another user !");
                return false;
            }
        }
        return true;
    }
    /**
     * function to check if this user already exist or not
     *email
     *password
     *systemUser 
     */
    public static User logIn(String email, String password, ArrayList<User> systemUser) {
        for (User user : systemUser) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

}
