package com.invoicegenerator.model;

import java.io.IOException;
import java.math.BigDecimal;

public class OnlineOrder extends Invoice {
/** This creates a variable to store how the customer paid (UPI, Card, etc.).
 final means once set, it can’t be changed.*/
   private final String paymentMethod;


    /**you give: customer name, tax rate, and payment method.
     The super() part calls the Invoice class constructor to set common values.*/
   public OnlineOrder(String customerName, BigDecimal taxRate,String paymentMethod){
       super(customerName,taxRate);
       if(paymentMethod ==null || paymentMethod.isBlank()){
           throw new IllegalArgumentException("Payment method required");
       }
       this.paymentMethod=paymentMethod.trim();
   }

    /**This method does 3 simple things:
     1.Calls parent’s method super.generateBill() to create the invoice file (same as Invoice class).
     2.Prints payment method to screen (console).
        Example: Payment Method: UPI
     3.Returns the file path of the created invoice.*/

   @Override
    public String generateBill() throws IOException{
      String  path =super.generateBill();
       System.out.println("Payment Method:"+paymentMethod);
       return path;
   }
}
