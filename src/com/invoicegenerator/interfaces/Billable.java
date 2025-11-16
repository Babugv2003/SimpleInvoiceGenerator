package com.invoicegenerator.interfaces;

import java.io.IOException;

public interface Billable {
    String generateBill() throws IOException;
}
