import java.util.*;
import java.io.*;
public class main{
    //GLobal variables
    

    public static void initialize(manager m){
        m.init();
    }
    
    public static void create(manager m, int var1){
        m.create(var1);
    }
    

    public static void destroy(manager m, int var1){
        m.destroy(var1);
    }
    
    
    public static void request(manager m, int var1, int var2){
        m.request(var1, var2);
    }
    
    public static void release(manager m, int var1, int var2){
        m.release(var1, var2);
    }

    
    public static void timeout(manager m){
        m.timeout();
    }
    
    public static void main(String[] args)throws Exception{
        
        //|Read in the FILE|
        File file = new File("D:\\UCI\\github_projects\\Process_Manager\\test.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String file_line;
        //Global Variable
        manager m = new manager();

        while( (file_line = br.readLine()) != null){
            String split_line[] = file_line.split(" ");
            
            String command = "";
            int var1 = 0;
            int var2 = 0;
            for(int i = 0; i < split_line.length; i++){
                if(i == 0){
                    command = split_line[i];
                }
                if(i == 1){
                    var1 = Integer.parseInt(split_line[i]);
                }
                if(i == 2){
                    var2 = Integer.parseInt(split_line[i]);
                }
            }


            if(command.equals("in")){
                initialize(m);
            }
            else if(command.equals("cr")){
                create(m, var1);
            }
            else if(command.equals("de")){
                destroy(m, var1);
            }
            else if(command.equals("rq")){
                request(m, var1, var2);
            }
            else if(command.equals("rl")){
                release(m, var1, var2);
            }
            else if(command.equals("to")){
                timeout(m);
            }
            /*
            else if(command.equals("de")){
                destroy(m, var1);
            }
            else if(command.equals("rq")){
                request(m, var1);
            }
            else if(command.equals("rl")){
                release(m, var1);
            }
            else if(command.equals("to")){
                timeout(m);
            }
            */

        }
        
        br.close();

        //Test Process class
        /*
        Queue<Integer> c = new LinkedList<>();
        c.add(1);
        c.add(3);

        Queue<Integer> r = new LinkedList<>();
        r.add(5);
        r.add(7);
        
        process p = new process(1, 2, c, r);
        //process p = new process();

        System.out.println("State: " + p.getState());
        System.out.println("Parent: " + p.getParent());
        p.printChildren();
        p.printResources();
        */


    }
}