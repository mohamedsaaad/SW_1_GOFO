package gofo;

import java.sql.Timestamp;

/**
 * <h1> GOFO Class </h1>
 * <P>
 * class that represent slot
 * <p>
 * @author hayamIbrahim
 * @since 2020-06-01
 */
public class SlotDitals {
	
    private int hour;
    private String playgroundID;
    private boolean reserve;
    private String playerReserveID;
    private Timestamp reservationTime;
    /**
     * default constructor
     */
    public SlotDitals() {
        hour = -1;
        playgroundID = "";
        playerReserveID = "";
        reserve = false;
    }
    /**
     * constructor
     *hour
     *playgroundID
     *reserve
     *playerReserveID 
     */
    public SlotDitals(int hour, String playgroundID, boolean reserve, String playerReserveID) {
        super();
        this.hour = hour;
        this.playgroundID = playgroundID;
        this.reserve = reserve;
        this.playerReserveID = playerReserveID;
    }
    /**
     * getter
     */
    public Timestamp getReservationTime() {
        return reservationTime;
    }

    /**
     * setter
     */
    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }
    /**
     * getter
     */
    public int getHour() {
        return hour;
    }
    /**
     * setter
     */
    public void setHour(int hour) {
        this.hour = hour;
    }
    /**
     * getter
     */
    public String getPlaygroundID() {
        return playgroundID;
    }
    /**
     * setter
     */
    public void setPlaygroundID(String playgroundID) {
        this.playgroundID = playgroundID;
    }
    /**
     * getter
     */
    public boolean isReserve() {
        return reserve;
    }
    /**
     * setter
     */
    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }
    /**
     * getter
     */
    public String getPlayerReserveID() {
        return playerReserveID;
    }
    /**
     * setter
     */
    public void setPlayerReserveID(String playerReserveID) {
        this.playerReserveID = playerReserveID;
    }
    /**
     * function to return slot ditals.
     */
    public void printSlotDitails() {
        System.out.println("the slot from " + this.hour + " to" + (this.hour + 1));
    }


}
