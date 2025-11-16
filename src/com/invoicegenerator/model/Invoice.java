package com.invoicegenerator.model;

import com.invoicegenerator.utils.FileUtils; // ✅ import added

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Invoice extends Order {
    private final List<Item> items = new ArrayList<>();

    /** Inner class representing an invoice line item */
    public class Item {
        private String name;
        private BigDecimal price;
        private int quantity; // ✅ corrected from 'quality'

        public Item(String name, BigDecimal price, int quantity) {
            if (name == null || name.isBlank())
                throw new IllegalArgumentException("Item name required.");
            if (price == null || price.signum() < 0)
                throw new IllegalArgumentException("Price must be >= 0.");
            if (quantity <= 0)
                throw new IllegalArgumentException("Quantity must be > 0.");

            this.name = name.trim();
            this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public int getQuantity() {   // ✅ fixed method name
            return quantity;
        }

        /** Calculates price × quantity and rounds to 2 decimals */
        public BigDecimal getLineTotal() {
            return price.multiply(BigDecimal.valueOf(quantity))
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    /** Calls the parent Order class constructor to set customer name and tax rate */
    public Invoice(String customerName, BigDecimal taxRate) {
        super(customerName, taxRate);
    }

    public void addItem(String name, BigDecimal price, int quantity) {
        items.add(new Item(name, price, quantity));
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    /** Calculates subtotal (sum of all item totals) */
    @Override
    public BigDecimal calculateSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Item item : items) {
            subtotal = subtotal.add(item.getLineTotal());
        }
        return subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String generateBill() throws IOException {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Cannot generate bill with no items.");
        }

        /** Creates a safe file name with customer name + timestamp */
        String safeCustomer = FileUtils.toSafeFilename(customerName); // ✅ now FileUtils works
        String filename = safeCustomer + "_invoice_" + FileUtils.timestamp() + ".txt";
        String absolutePath = FileUtils.absolutePath(filename);

        try (FileWriter writer = new FileWriter(absolutePath)) {
            writer.write("============== INVOICE ==============\n");
            writer.write("Customer: " + customerName + "\n");
            writer.write("Tax Rate: " + taxRate.multiply(new BigDecimal("100"))
                    .setScale(2, BigDecimal.ROUND_HALF_UP) + "%\n");
            writer.write("-------------------------------------\n");
            writer.write(String.format("%-20s %8s %6s %10s%n", "Item", "Price", "Qty", "Total"));
            writer.write("-------------------------------------\n");

            for (Item item : items) {
                writer.write(String.format(
                        "%-20.20s %8s %6d %10s%n",
                        item.getName(),
                        item.getPrice().toPlainString(),
                        item.getQuantity(), // ✅ corrected method name
                        item.getLineTotal().toPlainString()
                ));
            }

            writer.write("-------------------------------------\n");
            BigDecimal subtotal = calculateSubtotal();
            BigDecimal tax = calculateTax();
            BigDecimal grand = calculateGrandTotal();

            writer.write(String.format("%-20s %8s %6s %10s%n", "Subtotal", "", "", subtotal.toPlainString()));
            writer.write(String.format("%-20s %8s %6s %10s%n", "Tax", "", "", tax.toPlainString()));
            writer.write(String.format("%-20s %8s %6s %10s%n", "Grand Total", "", "", grand.toPlainString()));
            writer.write("=====================================\n");
        }

        return absolutePath;
    }
}
