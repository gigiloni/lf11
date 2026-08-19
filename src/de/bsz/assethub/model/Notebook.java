package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Notebook extends Asset {

    private int ram;
    private String serialNumber;

    public Notebook(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            int ram,
            String serialNumber) {

        super(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location
        );

        this.ram = ram;
        this.serialNumber = Objects.requireNonNull(
                serialNumber,
                "Serial number must not be null"
        );
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = Objects.requireNonNull(
                serialNumber,
                "Serial number must not be null"
        );
    }

    @Override
    public String toString() {
        return "Notebook{"
                + "asset=" + super.toString()
                + ", ram=" + ram + " GB"
                + ", serialNumber='" + serialNumber + '\''
                + '}';
    }
}
