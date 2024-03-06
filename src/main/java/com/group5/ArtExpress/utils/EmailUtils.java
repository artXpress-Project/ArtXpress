package com.group5.ArtExpress.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token){
        return "Hello " + name + ",\n\nYour new account has been created. Please Click the link below to verify your account. \n\n" +
                getVerificationUrl(host,token) + "'\n\n' support Team";
    }

    public static String getVerificationUrl(String host, String token){
        return host + "/api/v1/artist?token=" + token;
    }

    public static String getEmailMessageCollector(String name, String host, String token){
        return "Hello " + name + ",\n\nYour new account has been created. Please Click the link below to verify your account. \n\n" +
                getVerificationCollectorUrl(host,token) + "'\n\n' support Team";
    }

    public static String getVerificationCollectorUrl(String host, String token){
        return host + "/api/v1/collector?token=" + token;
    }
    public static String getEmailMessageExhibition(String name, String host, String token){
        return "Hello " + name + ",\n\nYour new account has been created, We are so glad to have you as our Attendee for the upcoming ArtXpress Event. \n Please Click the link below to verify your account. \n\n" +
                getVerificationExhibitionUrl(host,token) + "'\n\n' support Team";
    }


    public static String getVerificationExhibitionUrl(String host, String token){
        return host + "/api/v1/exhibition?token=" + token;
    }
}
