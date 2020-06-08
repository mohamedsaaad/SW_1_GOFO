
package gofo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1> GOFO Class </h1>
 * <P>
 * this is the main class that contain the main function 
 * to go on with application functionlties.
 * <p>
 * @author mohamedsaad
 *  @since   2020-06-04
 */
public class GOFO {
    static Admin admin = new Admin();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Player> players = new ArrayList<>();
    static ArrayList<PlaygroundOwner> playgroundOwners = new ArrayList<>();
    static ArrayList<Playground> playgrounds = new ArrayList<>();
    static ArrayList<SlotDitals> slots = new ArrayList<>();
    static DatabaseController database = DatabaseController.getInstance();

    /**
     * function to do the step needed to close program like drop the old
     * database , create new one and fill the tables.
     */
    private static void exitFun() {
        database.strConn();
        database.dropDatabase();
        database.createNewDatabase();
        ArrayList<User> newUsers = new ArrayList<>();
        for (Player player : players) {
            newUsers.add(player);
        }
        for (PlaygroundOwner playgroundOwner : playgroundOwners) {
            newUsers.add(playgroundOwner);
        }
        admin = new Admin();
        admin.setId(Integer.toString(users.size() + 1));
        admin.setName("sa7eb el cema webno");
        admin.setEmail("marwan3emad@hotmail.com");
        admin.setAmountOfEWallet(400);
        admin.setPassword("@-111");
        admin.setPhoneNumber("01090719097");
        admin.setLocation("swdqd");
        admin.setType(UserType.ADMIN);
        newUsers.add(admin);

        for (User user : newUsers) {
            database.insertUser(user);
        }
        for (Playground playground : playgrounds) {
            if (!"DELETED".equals(playground.getPalygroundStatus().toString())) {
                database.insertPlayground(playground);
            }
        }
        for (SlotDitals slot : slots) {
            database.insertSlot(slot);
        }
        database.closeConn();
        System.exit(0);
    }

