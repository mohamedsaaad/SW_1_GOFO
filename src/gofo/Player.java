package gofo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * <h1> Player Class </h1>
 * <P>
 * Class that responsible to deal with all player functionalities
 * <p>
 * @author mostafa_darwish
 *  @since   2020-5-27
 */
public class Player extends User {


    private ArrayList<Playground> allPlayground = new ArrayList<>();
    private ArrayList<PlaygroundOwner> playgroundOwners = new ArrayList<>();
    private ArrayList<SlotDitals> myBookedSlots = new ArrayList<>();

    public Player() {
        this.setType(UserType.PLAYER);
    }
    /**
     * getter 
     */
    public ArrayList<Playground> getAllPlayground() {
        return allPlayground;
    }
    /**
     * setter
     * allPlayground 
     */
    public void setAllPlayground(ArrayList<Playground> allPlayground) {
        this.allPlayground = allPlayground;
    }
    /**
     * getter 
     */
    public ArrayList<PlaygroundOwner> getPlaygroundOwners() {
        return playgroundOwners;
    }
    /**
     * setter
     * playgroundOwners 
     */
    public void setPlaygroundOwners(ArrayList<PlaygroundOwner> playgroundOwners) {
        this.playgroundOwners = playgroundOwners;
    }
    /**
     * getter 
     */
    public ArrayList<SlotDitals> getMyBookedSlots() {
        return myBookedSlots;
    }
    /**
     * setter
     * myBookedSlots 
     */
    public void setMyBookedSlots(ArrayList<SlotDitals> myBookedSlots) {
        this.myBookedSlots = myBookedSlots;
    }
    /**
     * function to show all playgrounds to user
     */
    public void showPlaygrounds() {
        int checkAvailblePlayground = -1;
        for (Playground playground : allPlayground) {
            if ("ACTIVATED".equals(playground.getPalygroundStatus().toString())) {
                checkAvailblePlayground = 1;
                playground.printPlaygroundDitals();
                for (SlotDitals slot : playground.getSlots()) {
                    if (!slot.isReserve()) {
                        if (slot.getHour() == 12) {
                            System.out.println(
                                    "the slot from " + slot.getHour() + " PM to " + "1 PM" + " is available");
                        } else {
                            System.out.println(
                                    "the slot from " + slot.getHour() + " PM to " + (slot.getHour() + 1) + " PM is available");
                        }

                    }
                }
                if (checkAvailblePlayground == -1) {
                    System.out.println("there is no avilable playground !");
                } else {

                    System.out.println("###########################################################################");
                }
            }
        }
    }
    
