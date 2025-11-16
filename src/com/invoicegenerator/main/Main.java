package com.invoicegenerator.main;

import com.invoicegenerator.model.Invoice;
import com.invoicegenerator.model.OfflineOrder;
import com.invoicegenerator.model.OnlineOrder;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final BigDecimal DEFAULT_TAX_RATE = new BigDecimal("0.05"); // 5% tax

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter customer name: ");
            String customer = sc.nextLine();

            System.out.println("Enter order type (online/offline): ");
            String type = sc.nextLine();

            BigDecimal taxRate = DEFAULT_TAX_RATE;
            Invoice invoice;

            // Choose type of order
            if (type.equalsIgnoreCase("online")) {
                System.out.println("Enter payment method (UPI/Card): ");
                String method = sc.nextLine();
                invoice = new OnlineOrder(customer, taxRate, method);

            } else if (type.equalsIgnoreCase("offline")) {
                System.out.println("Enter cashier name: ");
                String cashier = sc.nextLine();
                invoice = new OfflineOrder(customer, taxRate, cashier);

            } else {
                System.out.println("Invalid order type. Use 'online' or 'offline'.");
                return;
            }

            // Add items loop
            while (true) {
                System.out.println("Enter item name (or 'done' to finish): ");
                String name = sc.nextLine().trim();
                if (name.equalsIgnoreCase("done")) break;

                BigDecimal price = readMoney(sc, "Enter price: ");
                int qty = readPositiveInt(sc, "Enter quantity: ");

                invoice.addItem(name, price, qty);
            }

            // Generate invoice
            String path = invoice.generateBill();
            System.out.println("✅ Invoice generated at: " + path);
            System.out.println("Subtotal: " + invoice.calculateSubtotal());
            System.out.println("Tax     : " + invoice.calculateTax());
            System.out.println("Total   : " + invoice.calculateGrandTotal());

        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid number entered.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // Read and validate price (BigDecimal)
    private static BigDecimal readMoney(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = sc.nextLine().trim();
                BigDecimal val = new BigDecimal(s);
                if (val.signum() < 0) {
                    System.out.println("Price must be >= 0.");
                    continue;
                }
                return val.setScale(2, BigDecimal.ROUND_HALF_UP);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid amount (e.g., 499.99).");
            }
        }
    }

    // Read and validate quantity (integer)
    private static int readPositiveInt(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = sc.nextLine().trim();
                int q = Integer.parseInt(s);
                if (q <= 0) {
                    System.out.println("Quantity must be > 0.");
                    continue;
                }
                return q;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a whole number (e.g., 1, 2, 3).");
            }
        }
    }
}
