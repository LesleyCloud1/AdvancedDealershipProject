package com.pluralsight;

public class SalesContract extends Contract {
    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.0;
    private static final double PROCESSING_FEE_UNDER_10K = 295.0;
    private static final double PROCESSING_FEE_10K_OR_MORE = 495.0;

    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        double basePrice = vehicleSold.getPrice();
        double tax = basePrice * SALES_TAX_RATE;
        double processingFee = (basePrice < 10000) ? PROCESSING_FEE_UNDER_10K : PROCESSING_FEE_10K_OR_MORE;
        return basePrice + tax + RECORDING_FEE + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0.0;
        double total = getTotalPrice();
        int term = (vehicleSold.getPrice() < 10000) ? 24 : 48;
        double interestRate = (vehicleSold.getPrice() < 10000) ? 0.0525 : 0.0425;
        double monthlyRate = interestRate / 12.0;
        return (total * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -term));
    }

    public boolean isFinanced() { return finance; }
}