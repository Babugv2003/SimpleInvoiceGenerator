package com.invoicegenerator.model;

import java.io.IOException;
import java.math.BigDecimal;

public class OfflineOrder extends Invoice {
    private String cashierName;

    //  Constructor: sets customer name, tax rate, and cashier name
    public OfflineOrder(String customerName, BigDecimal taxRate,String cashierName){
        // Call the constructor of parent class (Invoice)
        super(customerName,taxRate);

        if(cashierName==null || cashierName.isBlank()){
            throw new IllegalArgumentException("Cashier name required.");
        }
        this.cashierName=cashierName;
    }
    @Override
    public String generateBill() throws IOException{
        // Generate the bill using the parent class method
        String filePath=super.generateBill();

        System.out.println("Bill processed by cashier: " +cashierName);
        return filePath;
    }
}
