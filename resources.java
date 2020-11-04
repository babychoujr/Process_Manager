import java.util.*;
public class resources {
    int state;
    int inventory;
    LinkedList<pair> waitlist;

    resources(int s, int inv){
        state = s;
        inventory = inv;
        waitlist = new LinkedList<pair>();

    }

    int getState(){
        return this.state;
    }

    int getInventory(){
        return this.inventory;
    }

}
