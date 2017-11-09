package com.zsolt.jpmc.bookstore;

public class TransactionBuy implements Transaction {

    @Override
    public String process(Inventory inventory, String... params) throws BadParameterException {
        if (params.length != 3) {
            throw new BadParameterException("Invalid numer of arguments. BUY must have 2 parameters: isbn, quantity.");
        }
        
        Book b = new Book();
        b.setISBN(params[1]);
        b.setQuantity(params[2]);
        
        Book book = inventory.get(b.getISBN());
        if (book == null) {
            return String.format("Book %s is not available.", b.getISBN());
        } else if (book.getQuantity() < b.getQuantity()) {
            return String.format("Insufficient quantity of %s.", b.getISBN());
        }
        
        book.decQuantity(b.getQuantity());
        double income = book.getPrice()* b.getQuantity();
        inventory.changeIncomeAmount(income);
        inventory.changeSoldeQuantity(b.getQuantity());
        
        return String.format("Sold %d %s of %s at £%.2f each. Total: £%.2f", 
                b.getQuantity(), 
                b.getQuantity() > 1 ? "copies" : "copy",
                b.getISBN(), 
                book.getPrice(), 
                income);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.BUY;
    }
    
}
