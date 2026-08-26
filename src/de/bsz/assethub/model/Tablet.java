package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    public String toString() {
        return "Tablet{"
                + "asset=" + super.toString()
                + ", storageCapacityGb=" + storageCapacityGb
                + '}';
    }
}
