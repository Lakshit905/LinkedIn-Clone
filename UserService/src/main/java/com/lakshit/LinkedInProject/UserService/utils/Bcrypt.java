package com.lakshit.LinkedInProject.UserService.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {

    public static String hash(String s){
        return BCrypt.hashpw(s,BCrypt.gensalt());
    }

    public static Boolean match(String passwordPlain, String hashedPassword){
        return BCrypt.checkpw(passwordPlain,hashedPassword);
    }
}
