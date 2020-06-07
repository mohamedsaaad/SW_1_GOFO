package gofo;
enum UserType           
{
	PLAYER,PLAYGROUNDOWNER,ADMIN
}
/**
 * <h1> User Class </h1>
 * <P>
 * this class create to be parent of admin , player and  
 * playground owner to avoid duplicate code
 * <p>
 * @author mohamedsalem
 *  @since   2020-05-30
 */
public class User {
	
	private String name;
	private String id;
	private String Email;
	private String password;
	private String phoneNumber;
	private String location;
	private float amountOfEWallet;
	private UserType type;
	/**
         * default constructor
         */
	public User() {}
        /**
         * constructor
         *  name
         *  id
         *  email
         *  password
         *  phoneNumber
         *  location
         *  amountOfEWallet
         *  type 
         */
	public User(String name, String id, String email, String password, String phoneNumber, String location,
			float amountOfEWallet, UserType type) {
		super();
		this.name = name;
		this.id = id;
		Email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.amountOfEWallet = amountOfEWallet;
		this.type = type;
	}
        /**
         * getter 
         */
	public String getName() {
		return name;
	}
        /**
         * setter 
         */
        public void setName(String name) {
		this.name = name;
	}
        /**
         * getter 
         */
        public String getId() {
		return id;
	}
        /**
         * setter 
         */
        public void setId(String id) {
		this.id = id;
	}
        /**
         * getter 
         */
        public String getEmail() {
		return Email;
	}
        /**
         * setter 
         */
	public void setEmail(String email) {
		Email = email;
	}
        /**
         * getter 
         */
        public String getPassword() {
		return password;
	}
        /**
         * setter 
         */
	public void setPassword(String password) {
		this.password = password;
	}
        /**
         * getter 
         */
        public String getPhoneNumber() {
		return phoneNumber;
	}
        /**
         * setter 
         */	
        public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
        /**
         * getter 
         */
        public String getLocation() {
		return location;
	}
        /**
         * setter 
         */
        public void setLocation(String location) {
		this.location = location;
	}
        /**
         * getter 
         */	
        public UserType getType() {
		return type;
	}
        /**
         * setter 
         */
	public void setType(UserType type) {
		this.type = type;
	}
        /**
         * getter 
         */
	public float getAmountOfEWallet() {
		return amountOfEWallet;
	}
	public void setAmountOfEWallet(float amountOfEWallet) {
		this.amountOfEWallet = amountOfEWallet;
	}
	/**
          * function to transfer money with between users
          *  amontOfMoney
          *  user2 
          */
	public void transfarMoney(float amontOfMoney,User user2) {
		if(this.getAmountOfEWallet() >= amontOfMoney) {
			user2.setAmountOfEWallet(user2.getAmountOfEWallet()+amountOfEWallet);
		}
	}
}
