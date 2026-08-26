package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Abstract base class for all assets managed by AssetHub.
 */
public abstract class Asset {

    private final String inventoryNumber;
    private String description;
    private LocalDate purchaseDate;
    private BigDecimal purchasePrice;
    private String location;

    /**
     * Creates an asset.
     *
     * @param inventoryNumber unique inventory number
     * @param description asset description
     * @param purchaseDate date of purchase
     * @param purchasePrice purchase price
     * @param location current location
     */
    protected Asset(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location) {

        this.inventoryNumber = Objects.requireNonNull(
                inventoryNumber,
                "Inventory number must not be null"
        );

        this.description = Objects.requireNonNull(
                description,
                "Description must not be null"
        );

        this.purchaseDate = Objects.requireNonNull(
                purchaseDate,
                "Purchase date must not be null"
        );

        this.purchasePrice = validatePurchasePrice(purchasePrice);

        this.location = Objects.requireNonNull(
                location,
                "Location must not be null"
        );
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(
                description,
                "Description must not be null"
        );
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = Objects.requireNonNull(
                purchaseDate,
                "Purchase date must not be null"
        );
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = validatePurchasePrice(purchasePrice);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = Objects.requireNonNull(
                location,
                "Location must not be null"
        );
    }

    /**
     * Returns the age of the asset in complete years.
     *
     * @return age in years
     */
    public int ageInYears() {
        return Period.between(purchaseDate, LocalDate.now()).getYears();
    }

    public abstract BigDecimal calculateResidualValue();

    public abstract String getInventoryLine();

    protected BigDecimal calculateLinearResidualValue(int usefulLifeYears) {
        int remainingYears = Math.max(0, usefulLifeYears - ageInYears());

        return purchasePrice
                .multiply(BigDecimal.valueOf(remainingYears))
                .divide(
                        BigDecimal.valueOf(usefulLifeYears),
                        2,
                        RoundingMode.HALF_UP
                );
    }

    private static BigDecimal validatePurchasePrice(BigDecimal purchasePrice) {
        BigDecimal validatedPrice = Objects.requireNonNull(
                purchasePrice,
                "Purchase price must not be null"
        );

        if (validatedPrice.signum() < 0) {
            throw new IllegalArgumentException(
                    "Purchase price must not be negative"
            );
        }

        return validatedPrice;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Asset asset)) {
            return false;
        }

        return inventoryNumber.equals(asset.inventoryNumber);
    }

    @Override
    public int hashCode() {
        return inventoryNumber.hashCode();
    }

    /**
     * Override toString methode to provide better class status representation.
     *
     * @return string message
     */
    @Override
    public String toString() {
        return "Asset{"
                + "inventoryNumber='" + inventoryNumber + '\''
                + ", description='" + description + '\''
                + ", purchaseDate=" + purchaseDate
                + ", purchasePrice=" + purchasePrice
                + ", location='" + location + '\''
                + ", ageInYears=" + ageInYears()
                + '}';
    }
}
