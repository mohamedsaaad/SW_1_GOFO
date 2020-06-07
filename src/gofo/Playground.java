package gofo;
import java.util.ArrayList;

enum PlaygroundStatus{
	ACTIVATED,SUSPENDED,WATINGAPPROVAL,DELETED,APPROVED
}
/**
 * <h1> playground </h1>
 * <P>
 * class that consider the representative of playground 
 * <p>
 * @author mohamedsalem
 *  @since   2020-05-028
 */
public class Playground {		
	private String palygroundID;
	private String name;
	private String location;
	private ArrayList<SlotDitals> slots=new ArrayList<>();
	private float pricePerHour;
	private String size;
	private float cancelationTime;
	private String playgroundOwnerID;
	private PlaygroundStatus palygroundStatus;
	/**
         * default constructor to create 12 slot for this playground 
         */
	public Playground() {
		palygroundStatus=PlaygroundStatus.WATINGAPPROVAL;
		SlotDitals slot=new SlotDitals();
		slot.setHour(12);
		slot.setReserve(false);
		slot.setPlaygroundID(this.palygroundID);
		slots.add(slot);
		for(int i=1 ;i<12;i++) {
			slot=new SlotDitals();
			slot.setHour(i);
			slot.setReserve(false);
			slot.setPlaygroundID(this.palygroundID);
			slots.add(slot);
		}
                this.setSlots(slots);
	}
	
	public Playground(String name, String location,
			float pricePerHour, String size, float cancelationTime, String playgroundOwnerID,String playgroundID) {
                palygroundStatus=PlaygroundStatus.WATINGAPPROVAL;
		this.name = name;
		this.location = location;
		this.palygroundID=playgroundID;
                
		SlotDitals slot=new SlotDitals();
		slot.setHour(12);
		slot.setReserve(false);
		slot.setPlaygroundID(this.palygroundID);
		slots.add(slot);
		for(int i=1 ;i<11;i++) {
			SlotDitals slot1=new SlotDitals();
			slot1.setHour(i);
			slot1.setReserve(false);
			slot1.setPlaygroundID(this.palygroundID);
			slots.add(slot1);
		}
		
		this.pricePerHour = pricePerHour;
		this.size = size;
		this.cancelationTime = cancelationTime;
		this.playgroundOwnerID = playgroundOwnerID;
	}
        /**
         * getter
         * @return 
         */
	public String getPalygroundID() {
		return palygroundID;
	}
        /**
         * setter
         * @return 
         */
	public void setPalygroundID(String palygroundID) {
		this.palygroundID = palygroundID;
	}
        /**
         * getter
         * @return 
         */
	public String getName() {
		return name;
	}
        /**
         * setter
         * @return 
         */
	public void setName(String name) {
		this.name = name;
	}
        /**
         * getter
         * @return 
         */
	public String getLocation() {
		return location;
	}
        /**
         * setter
         * @return 
         */
	public void setLocation(String location) {
		this.location = location;
	}
        /**
         * getter
         * @return 
         */
	public ArrayList<SlotDitals> getSlots() {
		return slots;
	}
        /**
         * setter
         * @return 
         */
	public void setSlots(ArrayList<SlotDitals> slots) {
		this.slots = slots;
	}
        /**
         * getter
         * @return 
         */
	public float getPricePerHour() {
		return pricePerHour;
	}
        /**
         * setter
         * @return 
         */
	public void setPricePerHour(float pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
        /**
         * getter
         * @return 
         */
	public String getSize() {
		return size;
	}
        /**
         * setter
         * @return 
         */
	public void setSize(String size) {
		this.size = size;
	}
        /**
         * getter
         * @return 
         */
	public float getCancelationTime() {
		return cancelationTime;
	}
        /**
         * setter
         * @return 
         */
	public void setCancelationTime(float cancelationTime) {
		this.cancelationTime = cancelationTime;
	}
        /**
         * getter
         * @return 
         */
	public String getPlaygroundOwnerID() {
		return playgroundOwnerID;
	}
        /**
         * setter
         * @return 
         */
	public void setPlaygroundOwnerID(String playgroundOwnerID) {
		this.playgroundOwnerID = playgroundOwnerID;
	}
        /**
         * getter
         * @return 
         */
	public PlaygroundStatus getPalygroundStatus() {
		return palygroundStatus;
	}
        /**
         * setter
         * @return 
         */
	public void setPalygroundStatus(PlaygroundStatus palygroundStatus) {
		this.palygroundStatus = palygroundStatus;
	}	
        /**
         * function to print playground ditals
         */
	public void printPlaygroundDitals() {
		System.out.println("the playground name is :" +this.getName());
		System.out.println("the playground location is :" +this.getLocation());
		System.out.println("the price per hour for this playground  is :" +this.getPricePerHour());
		System.out.println("the size of this playground is :" +this.getSize());
		System.out.println("time that you can cancel reservation within it is :" +this.getCancelationTime());
	}
}
