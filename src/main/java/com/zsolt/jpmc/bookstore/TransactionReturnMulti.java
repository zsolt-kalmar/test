package com.zsolt.jpmc.bookstore;

import java.util.ArrayList;

public class TransactionReturnMulti extends TransactionReturn {

    @Override
    public String process(Inventory inventory, String... params) throws BadParameterException {
        if ((params.length - 1) % 2 != 0) {
            throw new BadParameterException("Invalid numer of arguments. MULTIRETURN must have even number of parameters: (isbn,quantity),...");
        }

        ArrayList<String> output = new ArrayList<>(4);
        for (int i = 1; i<params.length; i+=2) {
            output.add(super.process(inventory, params[0], params[i], params[i+1]));
        }
        
        return String.join("\r\n", output);    }

    @Override
    public TransactionType getType() {
        return TransactionType.MULTIRETURN;
    }
}