    /**
     * function to show player functionalities like show all playground , book
     * playground or cancel booking user
     */
    private static void playerFunctions(User user) {
        Player player = (Player) user;
        player.setPlaygroundOwners(playgroundOwners);
        player.setAllPlayground(playgrounds);
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("you can do this operations:\n\t1-show all available playgrounds.\n\t"
                    + "2-booking a slot to play.\n\t3-cancel a slot you have already booked\n\t4-exit.\n");
            System.out.print("choice: ");
            int choice;
            while (true) {
                input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                choice = input.nextInt();
                if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                    System.out.println("you entered invalid choice!\nchoice: ");
                } else {
                    break;
                }
            }
            if (choice == 1) {
                player.showPlaygrounds();
            } else if (choice == 2) {
                player.booking();
            } else if (choice == 3) {
                player.cancelBooking();
            } else if (choice == 4) {
                exitFun();
            }
        }
    }

    /**
     * function to show playground owner functionalities like show his booked
     * slot change available slot ,update his playground data or add playground
     * if have not playground user
     */
    private static void playgroundOwnerFunctions(User user) {
        PlaygroundOwner playgroundOwner = (PlaygroundOwner) user;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("you can do this operations:\n\t1-add a playground.\n\t"
                    + "2-view playgrounds booked slots.\n\t3-change available hours.\n\t4-update my playground."
                    + "\n\t5-check my wallet.\n\t6-exit.\nchoice: ");
            int choice;
            while (true) {
                choice = input.nextInt();
                if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6) {
                    System.out.println("========= you entered invalid choice!=========\nchoice: ");
                } else {
                    break;
                }
            }
            if (choice == 6) {
                exitFun();
            } else {
                switch (choice) {
                    case 1:
                        Playground p = playgroundOwner.addPlayground(Integer.toString(playgrounds.size() + 1));
                        if (p != null) {
                            playgrounds.add(p);
                            for (SlotDitals slot : p.getSlots()) {
                                slots.add(slot);
                            }
                        }

                        break;
                    case 2:
                        playgroundOwner.viewPlaygroundBookedSlots();
                        break;
                    case 3:
                        playgroundOwner.changeAvailableHours();
                        break;
                    case 4:
                        playgroundOwner.updatePlayground();
                        break;
                    case 5:
                        playgroundOwner.checkMyEWallet();
                        break;
                }
            }

        }

    }

    /**
     * function to show administrator functionalities like activate , suspend
     * ,approve or delete playground user
     */
    private static void adminFunctions(User user) {
        Admin admin = (Admin) user;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("you can do this operations:\n\t1-activate a playground.\n\t"
                    + "2-delete a playground.\n\t3-approve a playground\n\t4-suspend a playground.\n\t"
                    + "5-exit.\nchoice: ");
            int choice;
            while (true) {
                choice = input.nextInt();
                if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
                    System.out.println("you entered invalid choice!\nchoice: ");
                } else {
                    break;
                }
            }
            if (choice == 5) {
                exitFun();
            } else {
                int i = 1;
                for (Playground playground : playgrounds) {
                    System.out.println("playground number " + i + " :");
                    playground.printPlaygroundDitals();
                    System.out.println("the playground status is: " + playground.getPalygroundStatus().toString());
                    System.out.println("============================================");
                    i++;
                }
                int selectedPlayground;
                while (true) {
                    System.out.print("enter playground number that you want: ");
                    selectedPlayground = input.nextInt();
                    if (choice < 1 && choice > playgrounds.size()) {
                        System.out.println("you entered invalid choice!\nchoice: ");
                    } else {
                        break;
                    }
                }
                switch (choice) {
                    case 1:
                        admin.activatePlayground(playgrounds.get(selectedPlayground - 1));
                        break;
                    case 2:
                        admin.deletePlayground(playgrounds.get(selectedPlayground - 1));
                        break;
                    case 3:
                        admin.approvePlayground(playgrounds.get(selectedPlayground - 1));
                        break;
                    case 4:
                        admin.suspendPlayground(playgrounds.get(selectedPlayground - 1));
                        break;

                }

            }

        }

    }

    /**
     * function to parse data that read from database table to prepare the
     * ArrayList to use it in my program logic
     */
    public static void parseUsers() {
        for (User user : users) {
            if ("PLAYER".equals(user.getType().toString())) {
                players.add((Player) user);
            } else if ("PLAYGROUNDOWNER".equals(user.getType().toString())) {
                playgroundOwners.add((PlaygroundOwner) user);
            } else if ("ADMIN".equals(user.getType().toString())) {
                admin = (Admin) user;
            }
        }
    }

    /**
     * function to set playground to owner
     */
    public static void setPlaygrounTohisOwner() {
        for (PlaygroundOwner owner : playgroundOwners) {
            String ownerID = owner.getId();
            for (Playground playground : playgrounds) {
                if (ownerID.equals(playground.getPlaygroundOwnerID())) {
                    owner.setMyPlayground(playground);
                }
            }
        }
    }

    /**
     * function to set slot to playground
     */
    public static void setPlaygroundSlots() {
        for (int i = 0; i < playgrounds.size(); i++) {
            String id = playgrounds.get(i).getPalygroundID();
            ArrayList<SlotDitals> mySlots = new ArrayList<>();
            for (int j = 0; j < slots.size(); j++) {
                if (id.equals(slots.get(j).getPlaygroundID())) {
                    mySlots.add(slots.get(j));
                }
            }
            playgrounds.get(i).setSlots(mySlots);
        }
    }

    /**
     * function to set the booked slot to the play that booked it
     */
    public static void setPlayerBookedSlots() {
        for (int i = 0; i < players.size(); i++) {
            String id = players.get(i).getId();
            ArrayList<SlotDitals> myBookedSlots = new ArrayList<>();
            for (int j = 0; j < slots.size(); j++) {
                if (id.equals(slots.get(j).getPlayerReserveID())) {
                    myBookedSlots.add(slots.get(j));
                }
            }
            players.get(i).setMyBookedSlots(myBookedSlots);
        }
    }

    /**
     * function that read all data from database tables
     */
    public static void getData() {
        database.strConn();
        users = database.getAllUsers();
        playgrounds = database.getAllPlayground();
        slots = database.getAllSlots();
        database.closeConn();
    }

    /**
     * main function that start the program args
     */
    public static void main(String[] args) {
        getData();
        parseUsers();
        setPlaygroundSlots();
        setPlayerBookedSlots();
        setPlaygrounTohisOwner();
        Scanner input = new Scanner(System.in);
        int loggingType;
        while (true) {
            while (true) {
                System.out.println("1-login\n2-sign up\n3-exit ");
                System.out.print("choice: ");
                loggingType = input.nextInt();
                if (loggingType != 1 && loggingType != 2 && loggingType != 3) {
                    System.out.println("please enter valid choice.");
                } else {
                    break;
                }
            }
            if (loggingType == 3) {
                exitFun();
            } else {
                System.out.println("======================================================================");
                User user;
                if (loggingType == 1) {
                    String email;
                    String password;

                    System.out.print("Enter your email: ");
                    input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    email = input.nextLine();
                    System.out.print("enter your password: ");
                    input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    password = input.nextLine();
                    user = Authentication.logIn(email, password, users);
                    if (user != null) {
                        if ("PLAYER".equals(user.getType().toString())) {
                            playerFunctions(user);
                        } else if ("PLAYGROUNDOWNER".equals(user.getType().toString())) {
                            playgroundOwnerFunctions(user);

                        } else if ("ADMIN".equals(user.getType().toString())) {
                            adminFunctions(user);
                        }

                    } else {
                        System.out.println("you entered wrong email or passsword ! ");
                    }
                } else if (loggingType == 2) {
                    int userType;
                    while (true) {
                        System.out.println("1-as a player\n2-as a playgroundowner");
                        input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                        System.out.print("choice: ");
                        userType = input.nextInt();
                        if (userType != 1 && userType != 2) {
                            System.out.println("please enter valid choice.");
                        } else {
                            break;
                        }
                    }
                    System.out.println("enter your data: ");
                    System.out.print("\tname: ");
                    input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    String name = input.nextLine();
                    String id = Integer.toString(users.size() + 1);
                    System.out.print("\temail: ");
                    input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    String email = input.nextLine();
                    System.out.print("\tpassword: ");
                    input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    String password = input.nextLine();
                    System.out.print("\tphone number: ");
                    input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    String phoneNumber = input.nextLine();
                    System.out.print("\tlocation: ");
                    input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    String location = input.nextLine();
                    switch (userType) {
                        case 1:
                            if (Authentication.signUp(email, phoneNumber, users)) {
                                User newUser = new Player();
                                newUser.setName(name);
                                newUser.setEmail(email);
                                newUser.setAmountOfEWallet(400);
                                newUser.setPassword(password);
                                newUser.setPhoneNumber(phoneNumber);
                                newUser.setLocation(location);
                                newUser.setId(id);
                                newUser.setType(UserType.PLAYER);
                                users.add(newUser);
                                players.add((Player) newUser);
                                playerFunctions(newUser);
                                break;
                            }
                            break;
                        case 2:
                            if (Authentication.signUp(email, phoneNumber, users)) {
                                User newUser = new PlaygroundOwner();
                                newUser.setName(name);
                                newUser.setEmail(email);
                                newUser.setAmountOfEWallet(400);
                                newUser.setPassword(password);
                                newUser.setPhoneNumber(phoneNumber);
                                newUser.setLocation(location);
                                newUser.setId(id);
                                newUser.setType(UserType.PLAYER);
                                users.add(newUser);
                                playgroundOwners.add((PlaygroundOwner) newUser);
                                playgroundOwnerFunctions(newUser);
                                break;
                            }
                    }
                }
            }
        }
    }

}
