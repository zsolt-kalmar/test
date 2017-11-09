/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zsolt.jpmc.bookstore;

import java.util.HashMap;

public class Inventory extends HashMap<String, Book> {
    
    private double incomeAmount = 0;
    
    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double amount) {
        this.incomeAmount = amount;
    }

    public void changeIncomeAmount(double amount) {
        this.incomeAmount+= amount;
    }

    private double refundAmount = 0;
 
    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double amount) {
        this.refundAmount = amount;
    }
    
    public void changeRefundAmount(double amount) {
        this.refundAmount+= amount;
    }
    
    private int soldQuantity = 0;

    public int getSoldQuantity() {
        return soldQuantity;
    }
    
    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public void changeSoldeQuantity(int quantity) {
        this.soldQuantity+= quantity;
    }
    
    private int returnedQuantity = 0;

    public int getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(int returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public void changeReturnedQuantity(int quantity) {
        this.returnedQuantity+= quantity;
    }
    
    public double getNetIncome() {
        return getIncomeAmount() - getRefundAmount();
    }
}
