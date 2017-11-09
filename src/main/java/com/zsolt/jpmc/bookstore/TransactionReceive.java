package com.zsolt.jpmc.bookstore;

public class TransactionReceive implements Transaction {

    @Override
    public String process(Inventory inventory, String... params) throws BadParameterException {
        if (params.length != 4) {
            throw new BadParameterException("Invalid numer of arguments. RECEIVE must have 3 parameters: isbn, quantity, price.");
        }
        
        Book b = new Book();
        b.setISBN(params[1]);
        b.setQuantity(params[2]);
        b.setPrice(params[3]);
        
        Book book = inventory.get(b.getISBN());
        if (book != null) {
            book.setPrice(b.getPrice());
            book.incQuantity(b.getQuantity());
        } else {
            inventory.put(b.getISBN(), b);
        }
        
        return String.format("Received %d %s of book %s at £%.2f each. Total: £%.2f.", 
                b.getQuantity(), 
                b.getQuantity() > 0 ? "copies" : "copy",
                b.getISBN(), 
                b.getPrice(), 
                b.getQuantity() * b.getPrice());
    }

    @Override
    public TransactionType getType() {
        return TransactionType.RECEIVE;
    }
}
