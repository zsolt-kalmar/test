package com.zsolt.jpmc.bookstore;

public class TransactionReturn implements Transaction {

    @Override
    public String process(Inventory inventory, String... params) throws BadParameterException {
       if (params.length != 3) {
            throw new BadParameterException("Invalid numer of arguments. RETURN must have 2 parameters: isbn, quantity.");
        }
        
        Book b = new Book();
        b.setISBN(params[1]);
        b.setQuantity(params[2]);
        
        Book book = inventory.get(b.getISBN());
        if (book == null) {
            return String.format("Book %s is not available.", b.getISBN());
        }
        
        book.incQuantity(b.getQuantity());
        double refund = book.getPrice()* b.getQuantity();
        inventory.changeRefundAmount(refund);
        inventory.changeReturnedQuantity(b.getQuantity());
        
        return String.format("Refund %d %s of %s at £%.2f each. Total: -£%.2f.", 
                b.getQuantity(), 
                b.getQuantity() > 1 ? "copies" : "copy",
                b.getISBN(), 
                book.getPrice(), 
                refund);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.RETURN;
    }
}
