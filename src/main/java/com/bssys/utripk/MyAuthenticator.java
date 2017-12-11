package com.bssys.utripk;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Created by dormv on 07.12.2017.
 */
public class MyAuthenticator extends Authenticator {

    protected PasswordAuthentication getPasswordAuthentication() {
        String username = "teamcity";
        String password = "teamcity";

        return new PasswordAuthentication(username, password.toCharArray());
    }
}
