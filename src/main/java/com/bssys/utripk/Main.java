package com.bssys.utripk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by dormv on 07.12.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        Authenticator.setDefault(new MyAuthenticator());

        URL url = new URL("http://teamcity/httpAuth/app/rest/builds/id:" + args[0]+ "/status");

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder text = new StringBuilder();

        String inputLine;
        String needLine = "FAILURE";
        while ((inputLine = in.readLine()) != null) {
            text.append(inputLine);
        }
        in.close();

        if (text.toString().equals(needLine)) {
            sendMessage("Teamcity. Сборка "+ args[1] +" упала! Проверьте!");
        }
    }
    public static void sendMessage(String s) throws Exception {
        URL tel = new URL(String.format("https://api.telegram.org/botTOKEN" +
                "/sendMessage?chat_id=<chat_id>&text=%s", URLEncoder.encode(s, "UTF-8")));
        URLConnection conn = tel.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();

        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        response.toString();
        br.close();
    }
}