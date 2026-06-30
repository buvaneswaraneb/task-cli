
package sta;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class json_engine{

    String json_fileName = "tasks.json";
    String jsonId_filename = "tasks_id.json";
    private final File file = new File(json_fileName);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ObjectNode json_node;


    protected  boolean isFileExists(){
        return file.exists();
    }

    private String getJsonId() throws IOException{
        File json_id = new File(jsonId_filename);
        ObjectMapper obmap = new ObjectMapper();
    
        ObjectNode json_id_node;

        if (json_id.exists()) 
            json_id_node = (ObjectNode) obmap.readTree(json_id);
        else {
            json_id_node = obmap.createObjectNode();
            ArrayNode id_array = obmap.createArrayNode();
        }

        return String.valueOf("1");
    }

    private void addInnerJson(ObjectNode inner_node , String id , String description){

        inner_node.put("id",id);
        inner_node.put("description",description);
        inner_node.put("status","TODO");

        LocalDateTime date = LocalDateTime.now();
        inner_node.put("createdAt", String.valueOf(date));
        inner_node.put("updatedAt","");
        
    }

    public void create_json(String description) throws IOException{

        if (isFileExists()) 
            json_node = (ObjectNode) objectMapper.readTree(new File(json_fileName));
        else 
            json_node = objectMapper.createObjectNode();

        // json object id 
        String json_id = getJsonId();
        json_node.putObject(json_id);

        ObjectNode inner_node = (ObjectNode) json_node.get(json_id);

        addInnerJson(inner_node,json_id, description);

        // write to json 
        objectMapper.writeValue(file,json_node);
    } 


    public void update_json_description(int id , String task) throws IOException{

        json_node = (ObjectNode) objectMapper.readTree(new File(json_fileName));

        ObjectNode update_json = (ObjectNode) json_node.get(String.valueOf(id));
        update_json.put("description",task);

        LocalDateTime date = LocalDateTime.now();
        update_json.put("updatedAt",String.valueOf(date));

        objectMapper.writeValue(file , json_node);
    }

    public 

    public void delete_json(int id) throws IOException{
        json_node = (ObjectNode) objectMapper.readTree(new File(json_fileName));
        json_node.remove("1");
        objectMapper.writeValue(file, json_node);
    }



}