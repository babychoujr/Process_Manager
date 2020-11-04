import java.util.*;
public class manager {
    process[] pcb;
    resources[] rcb;
    LinkedList<Integer>[] ready_list;

    manager(){
        pcb = new process[16];
        for(int i = 0; i < 16; i++){
            pcb[i] = new process();
        }

        pcb[0].used = 1;

        rcb = new resources[4];
        rcb[0] = new resources(1, 1);
        rcb[1] = new resources(1, 1);
        rcb[2] = new resources(2, 2);
        rcb[3] = new resources(3, 3);

        ready_list = new LinkedList[3];
        for(int i = 0; i < ready_list.length; i++){
            ready_list[i] = new LinkedList<Integer>();
        }
        
        ready_list[0].add(0);
    }

    void init(){
        /*
        Reset Process
        */
        pcb = new process[16];
        
        for(int i = 0; i < 16; i++){
            pcb[i] = new process();
        }
        
        pcb[0].used = 1;

        /*
        Reset resources
        */
        rcb = new resources[4];
        
        rcb[0] = new resources(1, 1);
        rcb[1] = new resources(1, 1);
        rcb[2] = new resources(2, 2);
        rcb[3] = new resources(3, 3);

        /*
        Reset ready_list
        */
        ready_list = new LinkedList[3];
        
        for(int i = 0; i < ready_list.length; i++){
            ready_list[i] = new LinkedList<Integer>();
        }
        
        ready_list[0] = new LinkedList<Integer>();
        ready_list[0].add(0);
        
        /*
        Print ready_list
        */
        /*
        for(int i = 0; i < ready_list.length; i++){
            System.out.println(i + " " + ready_list[i]);
        }
        */
        scheduler();
    }

    
    void create(int var1){
        int index_empty = 0;
        for(int i = 0; i < 16; i++){
            if(pcb[i].used == 0){
                index_empty = i;
                break;
            }
        }
        
        //allocate new PCB[j]
        pcb[index_empty] = new process();
        //store in the priority field of the new PCB
        pcb[index_empty].prio = var1;

        int running_process = 0;

        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() != 0){
                running_process = ready_list[i].getFirst();
                break;
            }
        }

        pcb[running_process].children.add(index_empty);

        pcb[index_empty].parent = running_process;
        
        //ready_list.add(index_empty);
        ready_list[var1].add(index_empty);
        
        pcb[index_empty].used = 1;
        //System.out.println("process " + index_empty + " created");
        /*
        for(int i = 0; i < pcb.length; i++){
            System.out.println(i + " " + pcb[i].used);
        }
        */
        scheduler();
    }
    
    /*
    Destroy Function
    */
    void destroy(int j){

        
        
        for(int i = 0; i < pcb[pcb[j].parent].children.size(); i++){
            if(pcb[pcb[j].parent].children.get(i) == j){
                pcb[pcb[j].parent].children.remove(i);
            }
        }

        //check the ready list for j
        int find = -1;
        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() != 0){
                for(int k = 0;  k < ready_list[i].size(); k++){
                    if(ready_list[i].get(k) == j){
                        ready_list[i].remove(k);
                        find = 0;
                    }
                }
            }
        }
        //check the waiting list if j isn't in ready list
        if(find != -1){
            for(int i = 0; i < rcb.length; i++){
                if(rcb[i].waitlist.size() != 0){
                    for(int k = 0; k < rcb[i].waitlist.size(); k++){
                        if(rcb[i].waitlist.get(k).getIndexOne() == j){
                            rcb[i].waitlist.remove(k);
                        }
                    }
                }
            }
        }

        //free PCB of j
        pcb[j] = new process();
        /*
        for(int i = 0; i < pcb.length; i++){
            System.out.println(i + " " + pcb[i].used);
        }
        */
        //System.out.println(j + " processes destroyed");
        /*
        for(Integer i: pcb[j].children){
            destroy(i);
        }
        
        //Remove j from parent's list of children
        if(pcb[pcb[j].parent].children != null){
            pcb[pcb[j].parent].children.remove(j);
        }
        //remove j from RL or waiting list
        ready_list.remove(j);

        //releases all resources of j
        for(Integer i: pcb[j].resources){
            release(i);
        }

        //free PCB of j
        pcb[j] = new process();
        
        //Print
        System.out.println(j + " processes destroyed");
        System.out.println(ready_list);
        */
        scheduler();

    }
    

    /*
    Resource Managing Processes
    */
    
    void request(int r_index, int requested_units){
        int running_process = 0;
        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() !=  0){
                running_process = ready_list[i].getFirst();
                break;
            }
        }
        if(rcb[r_index].state >= requested_units){
            rcb[r_index].state -= requested_units;
            
            pcb[running_process].resources.add(r_index);
            scheduler();
            //System.out.println("Resource " + r_index + " allocated");
        }
        else{
            pcb[running_process].state = 0;
            int removed_process = 0;
            for(int i = ready_list.length - 1; i > -1; i--){
                if(ready_list[i].size() != 0){
                    removed_process = ready_list[i].removeFirst();
                    break;
                }
            }
            pair p = new pair(removed_process, requested_units);
            rcb[r_index].waitlist.add(p);

            //System.out.println("Process " + removed_process + " blocked");
            scheduler();
        }
    }
    
    
    void release(int r_index, int released_units){
        int running_process = 0;
        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() !=  0){
                running_process = ready_list[i].getFirst();
                break;
            }
        }

        for(int i = 0; i < pcb[running_process].resources.size(); i++){
            if(pcb[running_process].resources.get(i) == running_process){
                pcb[running_process].resources.remove(i);
            }
        }

        if(rcb[r_index].waitlist.size() == 0){
            rcb[r_index].state += released_units;
        }
        else{       
            while(released_units != 0 && rcb[r_index].waitlist.size() != 0){
                int process_j  = rcb[r_index].waitlist.get(0).getIndexOne();
                for(int i = ready_list.length - 1; i > -1; i--){
                    if(i == pcb[process_j].prio){
                        ready_list[i].addLast(process_j);
                        break;
                    }
                }
                pcb[process_j].state = 1;
                pcb[process_j].resources.add(r_index);
                released_units -= rcb[r_index].waitlist.get(0).getIndexTwo();

                rcb[r_index].waitlist.removeFirst();
            }
            //System.out.println("Resource "  + r_index + " released");
            
        }
        scheduler();
    }
    
    /*
    Time out
     */
    
    void timeout(){
        int removed_process = 0;


        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() != 0){
                removed_process = ready_list[i].removeFirst();
                ready_list[i].addLast(removed_process);
                break;
            }
        }
        scheduler();
    }
    
    
    void scheduler(){
        int running_process = 0;
        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() !=  0){
                running_process = ready_list[i].getFirst();
                break;
            }
        }
        System.out.println("Process " + running_process + " running");
    }
    
}
