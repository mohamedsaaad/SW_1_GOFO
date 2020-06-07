package gofo;
/**
 * <h1> Admin Class </h1>
 * <P>
 * class to control playground status 
 * <p>
 * @author mohamedsalem
 *  @since   2020-05-31
 */
public class Admin extends User {
    /**
     * function to activate playground 
     *playground 
     */
    public void activatePlayground(Playground playground) {
        if ("ACTIVATED".equals(playground.getPalygroundStatus().toString())) {
            System.out.println("=============== this play is already active ===============");
        }
        else {
            playground.setPalygroundStatus(PlaygroundStatus.ACTIVATED);
             System.out.println("=============== your operation is done successfully ! ===============");
        }
    }
    /**
     * function to delete playground 
     *playground 
     */
    public void deletePlayground(Playground playground) {
        if ("DELETED".equals(playground.getPalygroundStatus().toString())) {
            System.out.println("=============== this play is already deleted ===============");
        }else {
            playground.setPalygroundStatus(PlaygroundStatus.DELETED);
            System.out.println("=============== your operation is done successfully ! ===============");
        }  
    }
    
    /**
     * function to approve playground 
     *playground 
     */
    public void approvePlayground(Playground playground) {
        if ("APPROVED".equals(playground.getPalygroundStatus().toString())) {
            System.out.println("=============== this play is already approved ===============");
            
        }else {
            playground.setPalygroundStatus(PlaygroundStatus.APPROVED);
             System.out.println("=============== your operation is done successfully ! ===============");
        }       
    }
    /**
     * function to suspend playground 
     *playground 
     */
    public void suspendPlayground(Playground playground) {
        if ("SUSPENDED".equals(playground.getPalygroundStatus().toString())) {
            System.out.println("=============== this play is already SUSPENDED ===============");
        }else {
            playground.setPalygroundStatus(PlaygroundStatus.SUSPENDED);
             System.out.println("=============== your operation is done successfully ! ===============");
        } 
    }
}
