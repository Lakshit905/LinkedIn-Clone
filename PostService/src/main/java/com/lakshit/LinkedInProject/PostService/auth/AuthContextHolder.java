package com.lakshit.LinkedInProject.PostService.auth;

public class AuthContextHolder {

    private static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    public static Long getUserId(){
        return currentUserId.get();
    }

    static void setUserId(Long userId){
        currentUserId.set(userId);
    }

    static void clear(){
        currentUserId.remove();
    }

}
