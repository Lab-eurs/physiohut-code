package com.example.physiohut;

public class NetworkConstants {
    public static String ip = "192.168.1.3";
    public static String baseUrl = "http://" + ip + "/physiohut-backend";

    public static String getUrlOfFile(String fileName){
        return baseUrl + "/" + fileName;
    }
}
