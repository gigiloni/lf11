package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Smartphone extends Asset {

    private int storageGb;
    private String imei;

    public Smartphone(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            int storageGb,
            String imei) {

        super(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location
        );

        this.storageGb = storageGb;
        this.imei = validateImei(imei);
    }

    public int getStorageGb() {
        return storageGb;
    }

    public void setStorageGb(int storageGb) {
        this.storageGb = storageGb;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = validateImei(imei);
    }

    public int usefulLifeInYears() {
        return 2;
    }

    @Override
    public BigDecimal calculateResidualValue() {
        return calculateLinearResidualValue(usefulLifeInYears());
    }

    @Override
    public String getInventoryLine() {
        return String.format(
                Locale.GERMANY,
                "%-8s | %-25s | %4d GB Speicher | IMEI: ****%s | Restwert: %10.2f EUR",
                getInventoryNumber(),
                getDescription(),
                storageGb,
                getLastFourImeiDigits(),
                calculateResidualValue()
        );
    }

    @Override
    public String getTypeName() {
        return "Smartphone";
    }

    private String getLastFourImeiDigits() {
        return imei.substring(imei.length() - 4);
    }

    private static String validateImei(String imei) {
        String validatedImei = Objects.requireNonNull(
                imei,
                "IMEI must not be null"
        );

        if (validatedImei.length() < 4) {
            throw new IllegalArgumentException(
                    "IMEI must contain at least four characters"
            );
        }

        return validatedImei;
    }
}
