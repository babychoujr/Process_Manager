import java.util.*;
import java.util.Iterator;
public class manager {
    process[] pcb;
    resources[] rcb;
    LinkedList<Integer>[] ready_list;

    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    String init(){
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
        
        return scheduler();
    }

    
    String create(int var1){
        //if priority number is wrong
        if(var1 <= 0 || var1 > 2){
            return Integer.toString(-1);
        }

        int index_empty = -1;
        for(int i = 0; i < 16; i++){
            if(pcb[i].used == 0){
                index_empty = i;
                break;
            }
        }
        //if there are no more space for processes return -1
        if(index_empty == -1){
            return Integer.toString(index_empty);
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

        return scheduler();
    }
    
    /*
    Destroy Function
    */
    String destroy(int j){
        //System.out.printf("In* %d:\n", j);
        
        //System.out.println("Children: " + pcb[j].children);
        
        if(j == 0){
            return Integer.toString(-1);
        }
        //If destroy tries to destroy a process that has not been created
        /*
        int index_used = 0;
        System.out.println("IM HEAVEN1");
        for(int i = 0; i < 16; i++){
            if(pcb[i].used == 1){
                index_used += 1;
            }
        }
        */
        //System.out.println("IM HEAVEN2");
        if(pcb[j].used != 1){
            return Integer.toString(-1);
        }
        //System.out.println("IM HEAVEN3");
        int running_process = 0;
        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() != 0){
                running_process = ready_list[i].getFirst();
                break;
            }
        }
        /*
        if(running_process == j){
            LinkedList<Integer> holder = pcb[running_process].children;
            int var = pcb[running_process].children.size();
            for(int i = 0; i < var; i++){
                destroy(holder.get(0));
            }
            
        }
        else{
            
            for(int i = 0; i < pcb[running_process].children.size(); i++){
                if(pcb[running_process].children.get(i) == j){
                    pcb[running_process].children.remove(i);
                }
            }
        }
        */
        //System.out.println("IM HEAVEN");
        if(running_process == j){
            LinkedList<Integer> holder = pcb[running_process].children;
            int var = pcb[running_process].children.size();
            for(int i = 0; i < var; i++){
                destroy(holder.get(0));
            }
        }
        else{
            //System.out.println("IM HERE");
            //LinkedList<Integer> h = pcb[j].children;
            int count = 0;
            while (pcb[j].children != null && pcb[j].children.size()!=0){
                //System.out.printf("In %d:\n", j); 
                //System.out.println("A " + pcb[j].children.get(0));
                //System.out.println("B " + pcb[j].children);
                destroy(pcb[j].children.get(0)); 
                if(count == 5){
                    break;
                }
                count++;
            }
            //if(pcb[j].children.size() == 0){
                //pcb[j].children.clear();
            //}
            //System.out.println("T: " + pcb[j].children.size());
            
                /*
            for(int i = 0; i < pcb[j].children.size(); i++){
                System.out.println("A " + pcb[j].children.get(0));
                System.out.println("B " + pcb[j].children);
                destroy(pcb[j].children.get(0));
            }
            */
            //System.out.println("C " + pcb[j].children);
            /*
            for(int i = 0; i < pcb[pcb[j].parent].children.size(); i++){
                if(pcb[pcb[j].parent].children.get(i) == j){
                    pcb[pcb[j].parent].children.remove(i);
                }
            }
            */
            
            int index = pcb[pcb[j].parent].children.indexOf(j);
            if(index >= 0){
                pcb[pcb[j].parent].children.remove(index);
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
        
        //Release all resources of j
        for(int i = 0; i < pcb[running_process].resources.size(); i++){
            if(pcb[running_process].resources.get(i) == j){
                pcb[running_process].resources.remove(i);
            }
        }

        //free PCB of j
        pcb[j] = new process();
        return scheduler();
    }
    

    /*
    Resource Managing Processes
    */
    
    String request(int r_index, int requested_units){
        

        if(r_index > 3){
            return Integer.toString(-1);
        }

        if(rcb[r_index].inventory < requested_units){
            return Integer.toString(-1);
        }

        int running_process = 0;
        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() !=  0){
                running_process = ready_list[i].getFirst();
                break;
            }
        }
        //process 0 cannot request resources or else deadlock
        if(running_process == 0){
            return Integer.toString(-1);
        }

        if(rcb[r_index].state >= requested_units){
            rcb[r_index].state -= requested_units;
            
            pcb[running_process].resources.add(r_index);
            return scheduler();
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
            return scheduler();
        }
    }
    
    
    String release(int r_index, int released_units){
        if(r_index > 3){
            return Integer.toString(-1);
        }

        int running_process = 0;
        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() !=  0){
                running_process = ready_list[i].getFirst();
                break;
            }
        }
        //process 0 cannot release resources units
        if(running_process == 0){
            return Integer.toString(-1);
        }
        int found = 0;
        for(int i = 0; i < pcb[running_process].resources.size(); i++){
            if(pcb[running_process].resources.get(i) == r_index){
                pcb[running_process].resources.remove(i);
                found = 1;
            }
        }
        //Could not release a block that is not a resource of the running process
        if(found == 0){
            return Integer.toString(-1);
        }

        if(rcb[r_index].inventory - rcb[r_index].state < released_units){
            return Integer.toString(-1);
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
        return scheduler();
    }
    
    /*
    Time out
     */
    
    String timeout(){
        int removed_process = 0;


        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() != 0){
                removed_process = ready_list[i].removeFirst();
                ready_list[i].addLast(removed_process);
                break;
            }
        }

        return scheduler();
    }
    
    
    String scheduler(){
        int running_process = 0;
        for(int i = ready_list.length - 1; i > -1; i--){
            if(ready_list[i].size() !=  0){
                running_process = ready_list[i].getFirst();
                break;
            }
        }
        //System.out.println("Process " + running_process + " running");
        return Integer.toString(running_process);
    }
    
}
