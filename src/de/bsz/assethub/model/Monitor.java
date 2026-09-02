package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

public class Monitor extends Asset implements Loanable {

    private double screenSizeInches;
    private final LoanStatus loanStatus = new LoanStatus();

    public Monitor(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            double screenSizeInches) {

        super(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location
        );

        this.screenSizeInches = screenSizeInches;
    }

    public double getScreenSizeInches() {
        return screenSizeInches;
    }

    public void setScreenSizeInches(double screenSizeInches) {
        this.screenSizeInches = screenSizeInches;
    }

    @Override
    public boolean isAvailable() {
        return loanStatus.isAvailable();
    }

    @Override
    public void loanTo(Employee employee, int days) {
        loanStatus.loanTo(employee, days, LocalDate.now());
    }

    @Override
    public void returnAsset() {
        loanStatus.returnAsset();
    }

    @Override
    public Optional<Employee> getCurrentUser() {
        return loanStatus.getCurrentUser();
    }

    @Override
    public Optional<LocalDate> getDueDate() {
        return loanStatus.getDueDate();
    }

    @Override
    public BigDecimal calculateResidualValue() {
        return calculateLinearResidualValue(5);
    }

    @Override
    public String getInventoryLine() {
        return String.format(
                Locale.GERMANY,
                "%-8s | %-25s | %4.1f Zoll | Restwert: %10.2f EUR",
                getInventoryNumber(),
                getDescription(),
                screenSizeInches,
                calculateResidualValue()
        );
    }

    @Override
    public String getTypeName() {
        return "Monitor";
    }

    @Override
    public String toString() {
        return "Monitor{"
                + "asset=" + super.toString()
                + ", screenSizeInches=" + screenSizeInches
                + '}';
    }
}
