package org.katas.refactoring;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final double RATE = 0.1;
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        printHeaders(output);

        printNameAndAddress(output);

        printLineItems(output);

        double totalSalesTax = calculateTotalSalesTax();
        double totalAmount = calculateTotalAmount();

        printSalesTex(output, totalSalesTax);
        printTotalAmount(output, totalAmount);

        return output.toString();
    }

    private void printHeaders(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }

    private void printNameAndAddress(StringBuilder output) {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    private void printLineItems(StringBuilder output) {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription()).append('\t');
            output.append(lineItem.getPrice()).append('\t');
            output.append(lineItem.getQuantity()).append('\t');
            output.append(lineItem.totalAmount()).append('\n');
        }
    }

    private void printSalesTex(StringBuilder output, double totalSalesTax) {
        output.append("Sales Tax").append('\t').append(totalSalesTax);
    }

    private void printTotalAmount(StringBuilder output, double totalAmount) {
        output.append("Total Amount").append('\t').append(totalAmount);
    }

    private double calculateTotalSalesTax() {
        double totalSalesTax = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            double salesTax = lineItem.totalAmount() * RATE;
            totalSalesTax += salesTax;
        }
        return totalSalesTax;
    }

    private double calculateTotalAmount() {
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            double salesTax = lineItem.totalAmount() * RATE;
            totalAmount += lineItem.totalAmount() + salesTax;
        }
        return totalAmount;
    }
}