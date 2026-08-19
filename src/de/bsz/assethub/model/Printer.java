package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Printer extends Asset {

    private String tonerType;
    private boolean colorCapable;

    public Printer(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            String tonerType,
            boolean colorCapable) {

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
    public String toString() {
        return "Printer{"
                + "asset=" + super.toString()
                + ", tonerType='" + tonerType + '\''
                + ", colorCapable=" + colorCapable
                + '}';
    }
}
