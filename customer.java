/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mayada AbouZeid
 */
public class customer {
    String name;
    double balance;

    public customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    public void reduceBalance(double amount) {
        balance -= amount;
    }
}
class Checkout{
    static final double shipfees = 30.0;
   
    public static void checkout(customer cus,cart car){
        if(car.isEmpty()){
            System.out.println("Cart is empty");
            System.out.println();
            return;
        }
        double subtotal=0;
        double totalweight=0;
        List<shippable> ship=new ArrayList<>();
        
        for (cartProducts item : car.getProds()) {
            products product = item.product;
            int q = item.quantity;
        
        if (product.expired()) {
                System.out.println(product.getName() + " is expired");
                System.out.println();
                return;
            }
        if (!product.Available(q)) {
                System.out.println(product.getName() + " is out of stock");
                System.out.println();
                return;
            }
        subtotal += product.getPrice() * q;
        
        if (product instanceof shippable) {
                for (int i = 0; i < q; i++) {
                    ship.add((shippable) product);
                    totalweight += ((shippable) product).getWeight();
                }
            }
        }
        double shipping = totalweight * shipfees;
        double total = subtotal + shipping;
        
        if (cus.getBalance() < total) {
            System.out.println("Insufficient balance "+cus.name);
            System.out.println();
            return;
        }
         if (!ship.isEmpty()) {
            ShippingService.ship(car.getProds());
        }
        for (cartProducts item : car.getProds()) {
            item.product.reduceQuantity(item.quantity);
        }
        cus.reduceBalance(total);
        
        
        System.out.println("** Checkout receipt **");
        for (cartProducts item : car.getProds()) {
            double lineTotal = item.quantity * item.product.getPrice();
            System.out.printf("%dx %s %.0f\n", item.quantity, item.product.getName(), lineTotal);
        }
        System.out.println("----------------------");
        System.out.printf("Shipping %.0f\n", shipping);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Current balance %.0f\n", cus.getBalance());
        System.out.println();
        car.getProds().clear();
     }
}