    /**
     * function to book slot with all steps
     */
    public void booking() {
        int checkAvilablePlayground = -1;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < allPlayground.size(); i++) {
            if ("ACTIVATED".equals(allPlayground.get(i).getPalygroundStatus().toString())) {
                int checkForFreeSlot = -1;
                for (SlotDitals slot : allPlayground.get(i).getSlots()) {
                    if (slot.isReserve() == false) {
                        checkForFreeSlot = 1;
                    }
                }
                if (checkForFreeSlot != -1) {
                    checkAvilablePlayground = 1;
                    System.out.println((i + 1) + " -");
                    allPlayground.get(i).printPlaygroundDitals();
                    System.out.println("###########################################################################");
                } else {
                    System.out.println((i + 1) + " - this playground have no free slot");
                    allPlayground.get(i).printPlaygroundDitals();
                    System.out.println("###########################################################################");
                }

            }
        }
        if (checkAvilablePlayground != -1) {
            System.out.print("please select number of playground you want to reseve solt on it: ");
            int numOfPlaygroundChoosen = input.nextInt();
            System.out.println("========= this playground have these slots ========== ");
            int counter = 1;
            for (SlotDitals slot : allPlayground.get(numOfPlaygroundChoosen - 1).getSlots()) {
                int to = slot.getHour() + 1;
                if (!slot.isReserve()) {
                    if (slot.getHour() == 12) {
                        System.out.println(counter + "- " + "the slot from " + slot.getHour()
                                + " PM to " + "1" + " PM is available");
                    } else {
                        System.out.println(counter + "- " + "the slot from " + slot.getHour()
                                + " PM to " + to + " PM is available");
                    }

                } else {
                    if (slot.getHour() == 12) {
                        System.out.println(counter + "- " + "the slot from " + slot.getHour()
                                + " PM to " + to + " PM is booked");
                    } else {
                        System.out.println(counter + "- " + "the slot from " + slot.getHour()
                                + " PM to " + to + " PM is booked");
                    }
                }
                counter++;
            }

            System.out.print("enter the number of slot you want to book it: ");
            int numberOfSlotChoosen = input.nextInt();
            //input.close();

            Playground playground = allPlayground.get(numOfPlaygroundChoosen - 1);
            if (playground.getSlots().get(numberOfSlotChoosen - 1).isReserve()) {
                System.out.println("======== this slot is already reseved before =======");
            } else {
                if (this.getAmountOfEWallet() >= playground.getPricePerHour()) {
                    //set ReservationTime
                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);
                    playground.getSlots().get(numberOfSlotChoosen - 1).setReservationTime(ts);
                    //set the status of this slot as reserved
                    playground.getSlots().get(numberOfSlotChoosen - 1).setReserve(true);
                    //set this slot to particular user
                    playground.getSlots().get(numberOfSlotChoosen - 1).setPlayerReserveID(this.getId());

                    myBookedSlots.add(playground.getSlots().get(numberOfSlotChoosen - 1));
                    System.out.println("========== your booking operation done successfully! =========");

                    for (PlaygroundOwner owner : playgroundOwners) {
                        String ownerID = playground.getPlaygroundOwnerID();
                        if (ownerID.equals(owner.getId())) {
                            this.setAmountOfEWallet(this.getAmountOfEWallet() - playground.getPricePerHour());
                            owner.setAmountOfEWallet(owner.getAmountOfEWallet() + playground.getPricePerHour());
                        }
                    }
                } else {
                    System.out.println("========== your money is not enouph===========");
                }

            }

        }
    }
    /**
     * function to cancel book with all steps 
     */
    public void cancelBooking() {
        Scanner input = new Scanner(System.in);
        int counter = 1;
        for (SlotDitals slot : myBookedSlots) {
            String playgroundID = slot.getPlaygroundID();
            for (Playground playground : allPlayground) {
                if (playgroundID.equals(playground.getPalygroundID())) {
                    if (slot.getHour() == 12) {
                        System.out.println(counter + " - you reserve this slot form " + slot.getHour()
                                + " PM to " + "1 PM");
                        System.out.println("slot at the " + playground.getName() + " playground");
                        System.out.println("");
                    } else {
                        System.out.println(counter + " - you reserve this slot form " + slot.getHour()
                                + " PM to " + (slot.getHour() + 1) + " PM");
                        System.out.println("slot at the " + playground.getName() + " playground");
                        System.out.println("");
                    }
                }
            }
            counter++;
        }
        if (myBookedSlots.isEmpty()) {
            System.out.println("========== there is no booked slots ! =================");
        } else {
            System.out.print("please enter the number of slot you want to cancel his resevation : ");
            int slotToCanceld = input.nextInt();
            Playground selectedPlayground = new Playground();
            SlotDitals slot = myBookedSlots.get(slotToCanceld - 1);
            String playgroundID = slot.getPlaygroundID();
            for (Playground playground : allPlayground) {
                if (playgroundID.equals(playground.getPalygroundID())) {
                    selectedPlayground = playground;
                }
            }
            if (isCanclable(slot, selectedPlayground)) {
                slot.setReserve(false);
                slot.setPlayerReserveID("");
                slot.setReservationTime(null);
                for (PlaygroundOwner owner : playgroundOwners) {
                    String ownerID = selectedPlayground.getPlaygroundOwnerID();
                    if (ownerID.equals(owner.getId())) {
                        this.setAmountOfEWallet(this.getAmountOfEWallet() + selectedPlayground.getPricePerHour());
                        owner.setAmountOfEWallet(owner.getAmountOfEWallet() - selectedPlayground.getPricePerHour());
                    }
                }
            } else {
                System.out.println("============= the allowed time to cancel is passed! ===========");
            }

        }
    }
    /**
     * function to check if canclation time is passed or not
     * slot
     * playground 
     */
    public boolean isCanclable(SlotDitals slot, Playground playground) {
        //get Resevation time and add to it the canclation period
        float cancelationTime = playground.getCancelationTime();
        Timestamp ts = slot.getReservationTime();
        float temp = (float) ts.getHours() + cancelationTime;
        ts.setHours((int) temp);
        //get the cancelation time
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts1 = new Timestamp(time);
        //compare the ts variables 
        if (ts.compareTo(ts1) < 0) {
            return false;
        }
        return true;
    }
    /**
     * function to check balance
     */
    public void checkMyEWallet() {
        System.out.println("========== your balance is: " + this.getAmountOfEWallet()+" ==================");
    }

}
