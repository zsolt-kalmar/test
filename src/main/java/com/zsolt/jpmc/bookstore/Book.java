package com.zsolt.jpmc.bookstore;
 
public class Book {
   
    private String isbn;

    public String getISBN() {
        return isbn;
    }
    
    public void setISBN(String isbn) throws BadParameterException {
        if (!isbn.matches("[\\d]{10}")) {
             throw new BadParameterException("Invalid isbn (incorrect formatting)");
        }
        
        this.isbn = isbn;
    }

    private int quantity = 0;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws BadParameterException {
        if (quantity < 0) {
            throw new BadParameterException("Invalid quantity (negative value)");
        }
        
        this.quantity = quantity;
    }

    public void setQuantity(String quantityString) throws BadParameterException {
        int q = 0;
        
        try {
            q = Integer.parseInt(quantityString);
        } catch (NumberFormatException exception) {
            throw new BadParameterException("Invalid quantity (unable to parse int)", exception);
        }

        if (q < 0) {
            throw new BadParameterException("Invalid quantity (negative value)");
        }
        
        this.quantity = q;
    }
    
    public void incQuantity(int amount) throws BadParameterException {
        setQuantity(quantity + amount);
    }

    public void decQuantity(int amount) throws BadParameterException {
        setQuantity(quantity - amount);
    }

    private double price = 0.0;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws BadParameterException {
        if (price < 0) {
            throw new BadParameterException("Invalid price (negative value)");
        }
        this.price = price;
    }
    
    public void setPrice(String priceString) throws BadParameterException {
        double p = 0;
        
        try {
            p = Double.parseDouble(priceString);
        } catch (NumberFormatException exception) {
            throw  new BadParameterException("Invalid price (unable to parse double)", exception);
        }
        
        if (p < 0) {
            throw  new BadParameterException("Invalid price (negative value)");
        }
        
        this.price = p;
    }
} 