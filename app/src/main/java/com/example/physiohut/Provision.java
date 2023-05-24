package com.example.physiohut;

public class Provision {

    private int id;

    private String prov_id;
    private String prov_price;
    private String name;
    private String description;
    private double price;

    public Provision(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Provision(String prov_id, String name, String description, String prov_price){
        this.prov_id = prov_id;
        this.name = name;
        this.description = description;
        this.prov_price = prov_price;
    }

    public double getPrice() {
        return price;
    }

    public String getDate(){
        return "2022/05/23";
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }
}
