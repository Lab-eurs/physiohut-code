package com.example.physiohut;

public class Provision {

    private int id;
    private String name;
    private String prov_id;
    private String prov_price;
    private String date;
    private String description;
    private double price;

    public Provision(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Provision(String prov_id,String date,String name, String description, String prov_price){
        this.prov_id = prov_id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.prov_price = prov_price;
    }

    public Provision(String name,String date){
        this.name = name;
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public String getDate(){
        return date;
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }
}
