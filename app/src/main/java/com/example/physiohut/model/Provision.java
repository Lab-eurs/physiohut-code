package com.example.physiohut.model;

public class Provision {

    private int id;

    private String prov_id;
    private String prov_price;
    private String name;

    private String date;
    private String description;
    private double price;
    private boolean isSelected;

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

    public Provision(String name, boolean isSelected){
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getProv_id(){
        return prov_id;
    }
    public boolean isSelected(){ return isSelected; }
    public void setSelected(boolean selected){ isSelected = selected; }
    @Override
    public String toString(){
        return "Provision Class" + "name= "+ name + "isSelected= " + isSelected;
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
