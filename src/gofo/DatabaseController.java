package gofo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1> Database Controller Class </h1>
 * <P>
 * this is the class that Responsible to deal with any operation with 
 * base like insert ,drop ,delete,create or get data from database
 * this class is singleton class to deal with database with one object
 * <p>
 * @author mohamedsaad
 *  @since   2020-5-22
 */
public class DatabaseController {
  private static DatabaseController database = null;
    private static Connection conn;
    /**
     * this is the private constrictor    
     */
    private DatabaseController() {
    }
    /**
     * this function is the single way to get object from this class
     * and only object object to deal with database
     */
    public static DatabaseController getInstance() {
        if (database == null) {
            synchronized (DatabaseController.class) {
                database = new DatabaseController();
            }
        }
        return database;
    }
    /**
     * function to start the connection with database
     */
    public void strConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/GOFO?verfiyServerCertificate=false&userSSL=true", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
    }
    /**
     * function to return the all data from table user  
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from USER");
            int i = 1;
            while (rs.absolute(i)) {
                User newUser = null;
                switch (rs.getInt(8)) {
                    case 0:
                        newUser = new Admin();
                        newUser.setType(UserType.ADMIN);
                        break;
                    case 1:
                        newUser = new Player();
                        newUser.setType(UserType.PLAYER);
                        break;
                    case 2:
                        newUser = new PlaygroundOwner();
                        newUser.setType(UserType.PLAYGROUNDOWNER);
                        break;
                }

                newUser.setId(rs.getString(1));
                newUser.setName(rs.getString(2));
                newUser.setEmail(rs.getString(3));
                newUser.setPassword(rs.getString(4));
                newUser.setPhoneNumber(rs.getString(5));
                newUser.setLocation(rs.getString(6));
                newUser.setAmountOfEWallet(rs.getFloat(7));
                users.add(newUser);
                i++;
            }
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        return users;
    }
    /**
     * function to return the all data from table slot  
     */
    public ArrayList<SlotDitals> getAllSlots() {
        ArrayList<SlotDitals> slots = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from SLOT");
            int i = 1;
            while (rs.absolute(i)) {
                SlotDitals slot = new SlotDitals();
                slot.setPlayerReserveID(rs.getString(1));
                slot.setPlaygroundID(rs.getString(2));
                slot.setHour(rs.getInt(4));
                slot.setReserve(rs.getBoolean(3));
                slot.setReservationTime(rs.getTimestamp(5));
                slots.add(slot);
                i++;
            }
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        return slots;
    }
    /**
     * function to return the all data from table playground 
     */
    public ArrayList<Playground> getAllPlayground() {
        ArrayList<Playground> playgrounds = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from playground");
            int i = 1;
            while (rs.absolute(i)) {
                Playground playground = new Playground();
                playground.setPalygroundID(rs.getString(1));
                playground.setPlaygroundOwnerID(rs.getString(2));
                playground.setName(rs.getString(3));
                playground.setLocation(rs.getString(4));
                playground.setPricePerHour(rs.getFloat(5));
                playground.setSize(rs.getString(6));
                //ACTIVATED,SUSPENDED,WATINGAPPROVAL,DELETED,APPROVED
                String playgroundStatus = rs.getString(7);
                if (("ACTIVATED").equals(playgroundStatus)) {
                    playground.setPalygroundStatus(PlaygroundStatus.ACTIVATED);
                } else if (("SUSPENDED").equals(playgroundStatus)) {
                    playground.setPalygroundStatus(PlaygroundStatus.SUSPENDED);
                } else if (("WATINGAPPROVAL").equals(playgroundStatus)) {
                    playground.setPalygroundStatus(PlaygroundStatus.WATINGAPPROVAL);
                } else if (("DELETED").equals(playgroundStatus)) {
                    playground.setPalygroundStatus(PlaygroundStatus.DELETED);
                } else if (("APPROVED").equals(playgroundStatus)) {
                    playground.setPalygroundStatus(PlaygroundStatus.APPROVED);
                }
                playground.setCancelationTime(rs.getFloat(8));
                playgrounds.add(playground);
                i++;
            }
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
        return playgrounds;
    }
    /**
     * function to insert new user to user table 
     */    
    public void insertUser(User user) {
        try {
            String query = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, user.getId());
            preparedStmt.setString(2, user.getName());
            preparedStmt.setString(3, user.getEmail());
            preparedStmt.setString(4, user.getPassword());
            preparedStmt.setString(5, user.getPhoneNumber());
            preparedStmt.setString(6, user.getLocation());
            preparedStmt.setFloat(7, user.getAmountOfEWallet());
            int type = 0;
            user.getType().toString();
            if (("ADMIN").equals(user.getType().toString())) {
                type = 0;
            } else if (("PLAYER").equals(user.getType().toString())) {
                type = 1;
            } else if (("PLAYGROUNDOWNER").equals(user.getType().toString())) {
                type = 2;
            }

            preparedStmt.setInt(8, type);
            preparedStmt.execute();

        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
    }
    /**
     * function to insert new slot to slot table  
     */
    public void insertSlot(SlotDitals slot) {
        try {
            String query = "INSERT INTO SLOT VALUES(?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, slot.getPlayerReserveID());
            preparedStmt.setString(2, slot.getPlaygroundID());
            preparedStmt.setBoolean(3, slot.isReserve());
            preparedStmt.setInt(4, slot.getHour());
            preparedStmt.setTimestamp(5, slot.getReservationTime());
            preparedStmt.execute();
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
    }
    /**
     * function to insert new playground to playground table  
     */
    public void insertPlayground(Playground playground) {
        try {       
                String query = "INSERT INTO playground VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, playground.getPalygroundID());
                preparedStmt.setString(2, playground.getPlaygroundOwnerID());
                preparedStmt.setString(3, playground.getName());
                preparedStmt.setString(4, playground.getLocation());
                preparedStmt.setFloat(5, playground.getPricePerHour());
                preparedStmt.setString(6, playground.getSize());
                String status = "";
                if (("ACTIVATED").equals(playground.getPalygroundStatus().toString())) {
                    status = "ACTIVATED";
                } else if (("SUSPENDED").equals(playground.getPalygroundStatus().toString())) {
                    status = "SUSPENDED";
                } else if (("WATINGAPPROVAL").equals(playground.getPalygroundStatus().toString())) {
                    status = "WATINGAPPROVAL";
                } else if (("DELETED").equals(playground.getPalygroundStatus().toString())) {
                    status = "DELETED";
                } else if (("APPROVED").equals(playground.getPalygroundStatus().toString())) {
                    status = "APPROVED";
                }
                preparedStmt.setString(7, status);
                preparedStmt.setFloat(8, playground.getCancelationTime());
                preparedStmt.execute();
            }catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
    }
    /**
     * function to drop all database tables 
     */
    public void dropDatabase() {
        try {
            Statement stat = conn.createStatement();
            String s2 = "drop table if exists PLAYGROUND";
            String s3 = "drop table if exists SLOT";
            String s5 = "drop table if exists USER";
            stat.execute(s2);
            stat.execute(s3);
            stat.execute(s5);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * function to create new database table  
     */
    public void createNewDatabase() {
        try {
            Statement stat = conn.createStatement();
            String s1 = "create table PLAYGROUND \n"
                    + "(\n"
                    + "   PG_ID                varchar(24)                    not null,\n"
                    + "   OWNER_ID             varchar(50)                    not null,\n"
                    + "   PG_NAME              varchar(50)                    null,\n"
                    + "   LOCATION             varchar(1024)                  not null,\n"
                    + "   PRICE                float                          not null,\n"
                    + "   SIZE                 varchar(20)                    not null,\n"
                    + "   STATUS               varchar(50)                    null,\n"
                    + "   CANCEL_TIME          float                          not null,\n"
                    + "   constraint PK_PLAYGROUND primary key (PG_ID)\n"
                    + ");";
            String s2 = "create unique index PLAYGROUND_PK on PLAYGROUND (\n"
                    + "PG_ID ASC\n"
                    + ");";
            String s3 = "create table SLOT \n"
                    + "(\n"
                    + "   PLAYER_ID            varchar(50)                    null,\n"
                    + "   PG_ID                varchar(50)                    not null,\n"
                    + "   RESERVED             boolean                       not null,\n"
                    + "   TIME                 decimal(2)                     not null,\n"
                    + "   RESREVATION_TIME     TIMESTAMP		        null\n"
                    + ");";
            String s4 = "create table USER \n"
                    + "(\n"
                    + "   USER_ID              varchar(24)                    not null,\n"
                    + "   NAME                 varchar(50)                    not null,\n"
                    + "   EMAIL                varchar(100)                   not null,\n"
                    + "   PASSWORD             varchar(100)                   not null,\n"
                    + "   PHONE_NUM            varchar(13)                    not null,\n"
                    + "   LOCATION             varchar(1024)                  not null,\n"
                    + "   BALANCE              float                          null,\n"
                    + "   USER_TYPE            decimal(1)                     not null,\n"
                    + "   constraint PK_USER primary key (USER_ID)\n"
                    + ");";
            String s5 = "create unique index USER_PK on USER (\n"
                    + "USER_ID ASC\n"
                    + ");";
            stat.execute(s1);
            stat.execute(s2);
            stat.execute(s3);
            stat.execute(s4);
            stat.execute(s5);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * function to delete all user  
     */
    public void deletAllUser() {
        try {
            Statement stat = conn.createStatement();
            stat.execute("TRUNCATE TABLE books");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * function to delete all slots  
     */
    public void deletAllslots() {
        try {
            Statement stat = conn.createStatement();
            stat.execute("TRUNCATE TABLE slot");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * function to delete all playground  
     */
    public void deletAllplayground() {
        try {
            Statement stat = conn.createStatement();
            stat.execute("TRUNCATE TABLE playground");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * function to close database connection  
     */
    public void closeConn() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
        }
    }

}
