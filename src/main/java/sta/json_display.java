package sta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class json_display extends json_engine{

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode json_node;
    Path path  = Path.of("help.txt");



    //display warning
    public void displayWarning(String warning){
        System.out.println(warning);
    }

    // list all values 
    void display_allNodes() throws IOException{
        
        if(!isFileExists()) displayWarning("Json File Not exists");
        json_node = objectMapper.readTree(new File(json_fileName));

        for (JsonNode node : json_node){
            task(node);
        }

    } 

    public void helper() {
        System.out.println(path.toString());
        File help = new File(path.toString());
        for (int i = 0; i < 10; i++) System.out.print("=");
        System.out.print(" Help ");
        for (int i = 0; i < 10; i++) System.out.print("=");
        System.out.println();

        if (!help.exists()){
            System.out.println("Error Occuried help.txt doesn\'t exits");
            return;
        }

        try(Scanner file_reader = new Scanner(help)){
            while(file_reader.hasNextLine()){
                String data = file_reader.nextLine();
                System.out.println(data);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Error File Not Found");
        }
    
    }

    public void filter (String status) throws IOException{
        json_node = objectMapper.readTree(new File(json_fileName));
        
        for (JsonNode node : json_node){
            if (node.get("status").asText().equals(status)){
                task(node);
            }
        }
    }


    // tasks 
    private void task (JsonNode node){
        String id = node.get("id").asText();
        String description = node.get("description").asText();
        String status = node.get("status").asText();
        String createdAt = node.get("createdAt").asText();
        String updatedAt  = node.get("updatedAt").asText();
        for (int i = 0; i < 10; i++) System.out.print("=");
        System.out.print(" task ");
        for (int i = 0; i < 10; i++) System.out.print("=");
        System.out.println();
        System.out.println("id: "+id);
        System.out.println("description: "+description);
        System.out.println("status: "+status);
        System.out.println("createdAt: "+createdAt);
        System.out.println("updatedAt: "+updatedAt);
        for (int i = 0; i < 10; i++) System.out.print("=");
        String qoute = " \"The most effective way to do it, is to do it.\" — Amelia Earhart ";
        System.out.print(qoute);
        for (int i = 0; i < 10; i++) System.out.print("=");
        System.out.println();
    }
    
}
