package sta;

import java.io.IOException;

public class Main {
    static void main(String[] args)throws IOException{

        json_engine engine = new json_engine();
        //engine.create_json( "test");
        //engine.update_json(1, "test + result+ delete");
        //engine.delete_json(1);
        json_display display = new json_display();
        //display.display_allNodes();
        display.filter("TODO");
        
    }
}
