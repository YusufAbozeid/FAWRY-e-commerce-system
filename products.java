/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerc;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mayada AbouZeid
 */
abstract class products {
    protected String name;
    protected double price;
    protected int  quantity;

    public products(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public boolean Available(int quantity) {
        return this.quantity >= quantity;
    }
    
    public void reduceQuantity(int quantity) {
    this.quantity -= quantity;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    public abstract boolean expired();
       
    
    
    
}
class nonEXP extends products{
    
    public nonEXP(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean expired() {
        return false;
    }
    
}
class EXP extends products{
    private Date expireDATE;
    public EXP(String name, double price, int quantity,Date expireDATE) {
        super(name, price, quantity);
        this.expireDATE=expireDATE;
    }

    @Override
    public boolean expired() {
        return new Date().after(expireDATE);
    }
    
    
}
interface shippable {
    String getName();
    double getWeight();
}
class shippableProducts extends products implements shippable{
    double weight;

    public shippableProducts(String name, double price,int quantity,double weight) {
        super(name, price, quantity);
        this.weight=weight;
    }

    @Override
    public boolean expired() {
        return false;
    }

    @Override
    public double getWeight() {
        return weight;
    }
   
}
class EXPshippableProducts extends EXP implements shippable{
    double weight;

    public EXPshippableProducts(String name, double price, int quantity, Date expireDATE,double weight) {
        super(name, price, quantity, expireDATE);
        this.weight=weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    
}
class ShippingService {
    public static void ship(List<cartProducts> cartItems) {
        System.out.println("** Shipment notice **");

        double totalWeight = 0;

        for (cartProducts item : cartItems) {
            products product = item.product;
            int quantity = item.quantity;

            if (product instanceof shippable) {
                double weight = ((shippable) product).getWeight();
                int totalGrams = (int) (weight * 1000 * quantity);
                System.out.printf("%dx %s %dg\n", quantity, product.getName(), totalGrams);
                totalWeight += weight * quantity;
            }
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight);
        System.out.println();
    }
}


