package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Monitor extends Asset {

    private double screenSizeInches;

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
