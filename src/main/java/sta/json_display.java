package sta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class json_display extends json_engine{

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode json_node;

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
        for (int i = 0; i < 10; i++) System.out.print("=");
        System.out.print(" Help ");
        for (int i = 0; i < 10; i++) System.out.print("=");
        System.out.println();

        InputStream helpStream = getClass().getClassLoader().getResourceAsStream("help.txt");
        if (helpStream == null) {
            File fallbackHelp = new File("src/main/resources/help.txt");
            if (!fallbackHelp.exists()) {
                System.out.println("Error Occuried help.txt doesn't exits");
                return;
            }
            try (Scanner file_reader = new Scanner(fallbackHelp)) {
                while (file_reader.hasNextLine()) {
                    String data = file_reader.nextLine();
                    System.out.println(data);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error File Not Found");
            }
            return;
        }

        try (Scanner file_reader = new Scanner(helpStream)) {
            while (file_reader.hasNextLine()) {
                String data = file_reader.nextLine();
                System.out.println(data);
            }
        } catch (Exception e) {
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

    private String get_quoute(){
        String[] quotes = {
                "\"The most effective way to do it, is to do it.\" — Amelia Earhart",
                "\"The secret of getting ahead is getting started.\" — Mark Twain",
                "\"Small progress is still progress.\"",
                "\"Done is better than perfect.\"",
                "\"One task at a time. One step at a time.\"",
                "\"Discipline beats motivation.\"",
                "\"Today's efforts build tomorrow's success.\"",
                "\"Start where you are. Use what you have. Do what you can.\" — Arthur Ashe",
                "\"Success is the sum of small efforts repeated day in and day out.\" — Robert Collier",
                "\"Don't watch the clock; do what it does. Keep going.\" — Sam Levenson",
                "\"Dream big. Start small. Act now.\"",
                "\"Every completed task is a promise kept to yourself.\"",
                "\"Your future self will thank you.\"",
                "\"Focus on progress, not perfection.\"",
                "\"A little progress each day adds up to big results.\"",
                "\"The journey of a thousand miles begins with one step.\" — Lao Tzu",
                "\"Success doesn't come from what you do occasionally, it comes from what you do consistently.\"",
                "\"The best time to start was yesterday. The next best time is now.\"",
                "\"You don't have to be great to start, but you have to start to be great.\" — Zig Ziglar",
                "\"Action is the foundational key to all success.\" — Pablo Picasso",
                "\"Make today count.\"",
                "\"Every checkmark is a step closer to your goals.\"",
                "\"Stay focused and never give up.\"",
                "\"Your only limit is your willingness to begin.\"",
                "\"Progress over excuses.\"",
                "\"Finish what you started.\"",
                "\"Consistency creates results.\"",
                "\"Keep moving forward, no matter how small the step.\"",
                "\"Success starts with showing up.\"",
                "\"The hardest part is starting.\"",
                "\"Turn your plans into progress.\"",
                "\"Every task completed is a victory.\"",
                "\"One productive day can change your week.\"",
                "\"Momentum begins with a single action.\"",
                "\"Be proud of every task you complete.\"",
                "\"Great things are built one task at a time.\"",
                "\"Your goals deserve your effort.\"",
                "\"Don't wait for motivation. Create it.\"",
                "\"Stay organized. Stay unstoppable.\"",
                "\"Keep going. You're closer than you think.\""
        };

        Random rand = new Random();
        return quotes[rand.nextInt(quotes.length)];

    }


    // tasks 
    private void task (JsonNode node){
        String id = node.get("id").asText();
        String description = node.get("description").asText();
        String status = node.get("status").asText();
        String createdAt = node.get("createdAt").asText();
        String updatedAt  = node.get("updatedAt").asText();
        System.out.println();
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
        String qoute = get_quoute();
        System.out.printf(" %s ",qoute);
        for (int i = 0; i < 10; i++) System.out.print("=");
        System.out.println();
    }
    
}
