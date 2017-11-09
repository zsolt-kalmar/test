package com.zsolt.jpmc.bookstore;

import java.util.EnumMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public final class BookstoreApplication {

    private final Map<TransactionType, Transaction> transactions = new EnumMap<>(TransactionType.class);

    final Inventory inventory = new Inventory();
    
    public Map<TransactionType, Transaction> getTransactions() {
        return transactions;
    }
    
    private void registerTransaction(Transaction transaction) {
       transactions.put(transaction.getType(), transaction);
    }
    
    public BookstoreApplication() {
        registerTransaction(new TransactionReceive());
        registerTransaction(new TransactionQuery());
        registerTransaction(new TransactionUpdate());
        registerTransaction(new TransactionBuy());
        registerTransaction(new TransactionBuyMulti());
        registerTransaction(new TransactionReturn());
        registerTransaction(new TransactionReturnMulti());
    }
    
    private void processInventory(){
        System.out.println("\r\nSUMMARY:");
        
        System.out.printf("Sold %d %s. Total: £%.2f.\r\n",
                inventory.getSoldQuantity(),
                inventory.getSoldQuantity() > 1 ? "books" : "book",
                inventory.getIncomeAmount());
        
        System.out.printf("Refunded %d %s. Total: £%.2f.\r\n",
                inventory.getReturnedQuantity(),
                inventory.getReturnedQuantity() > 1 ? "books" : "book",
                inventory.getRefundAmount());

        System.out.printf("Net total: £%.2f.\r\n",
                inventory.getNetIncome());

        System.out.println("\r\nINVENTORY:");
        inventory.forEach((isbn, book) -> {System.out.printf("%s:%d@£%.2f\r\n", isbn, book.getQuantity(), book.getPrice());});
            
    }
    
    private void processFile(Path path){
        try (Scanner scanner = new Scanner(path)) {
            String output;

            System.out.println("TRANSACTIONS:");

            while (scanner.hasNext()){
                String line = scanner.nextLine();
                output = callTransaction(line.split(","));
                
                System.out.println(output);
            }
        } catch (Exception exception) {
            String message = String.format("Unable to process the file (%s).", exception.getMessage());
            
            System.out.println(message);
        }
   }
    
    private String callTransaction(String[] params) {
        String output;
        
        Transaction transaction = transactions.get(TransactionType.fromString(params[0]));
        if (transaction != null) {
            try {
               output = transaction.process(inventory, params);
            } catch (BadParameterException exception) {
               output = exception.getMessage();
            }
        } else {
           output = String.format("Unknown transaction handler (%s).", params[0]); 
        }

        return output;
    }
    
    private static String printUsage() {
        return "Usage: BookstorApplication <path to transaction file>";
    }
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(printUsage());
            System.exit(0);
        }
        
        Path path = Paths.get(args[0]);
        
        if (!Files.exists(path)) {
            System.out.printf("File %s does not exist.\r\n", path);
            System.out.println(printUsage());
            System.exit(0);
        }
        
        BookstoreApplication app = new BookstoreApplication();
        app.processFile(path);
        app.processInventory();
    }    
}
