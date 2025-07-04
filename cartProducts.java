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
public class cartProducts {
    products product;
    int quantity;

    public cartProducts(products product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
}

class cart{
    List<cartProducts> prods=new ArrayList<>();
    
    public void add(products product,int quantity){
        if(quantity<=0){
            System.out.println("Add more quantity ");
            System.out.println();
            return;
        }
        if(product.expired()){
            System.out.println("Expired product");
            System.out.println();
            return;
        }
        if (!product.Available(quantity)) {
            System.out.println("Not enough stock");
            System.out.println();
            return;
        }
        prods.add(new cartProducts(product,quantity));
        
    }
    public boolean isEmpty() {
        return prods.isEmpty();
    }

    public List<cartProducts> getProds() {
        return prods;
    }
    
    public void clear() {
        prods.clear();
    }
    
}
