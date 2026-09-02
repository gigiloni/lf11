package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Printer extends Asset implements Maintainable {

    private String tonerType;
    private boolean colorCapable;
    private LocalDate lastMaintenanceDate;

    public Printer(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            String tonerType,
            boolean colorCapable) {

        this(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location,
                tonerType,
                colorCapable,
                purchaseDate
        );
    }

    public Printer(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            String tonerType,
            boolean colorCapable,
            LocalDate lastMaintenanceDate) {

        super(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location
        );

        this.tonerType = Objects.requireNonNull(
                tonerType,
                "Toner type must not be null"
        );

        this.colorCapable = colorCapable;
        this.lastMaintenanceDate = Objects.requireNonNull(
                lastMaintenanceDate,
                "Last maintenance date must not be null"
        );
    }

    public String getTonerType() {
        return tonerType;
    }

    public void setTonerType(String tonerType) {
        this.tonerType = Objects.requireNonNull(
                tonerType,
                "Toner type must not be null"
        );
    }

    public boolean isColorCapable() {
        return colorCapable;
    }

    public void setColorCapable(boolean colorCapable) {
        this.colorCapable = colorCapable;
    }

    @Override
    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = Objects.requireNonNull(
                lastMaintenanceDate,
                "Last maintenance date must not be null"
        );
    }

    @Override
    public int getMaintenanceIntervalInMonths() {
        return 6;
    }

    @Override
    public BigDecimal calculateResidualValue() {
        return calculateLinearResidualValue(5);
    }

    @Override
    public String getTypeName() {
        return "Printer";
    }

    @Override
    public String getInventoryLine() {
        return String.format(
                Locale.GERMANY,
                "%-8s | %-25s | Toner %-10s | %-5s | Restwert: %10.2f EUR",
                getInventoryNumber(),
                getDescription(),
                tonerType,
                colorCapable ? "Farbe" : "s/w",
                calculateResidualValue()
        );
    }

    @Override
    public String toString() {
        return "Printer{"
                + "asset=" + super.toString()
                + ", tonerType='" + tonerType + '\''
                + ", colorCapable=" + colorCapable
                + '}';
    }
}
