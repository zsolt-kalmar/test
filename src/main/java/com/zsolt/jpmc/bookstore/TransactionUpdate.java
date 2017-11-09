package com.zsolt.jpmc.bookstore;

public class TransactionUpdate implements Transaction {

    @Override
    public String process(Inventory inventory, String... params) throws BadParameterException {
        if (params.length != 3) {
            throw new BadParameterException("Invalid numer of arguments. UPDATE must have 2 parameters: isbn, price.");
        }
        
        Book b = new Book();
        b.setISBN(params[1]);
        b.setPrice(params[2]);
        
        Book book = inventory.get(b.getISBN());
        if (book == null) {
            return String.format("Book %s is not available.", b.getISBN());
        } 
        
        book.setPrice(b.getPrice());
        
        return String.format("New price for %s at £%.2f each.", book.getISBN(), book.getPrice());
    }

    @Override
    public TransactionType getType() {
        return TransactionType.UPDATE;
    }
    
}
