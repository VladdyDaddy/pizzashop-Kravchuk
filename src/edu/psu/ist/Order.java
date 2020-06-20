package edu.psu.ist;

import java.util.ArrayList;

public class Order {
    //Class Level Variables - Protect the data
    private int orderId;
    private Customer cust;
    private ArrayList<Menu> menuItem;
    private float totalPrice;

    //Constructor Method
    public Order(int _orderId){
        this.orderId = _orderId;
    }

    //Setters and Getters
    public int getorderId() { return orderId; }
    public void setorderId(int _orderId) {this.orderId = _orderId;}

    public void setCustomer(Customer cust){
        this.cust = cust;
    }
    public void setMenuItem(ArrayList<Menu> menuItem){
        this.menuItem = menuItem;
    }
    public void setTotalPrice(float totalPrice){
        this.totalPrice = totalPrice;
    }

    public String getCustomer(){
        return cust.getDetails();
    }
    public String getMenuItem(){
        StringBuilder a = new StringBuilder();
        for (Menu i : menuItem) {
            a.append(i.getDetails());
        }
        return a.toString();
    }
    public float getTotalPrice(){
        return totalPrice;
    }

    public String getDetails(){
        return "Order ID: " + getorderId() + "| Customer: " + getCustomer() + "| Menu Items: " + getMenuItem() + "| Total Price: $" + getTotalPrice();
    }


}
