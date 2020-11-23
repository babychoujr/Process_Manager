import java.io.*;
public class shell{
    //GLobal variables
    

    public static String initialize(manager m){
        return m.init();
    }
    
    public static String create(manager m, int var1){
        return m.create(var1);
    }
    

    public static String destroy(manager m, int var1){
        return m.destroy(var1);
    }
    
    
    public static String request(manager m, int var1, int var2){
        return m.request(var1, var2);
    }
    
    public static String release(manager m, int var1, int var2){
        return m.release(var1, var2);
    }

    
    public static String timeout(manager m){
        return m.timeout();
    }
    
    public static void main(String[] args)throws Exception{
        
        //|Read in the FILE|
        File file = new File("input.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String file_line;
        //Global Variable
        manager m = new manager();

        //Writer to output file
        FileWriter writer = new FileWriter("output.txt");

        //first in
        int first = 0;
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

            
            String running_process = "";
            if(command.equals("in")){
                if(first == 0){
                    running_process = initialize(m);
                    writer.write(running_process + " ");
                    first += 1;
                }
                else{
                    running_process = initialize(m);
                    writer.write("\n" + running_process + " ");
                }
            }
            else if(command.equals("cr")){
                running_process = create(m, var1);
                writer.write(running_process + " ");
            }
            else if(command.equals("de")){
                running_process = m.destroy(var1);
                writer.write(running_process + " ");
            }
            else if(command.equals("rq")){
                running_process = request(m, var1, var2);
                writer.write(running_process + " ");
            }
            else if(command.equals("rl")){
                running_process = release(m, var1, var2);
                writer.write(running_process + " ");
            }
            else if(command.equals("to")){
                running_process = timeout(m);
                writer.write(running_process + " ");
            }
        }
        
        br.close();
        writer.close();
    }
}