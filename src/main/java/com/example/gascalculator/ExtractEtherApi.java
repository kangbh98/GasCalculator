package com.example.gascalculator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtractEtherApi {


    public String extractEtherApi(String ResponseBody) {
        // JSON 파싱
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(ResponseBody);
            // 원하는 필드 추출
            JsonNode userNode = jsonNode.get("result");
            String fieldValue = userNode.get("LastBlock").asText();
            int fieldValue1 = userNode.get("SafeGasPrice").asInt();
            String fieldValue2 =userNode.get("FastGasPrice").asText();

            String result = "LastBlock: " + fieldValue + "\n" +
                            "SafeGasPrice: " + fieldValue1 + "\n" +
                            "FastGasPrice: " + fieldValue2 + "\n";

            return result;

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    public int extractCurrentSafeGasPrice(String ResponseBody){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(ResponseBody);

            JsonNode userNode = jsonNode.get("result");
            int fieldValue1 = userNode.get("SafeGasPrice").asInt();

            return fieldValue1;

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return 0;
        }
    }
}
