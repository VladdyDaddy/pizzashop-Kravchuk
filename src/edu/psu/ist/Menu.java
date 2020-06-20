package edu.psu.ist;

import java.util.ArrayList;

public class Menu {

    //Class Level Variables - Protect the data
    private int menuId;
    private String menuItem;
    private float menuPrice;

    //Constructor Method
    public Menu(int _menuId, String _menuItem, float menuPrice){
        this.menuId = _menuId;
        this.menuItem = _menuItem;
        this.menuPrice = menuPrice;
    }

    //Setters and Getters
    public int getmenuId() { return menuId; }
    public void setmenuId(int _menuId) {this.menuId = _menuId;}

    public String getmenuItem() { return menuItem; }
    public void setmenuItem(String _menuItem) {this.menuItem = _menuItem;}

    public float getMenuPrice(){
        return menuPrice;
    }

    public static void listMenu(ArrayList<Menu> mList){
        for (Menu menu: mList){
            System.out.println(menu.getmenuId() + ": " + menu.getmenuItem() + " $" + menu.getMenuPrice());
        }
    }
    public String getDetails(){
        return "ID: " + getmenuId() + " Menu Item: " + getmenuItem() + " Price: $" + getMenuPrice() + " ";
    }
}
