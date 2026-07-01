package sta;

import java.io.IOException;
import java.util.Arrays;

public class cli_interface {
    json_display jsn_display ;
    json_engine jsn_engine ;

    public cli_interface() {
        this.jsn_display = new json_display();
        this.jsn_engine = new json_engine();
    }

    String[] allowed = {"add","update","delete","mark-in-progress","mark-done","list"};

    public String operations(String[] args) throws IOException{
        if(!(args[0].equals("task-cli") || args[0].equals("?help") )) return "\n\nIncorrect tool calling: did you mean \" task-cli \" ? ";
         
        if (args[0].equals("?help")) {
            jsn_display.helper();
            return  "OK";
        }

        if (args.length <= 1) return "\n\ntask-cli arguments missing.\nTo get started \n\t \" task-cli add <task description> \"";
        if(!Arrays.stream(allowed).anyMatch(args[1]::equals)) return "\n\nOperation Invalid please enter a valid operation";
        
        if (args[1].equals("add")){ 
            if ((args.length != 3)) return "\n\nMissing Task description\n\t\"task-cli add <task description>\"";
            add(args[2]);
        }

        else if (args[1].equals("update")) {

                if ((args.length < 4)){
                    try
                    {
                        if (args[2].length() > 1){
                            return "\n\nIncorrect TaskID \n \t \" task-cli update <task ID> <task description> \" ";
                        }
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        return "\n\nMissing Task ID  \n \t \" task-cli update <task ID> <task description> \" ";
                    }
                    try
                    {
                        args[3].length();
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        return "\n\nMissing Task description \n \t \" task-cli update <task ID> <task description> \" ";
                    }
                }
                else 
                    update(args[2], args[3]);
            }


        else if (args[1].equals("delete")) delete(args[2]);
        else if (args[1].equals("mark-in-progress")) jsn_engine.update_json_status(args[2], "in-progress");
        else if (args[1].equals("mark-done")) jsn_engine.update_json_status(args[2], "done");

        else if (args[1].equals("list")){
            if (args.length != 3 ){
                 jsn_display.display_allNodes();
                 return "OK";
            }
            else if (args.length == 3 && args[2].equals("todo")){
                jsn_display.filter("todo");
            }
            else if (args.length == 3 && args[2].equals("done")){
                jsn_display.filter("done");
            }
            else if (args.length == 3 && args[2].equals("in-progress")){
                jsn_display.filter("in-progress");
            }
            else 
                return "Invalid list command, accepted commands {todo , done ,in-progress}";
        }

        return "OK";
    }

    private void add(String tasks) throws IOException{

        try {
            String id = jsn_engine.create_json(tasks);
            System.out.printf("Task is sucessfully added id : %s \n",id);

        } catch (Exception e) {
            jsn_display.displayWarning(String.valueOf(e));
        }
    }

    // updates the task description 
    private void update(String id, String task){
        try {
            jsn_engine.update_json_description(id, task);
            System.out.println("Task is updated sucessfully");
        } catch (Exception e) {
            jsn_display.displayWarning(String.valueOf(e));
        }
    }
    private void delete(String id){
        try {
            jsn_engine.delete_json(id);
            System.out.println("Task is sucessfully deleted");
        } catch (Exception e) {
            jsn_display.displayWarning(String.valueOf(e));
        }
    }
    
}
