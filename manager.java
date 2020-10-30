import java.util.*;
public class manager {
    process[] pcb;
    resources[] rcb;

    manager(){
        pcb = new process[16];
        for(int i = 0; i < 16; i++){
            pcb[i] = new process();
        }

        rcb = new resources[4];
    }

    void init(){
        pcb = new process[16];
        for(int i = 0; i < 16; i++){
            pcb[i] = new process();
        }
    }
    


}
