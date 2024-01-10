package org.example.asking;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class GptAsking {

    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);

        String extractedMessage = response.substring(start, end).replace("\\n", "\n");

        return extractedMessage;

    }

//    public static void main(String[] args) {
////        Config rollbarConfig =
////                ConfigBuilder.withAccessToken("baea24d1bd7e46bd9158b3752e1f2a37")
////
////                        .environment("production")
////                        .build();
////        Rollbar rollbar = new Rollbar(rollbarConfig);
////
////        try {
////            System.out.println(chatGPT("hello, how are you? Can you tell me what's a Fibonacci Number?"));
////        } catch (Exception e) {
////            rollbar.error(e);
////        } finally {
////            try {
////                rollbar.close(true);
////            } catch (Exception e) {
////                throw new RuntimeException(e);
////            }
////        }
////
//        System.out.println(chatGPT("hello, how are you? Can you tell me what's a Fibonacci Number?"));
//    }
}