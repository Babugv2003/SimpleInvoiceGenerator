package com.invoicegenerator.model;

import com.invoicegenerator.interfaces.Billable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Order implements Billable {
    protected final String customerName;
    protected final BigDecimal taxRate;

    protected Order(String customerName, BigDecimal taxRate) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        if (taxRate == null || taxRate.signum() < 0) {
            throw new IllegalArgumentException("Tax rate must be >= 0.");
        }
        this.customerName = customerName.trim();
        this.taxRate = taxRate;
    }

    // Return pre-tax subtotal
    public abstract BigDecimal calculateSubtotal();

    // Return subtotal * taxRate (rounded to two decimals)
    public BigDecimal calculateTax() {
        return calculateSubtotal()
                .multiply(taxRate)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Return subtotal + tax (rounded to two decimals)
    public BigDecimal calculateGrandTotal() {
        return calculateSubtotal()
                .add(calculateTax())
                .setScale(2, RoundingMode.HALF_UP);
    }
}
