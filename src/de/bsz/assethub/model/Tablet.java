package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Tablet extends Asset {

    private int storageCapacityGb;

    public Tablet(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            int storageCapacityGb) {

        super(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location
        );

        this.storageCapacityGb = storageCapacityGb;
    }

    public int getStorageCapacityGb() {
        return storageCapacityGb;
    }

    public void setStorageCapacityGb(int storageCapacityGb) {
        this.storageCapacityGb = storageCapacityGb;
    }

    @Override
    public BigDecimal calculateResidualValue() {
        return calculateLinearResidualValue(3);
    }

    @Override
    public String getInventoryLine() {
        return String.format(
                Locale.GERMANY,
                "%-8s | %-25s | %4d GB Speicher | Restwert: %10.2f EUR",
                getInventoryNumber(),
                getDescription(),
                storageCapacityGb,
                calculateResidualValue()
        );
    }

    @Override
    public String getTypeName() {
        return "Tablet";
    }

    @Override
    public String toString() {
        return "Tablet{"
                + "asset=" + super.toString()
                + ", storageCapacityGb=" + storageCapacityGb
                + '}';
    }
}
