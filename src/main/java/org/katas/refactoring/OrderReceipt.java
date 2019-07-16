package org.katas.refactoring;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final double RATE = 0.1;
    private static final String SALE_TEX = "Sales Tax";
    private static final String TOTAL_AMOUNT = "Total Amount";
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        double totalSalesTax = calculateTotalSalesTax();
        double totalAmount = calculateTotalAmount();
        String customerName = order.getCustomerName();
        String customerAddress = order.getCustomerAddress();
        return print(customerName, customerAddress, printItemsToString(), totalSalesTax, totalAmount);
    }

    private String print(String customerName, String customerAddress, String lineItem, double totalSalesTax, double totalAmount) {
        return String.format(
                "======Printing Orders======\n" +
                "%s %s %s" +
                "%s\t%.1f" +
                "%s\t%.1f"
        , customerName, customerAddress, lineItem, SALE_TEX, totalSalesTax, TOTAL_AMOUNT, totalAmount);
    }

    private String printItemsToString() {
        return order.getLineItems().stream().map(x -> String.format("%s\t%s\t%s\t%s\n",
                x.getDescription(), x.getPrice(), x.getQuantity(), x.getTotalAmount())).collect(Collectors.joining(""));
    }

    private double calculateTotalSalesTax() {
        return order.getLineItems().stream()
                .mapToDouble(x-> x.getTotalAmount() * RATE)
                .sum();
    }

    private double calculateTotalAmount() {
        return order.getLineItems().stream()
                .mapToDouble(x-> x.getTotalAmount() + x.getTotalAmount() * RATE)
                .sum();
    }
}