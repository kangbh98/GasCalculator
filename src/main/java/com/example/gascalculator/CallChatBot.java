package com.example.gascalculator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class CallChatBot {


    public int callChatBot(String extractEtherApi, int changeTime){
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String url = "https://api.telegram.org/bot6647751450:AAF5kYoxZ4QTssExqXuAen0BHU04wb9Zevc/sendmessage?text=" + extractEtherApi + "ChangeTime: " + changeTime + "&chat_id=6567713476";
            String response = restTemplate.getForObject(url, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode userNode = jsonNode.get("result");
            int result = userNode.get("message_id").asInt();



            if (response != null) {
                System.out.println("메시지가 전송되었습니다." + java.time.LocalDateTime.now());
            } else {
                System.err.println("메시지 전송에 실패했습니다.");
            }

            return result;
        }catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return Integer.parseInt(null);
        }
    }

    public String stopChatBot(long lastUpdateId)  {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String url = "https://api.telegram.org/bot6647751450:AAF5kYoxZ4QTssExqXuAen0BHU04wb9Zevc/getUpdates";
            String response = restTemplate.getForObject( url, String.class);
            JsonNode jsonNode = objectMapper.readTree(response).get("result");

            for (JsonNode update : jsonNode) {

                JsonNode userNode = update.get("message");
                int updateId = userNode.get("message_id").asInt();

                if (updateId > lastUpdateId) {

                    JsonNode messageNode = update.get("message");

                    if (messageNode != null) {
                        String text = messageNode.get("text").asText();
                        if (text.equals("/stop")) {
                            return "/stop";
                        }
                        lastUpdateId = updateId;
                    }
                }
            }
            return null;

        }catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }


    }
}
