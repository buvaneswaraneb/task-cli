
package sta;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class json_engine {

    String json_fileName = "tasks.json";
    String jsonId_filename = "tasks_id.json";
    private final File file = new File(json_fileName);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ObjectNode json_node;

    public boolean isFileExists() {
        return file.exists();
    }

    private String getJsonId() throws IOException {
        File json_id = new File(jsonId_filename);
        String key = "";
        ObjectMapper obmap = new ObjectMapper();

        ObjectNode json_id_node;

        if (json_id.exists())
            json_id_node = (ObjectNode) obmap.readTree(json_id);
        else {
            json_id_node = obmap.createObjectNode();
            ArrayNode id_array = obmap.createArrayNode();
            id_array.add("0");
            json_id_node.set("id", id_array);
            ArrayNode deleted_keys = obmap.createArrayNode();
            json_id_node.set("deleted_key", deleted_keys);
            obmap.writeValue(json_id, json_id_node);
        }

        ArrayNode arr = (ArrayNode) json_id_node.get("id");
        for (JsonNode a : arr)
            key = a.asText();
        key = String.valueOf((key.charAt(0) - '0') + 1);
        arr.add(key);
        obmap.writeValue(json_id, json_id_node);
        return key;
    }

    private void addInnerJson(ObjectNode inner_node, String id, String description) {

        inner_node.put("id", id);
        inner_node.put("description", description);
        inner_node.put("status", "todo");

        LocalDateTime date = LocalDateTime.now();
        inner_node.put("createdAt", String.valueOf(date));
        inner_node.put("updatedAt", "");

    }

    public String create_json(String description) throws IOException {

        if (isFileExists())
            json_node = (ObjectNode) objectMapper.readTree(new File(json_fileName));
        else
            json_node = objectMapper.createObjectNode();

        // json object id
        String json_id = getJsonId();
        json_node.putObject(json_id);

        ObjectNode inner_node = (ObjectNode) json_node.get(json_id);

        addInnerJson(inner_node, json_id, description);

        // write to json
        objectMapper.writeValue(file, json_node);

        return json_id;
    }

    public void update_json_description(String id, String task) throws IOException {

        json_node = (ObjectNode) objectMapper.readTree(new File(json_fileName));

        ObjectNode update_json = (ObjectNode) json_node.get(id);
        update_json.put("description", task);

        LocalDateTime date = LocalDateTime.now();
        update_json.put("updatedAt", String.valueOf(date));

        objectMapper.writeValue(file, json_node);
    }

    public void update_json_status(String target_status, String change_status) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(new File(json_fileName));

        for (JsonNode node : jsonNode) {

            if (!node.get("id").asText().equals(target_status))
                continue;

            ((ObjectNode) node).put("status", change_status);
            LocalDateTime time = LocalDateTime.now();
            ((ObjectNode) node).put("updatedAt", String.valueOf(time));
            objectMapper.writeValue(new File(json_fileName), jsonNode);
        }

    }

    public void delete_json(String id) throws IOException {
        json_node = (ObjectNode) objectMapper.readTree(new File(json_fileName));
        JsonNode keys_node = objectMapper.readTree(new File(jsonId_filename));
        ArrayNode keys_array = (ArrayNode) keys_node.get("id");

        json_node.remove(id);
        objectMapper.writeValue(file, json_node);
    }

}