/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.e.commerc;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mayada AbouZeid
 */
public class ECommerc {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        Date future = cal.getTime();

        products cheese = new EXPshippableProducts("Cheese", 100, 5, future, 0.2);
        products biscuits = new EXPshippableProducts("Biscuits", 150, 3, future, 0.7);
        products scratch = new nonEXP("Scratch Card", 50, 10);
        products TV = new nonEXP("TV", 500, 10);

        customer cus = new customer("Yusuf", 5000);
        cart cart = new cart();
        
        customer cus2 = new customer("M7md", 100);
        cart cartm = new cart();
        
        customer cus3 = new customer("kareem", 2000);
        cart cartk = new cart();
        
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(TV, 2);
        cart.add(scratch, 1);

        cartm.add(TV, 1);
        
        cartk.add(TV, 2);
        cartk.add(scratch, 10);
        
        Checkout.checkout(cus, cart);
        Checkout.checkout(cus2, cartm);
        Checkout.checkout(cus3, cartk);

    }
}
