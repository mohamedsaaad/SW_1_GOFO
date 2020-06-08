package gofo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

/**
 * <h1> Owner Class </h1>
 * <P>
 * class to manage playground owner functionalities
 * <p>
 * @author HayamIbrahim
 *  @since   2020-5-15
 */

public class PlaygroundOwner extends User {
	  private Playground myPlayground;
    
    public PlaygroundOwner() {
        this.setType(UserType.PLAYGROUNDOWNER);
    }
    /**
     * function to get Playground 
     */
    public Playground getMyPlayground() {
        return myPlayground;
    }

    /**
     * function to set Playground
     *myPlayground 
     */
    public void setMyPlayground(Playground myPlayground) {
        this.myPlayground = myPlayground;
    }
    /**
     * function that allow the owner to add playground
     *id 
     */
    public Playground addPlayground(String id) {
        Playground playground = null;
        if (this.myPlayground == null) {
            Scanner input = new Scanner(System.in);
            System.out.print("enter you playground name :");
            String name = input.nextLine();
            System.out.print("enter the playground location : ");
            String location = input.nextLine();
            System.out.print("enter the playground size : ");
            String size = input.nextLine();
            System.out.print("enter the price per hour in EG pound : ");
            float pricePerHour = input.nextFloat();
            System.out.print("enter the cancelationPeriod size in hours: ");
            float cancelationTime = input.nextFloat();
            playground = new Playground(name, location, pricePerHour, size, cancelationTime, this.getId(), id);
            this.myPlayground = playground;
            System.out.println("================== please wait approval from the administrator ! ========================");
        } else {
            System.out.println("================= you already have a playground. ==========================");
        }
        return playground;
    }
    /**
     * function allow the owner to view his playground booked slot 
     */
    public void viewPlaygroundBookedSlots() {
        int check =-1;
        for (SlotDitals slot : this.myPlayground.getSlots()) {
            if (slot.isReserve()) {
                check=1;
                if (slot.getHour() == 12) {
                    System.out.println(
                            "the slot from " + slot.getHour() + " PM to " + "1 PM" + " is booked slot");
                } else {
                    System.out.println(
                            "the slot from " + slot.getHour() + " PM to " + (slot.getHour() + 1) + " PM is booked slot");
                }
            }
        }
        if(check==-1){
            System.out.println("========== you have no booked slot ============");
        }
    }
    /**
     * function allow the owner to change the availability of slots
     * but after 11 PM only 
     */
    public void changeAvailableHours() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp st = new Timestamp(time);
        if (st.getHours() > 23) {
            Scanner input = new Scanner(System.in);
            for (int i = 0; i < myPlayground.getSlots().size(); i++) {
                SlotDitals slot = myPlayground.getSlots().get(i);
                if (slot.isReserve()) {
                    if (slot.getHour() == 12) {
                        System.out.println(
                                "the slot from " + slot.getHour() + " PM to " + "1 PM" + " is booked");
                    } else {
                        System.out.println(
                                "the slot from " + slot.getHour() + " PM to " + (slot.getHour() + 1) + " PM is booked");
                    }
                } else {
                    if (slot.getHour() == 12) {
                        System.out.println(
                        "the slot from " + slot.getHour() + " PM to " + "1 PM" + " is available");
                    } else {
                        System.out.println(
                        "the slot from " + slot.getHour() + " PM to " + (slot.getHour() + 1) + " PM is available");
                    }
                }
            }
            System.out.print("enter the number of slot you want to change it :");
            int choosenSlot = input.nextInt();
            input.close();
            SlotDitals slot = myPlayground.getSlots().get(choosenSlot - 1);
            if (slot.isReserve()) {
                slot.setReserve(false);
                if (slot.getHour() == 12) {
                    System.out.println(
                            "the slot from " + slot.getHour() + " PM to " + "1 PM" + " is become an availble slot");
                } else {
                    System.out.println(
                            "the slot from " + slot.getHour() + " PM to " + (slot.getHour() + 1) + " PM become an availble slot");
                }
   
            } else {
                 slot.setReserve(true);
                if (slot.getHour() == 12) {
                    System.out.println(
                            "the slot from " + slot.getHour() + " PM to " + "1 PM" + " isbecome a booked slot");
                } else {
                    System.out.println(
                            "the slot from " + slot.getHour() + " PM to " + (slot.getHour() + 1) + " PM become a booked slot");
                }
                
            }
        }else {
            System.out.println("============= you are not allowed to modify anything untill 11:00 PM ====================");
        }
    }
    /**
     * function allow the owner to update playground
     * but after any update the playground status will be Suspended 
     * till admin check this change
     */
    public void updatePlayground() {
        Scanner input = new Scanner(System.in);
        System.out.print("you can do this operations:\n\t1-change playground name.\n\t"
                    + "2-change playground location.\n\t3-change playground size.\n\t4-change price per hour.\n\t"
                    + "5-change cancelationPeriod.\nchoice: ");
        int choose = input.nextInt();
        while (true) {
            if (choose != 1 && choose != 2 && choose != 3 && choose != 4 && choose != 5) {
                System.out.println("please enter valid choice.");
            } else {
                break;
            }
        }
        if (choose == 1) {
            System.out.print("enter the new name of palyground :");
            input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            String name = input.nextLine();
            this.myPlayground.setName(name);
            this.myPlayground.setPalygroundStatus(PlaygroundStatus.SUSPENDED);
            System.out.println("====== after this update your playgroud is suspended till activation from amidn ======");
        } else if (choose == 2) {
            System.out.println("enter the new location of palyground :");
            input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            String location = input.nextLine();
            this.myPlayground.setLocation(location);
            this.myPlayground.setPalygroundStatus(PlaygroundStatus.SUSPENDED);
            System.out.println("====== after this update your playgroud is suspended till activation from amidn ======");
        } else if (choose == 3) {
            System.out.print("enter the new size of palyground : ");
            input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            String size = input.nextLine();
            this.myPlayground.setSize(size);
            this.myPlayground.setPalygroundStatus(PlaygroundStatus.SUSPENDED);
            System.out.println("====== after this update your playgroud is suspended till activation from amidn ======");
        } else if (choose == 4) {
            System.out.print("enter the new price per hour for palyground in EG pound: ");
            input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            float pricePerHour = input.nextFloat();
            this.myPlayground.setPricePerHour(pricePerHour);
            this.myPlayground.setPalygroundStatus(PlaygroundStatus.SUSPENDED);
            System.out.println("====== after this update your playgroud is suspended till activation from amidn ======");
        } else if (choose == 5) {
            System.out.println("enter the new cancelation Time for palyground in hour : ");
            input.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            float cancelationTime = input.nextFloat();
            this.myPlayground.setCancelationTime(cancelationTime);
            this.myPlayground.setPalygroundStatus(PlaygroundStatus.SUSPENDED);
            System.out.println("====== after this update your playgroud is suspended till activation from amidn ======");
        }
    }
    /**
     * function to check balance
     */
     public void checkMyEWallet() {
        System.out.println("========== your balance is: " + this.getAmountOfEWallet()+" ==================");
    }

}
