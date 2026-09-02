package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Server extends Asset implements Maintainable {

    private int rackUnits;
    private int cpuCount;
    private LocalDate lastMaintenanceDate;

    public Server(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            int rackUnits,
            int cpuCount) {

        this(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location,
                rackUnits,
                cpuCount,
                purchaseDate
        );
    }

    public Server(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            int rackUnits,
            int cpuCount,
            LocalDate lastMaintenanceDate) {

        super(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location
        );

        this.rackUnits = rackUnits;
        this.cpuCount = cpuCount;
        this.lastMaintenanceDate = Objects.requireNonNull(
                lastMaintenanceDate,
                "Last maintenance date must not be null"
        );
    }

    public int getRackUnits() {
        return rackUnits;
    }

    public void setRackUnits(int rackUnits) {
        this.rackUnits = rackUnits;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
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
        return 3;
    }

    @Override
    public BigDecimal calculateResidualValue() {
        return calculateLinearResidualValue(7);
    }

    @Override
    public String getInventoryLine() {
        return String.format(
                Locale.GERMANY,
                "%-8s | %-25s | %2d HE | %2d CPUs | Restwert: %10.2f EUR",
                getInventoryNumber(),
                getDescription(),
                rackUnits,
                cpuCount,
                calculateResidualValue()
        );
    }

    @Override
    public String getTypeName() {
        return "Server";
    }

    @Override
    public String toString() {
        return "Server{"
                + "asset=" + super.toString()
                + ", rackUnits=" + rackUnits
                + ", cpuCount=" + cpuCount
                + '}';
    }
}
