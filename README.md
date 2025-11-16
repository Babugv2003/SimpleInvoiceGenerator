# Simple Invoice Generator

The **Simple Invoice Generator** is a Core Java–based billing application designed to demonstrate clean object-oriented design, inner classes, and file-based invoice generation. The system allows users to create itemized invoices, calculate billing totals, and export formatted invoice reports using Java file handling.

## 🔹 Overview

This project generates professional invoice files based on user input. It demonstrates key backend development concepts using pure Java, making it an ideal academic, portfolio, or interview project.

---

## 🔹 Key Features

- Add items with **name**, **price**, and **quantity**
- Automatic calculation of:
  - Subtotal  
  - Tax  
  - Grand Total  
- **Inner class** (`Item`) used inside `Invoice`
- **Abstraction, Inheritance, Polymorphism, Encapsulation**
- Clean file generation using Java **File I/O**
- Supports **Online** and **Offline** order workflows
- Extensible design suitable for integrating:
  - MySQL (JDBC)
  - GUI (JavaFX/Swing)
  - Spring Boot API

---

## 🔹 Project Structure

```

src/
└── com.invoicegenerator
├── main
│   └── Main.java
├── model
│   ├── Order.java
│   ├── Invoice.java
│   ├── OnlineOrder.java
│   └── OfflineOrder.java
├── interfaces
│   └── Billable.java
└── utils
└── FileUtils.java

```

---

## 🔹 Technologies Used

- **Java (Core Java 8+)**
- **OOP Concepts**
- **File Handling (FileWriter, BufferedWriter)**
- **Collections Framework**
- **Command-Line UI**

---

## 🔹 How It Works

1. User runs the program from `Main.java`
2. Customer details and order type are collected
3. User adds multiple items with price and quantity
4. System calculates:
   - Subtotal  
   - Tax  
   - Grand Total  
5. A formatted invoice `.txt` file is generated in the project directory
6. File name is auto-generated using timestamps

---

## 🔹 Sample Invoice Output

```

===============================
INVOICE
=======

Customer : Babu
Order Type : Online
Payment Mode : UPI

Items:

1. Laptop (Qty: 1) - ₹55000
2. Mouse  (Qty: 1) - ₹500

Subtotal   : ₹55500
Tax (5%)   : ₹2775
Grand Total: ₹58275

Generated File: Babu_invoice_20250210_113045.txt

```

---

## 🔹 Skills Demonstrated

- Object-Oriented Design  
- Inner Class Implementation  
- Interface-Based Architecture  
- Clean File I/O Processing  
- Modular Java Package Structure  
- CLI-driven Application Workflow  

---

## 🔹 Author

**Babu G V**  
Computer Science Engineering Student  
Skills: Core Java, OOP, Collections, SQL, Spring, Spring Boot (Basics)  
GitHub: [Babugv2003](https://github.com/Babugv2003)

---

## 🔹 Future Enhancements

- MySQL Database Integration (JDBC)
- REST API version using Spring Boot
- GUI Implementation using JavaFX
- PDF Invoice Export  
- Cloud-based storage integration

---

✔ *An executive-level project description for your resume*
✔ *A LinkedIn-optimized project summary*
