import java.util.*;

public class process {
    int state;
    int parent;
    int used;
    int prio;
    LinkedList<Integer> children;
    LinkedList<Integer> resources;

    process(){
        state = 1;
        parent = 0;
        used = 0;
        prio = 0;
        children = new LinkedList<>();
        resources = new LinkedList<>();
    }

    process(int s, int p, int u, int pr, LinkedList<Integer> c, LinkedList<Integer> r){
        state = s;
        parent = p;
        used = u;
        prio = pr;
        children = new LinkedList<>();
        resources = new LinkedList<>();
        
        for(Integer i : c){
            children.add(i);
        }

        for(Integer i: r){
            resources.add(i);
        }
    }

    void printAll(){
        System.out.println("State: " + this.state);
        System.out.println("Parent: " + this.parent);
        System.out.println("Used: " + this.used);
        System.out.println("Priority: " + this.prio);
        System.out.println("Children: " + this.children);
        System.out.println("Resources: " + this.resources);
    }

}
