package com.zsolt.jpmc.bookstore;

public class TransactionQuery implements Transaction {

    @Override
    public String process(Inventory inventory, String... params) throws BadParameterException {
        if (params.length != 2) {
            throw new BadParameterException("Invalid numer of arguments (QUERY must have 1 parameters)");
        }
        
        Book b = new Book();
        b.setISBN(params[1]);
        
        Book book = inventory.get(b.getISBN());
        if (book == null) {
            return String.format("Book %s is not available.", b.getISBN());
        }

        return String.format("There %s %d %s of %s at £%.2f each. Total: £%.2f.", 
            book.getQuantity() > 1 ? "are" : "is", 
            book.getQuantity(),
            book.getQuantity() > 1 ? "copies" : "copy", 
            book.getISBN(),
            book.getPrice(),
            book.getQuantity() * book.getPrice());
    }
    
    @Override
    public TransactionType getType() {
        return TransactionType.QUERY;
    }
}
