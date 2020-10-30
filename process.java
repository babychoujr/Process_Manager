import java.util.*;

public class process {
    int state;
    int parent;
    Queue<Integer> children;
    Queue<Integer> resources;

    process(){
        state = 0;
        parent = 0;
        children = new LinkedList<>();
        resources = new LinkedList<>();
    }

    process(int s, int p, Queue<Integer> c, Queue<Integer> r){
        state = s;
        parent = p;
        children = new LinkedList<>();
        resources = new LinkedList<>();
        
        for(Integer i : c){
            children.add(i);
        }

        for(Integer i: r){
            resources.add(i);
        }
    }

    int getState(){
        return this.state;
    }

    int getParent(){
        return this.parent;
    }

    void printChildren(){
        System.out.println(this.children);
    }

    void printResources(){
        System.out.println(this.resources);
    }


}
