package sta;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static void main(String[] args)throws IOException{
        json_engine engine = new json_engine();
        boolean loop = true;
        Scanner s = new Scanner(System.in);
        cli_interface CI = new cli_interface();
        System.out.println("\nWelcome to task manager cli\n");
        String cmd;
        while (loop){
            System.out.print(">> ");
            cmd = s.nextLine();
            String[] cmds = cmd.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            if (cmd.equals("^[") || cmd.equals("q"))
                 return;
            System.out.println(CI.operations(cmds));
            System.out.println();

        } 
    }
}
