import java.util.*;
public class resources {
    int state;
    int inventory;
    Queue<Integer> waitlist;

    resources(){
        state = 0;
        inventory = 0;
        waitlist = new LinkedList<>();
    }
    resources(int s, int inv, Queue<Integer> wl){
        state = s;
        inventory = inv;
        waitlist = new LinkedList<>();

        for(Integer i: wl){
            waitlist.add(i);
        }
    }

    int getState(){
        return this.state;
    }

    int getInventory(){
        return this.inventory;
    }

    void printWaitList(){
        System.out.println(waitlist);
    }
}
