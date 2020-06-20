package edu.psu.ist;
/*
Project: Lab 9
Purpose Details: Pizza ordering application
Course: IST 242
Author: Joe Oakes
Date Developed: 3/14/19
Last Date Changed: 3/13/19
Rev: 2
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    int cCount = 1;
    int oCount = 1;
    public static void main(String[] args) {

        Main main = new Main();

        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        final char HELP_CODE = '?';
        char userAction;
        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();

        Menu menu1 = new Menu(1, "Plain", 5.00F);
        Menu menu2 = new Menu(2, "Meat", 6.00F);
        Menu menu3 = new Menu(3, "Extra", 6.50F);
        Menu menu4 = new Menu(4, "Veg", 4.50F);

        mList.add(menu1);
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);

        Customer cust1 = new Customer(1);
        cust1.setCustomerName("Vlad");
        cust1.setCustomerPhoneNumber("1231231234");
        cList.add(cust1);

        Customer cust2 = new Customer(2);
        cust2.setCustomerName("John");
        cust2.setCustomerPhoneNumber("1231231234");
        cList.add(cust2);

        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {
            switch(userAction) {
                case CUST_CODE : cList.add(main.addCustomer());
                    break;
                case CUST_PRNT : Customer.printCustomer(cList);
                    break;
                case MENU_CODE : Menu.listMenu(mList);
                    break;
                case ORDE_CODE : oList.add(main.addOrders(cList, oList, mList, tList));
                    break;
                case TRAN_CODE : Transaction.listTransactions(tList);
                    break;
            }

            userAction = getAction(PROMPT_ACTION);
        }
    }

    public static char getAction(String prompt) {
        Scanner scnr = new Scanner(System.in);
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    public Customer addCustomer(){
        Customer cust = new Customer(cCount++);
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please Enter your Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter your Phone: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());
        return cust;
    }
    public Order addOrders(ArrayList<Customer> cList, ArrayList<Order> oList, ArrayList<Menu> mList, ArrayList<Transaction> tList){
        Order ord = new Order(oCount++);
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Customer Name: ");
        String c = scnr.nextLine();
        int trigger = 0;
        for (Customer cust : cList){
            if (cust.getCustomerName().equals(c)){
                trigger = 1;
                ord.setCustomer(cust);
            }
        }
        if (trigger == 1){
            System.out.println("Customer is Valid");
            int a = 1;
            float totalCount = 0.00f;
            ArrayList<Menu> orderItems = new ArrayList<>();
            while(a==1){
                System.out.println("Add item to order by menu number:");
                Menu.listMenu(mList);
                int z = Integer.parseInt(scnr.nextLine());
                orderItems.add(mList.get(z-1));
                totalCount += mList.get(z-1).getMenuPrice();
                System.out.println("Add more items to order? Press a");
                System.out.println("Finished order? Press q");
                if (scnr.nextLine().equals("a")){
                    continue;
                }
                else{
                    ord.setMenuItem(orderItems);
                    ord.setTotalPrice(totalCount);
                    System.out.println("Payment Type? '1'Cash or '2'Credit?");
                    if (scnr.nextLine().equals("1")){
                        Transaction trans = new Transaction(ord.getorderId(), ord, PaymentType.cash);
                        tList.add(trans);
                    }
                    else{
                        Transaction trans = new Transaction(ord.getorderId(), ord, PaymentType.credit);
                        tList.add(trans);
                    }
                    break;
                }
            }
        }
        else{
            System.out.println("Customer is not Valid, add customer first");
        }
        return ord;
    }
}
