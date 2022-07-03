package org.example.http;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequestTest {

    @Test
    void should_GetRequest_Not_Null_With_Builder() {
        GetRequest getRequest = Request.get("https://www.google.com/");

        assertNotNull(getRequest);
    }

//    @Test
//    void should_Executable_GetRequest() {
//        ResponseRequest.get("https://www.google.com/")
//                .execute();
//    }

    @Test
    void test() throws IOException {
        URL url = new URL("https://www.google.com/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream inputStream = (connection.getInputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(connection.getResponseCode());
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        System.out.println(stringBuilder.toString());
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        connection.disconnect();
    }
}