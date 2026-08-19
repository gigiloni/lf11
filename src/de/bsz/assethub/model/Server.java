package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Server extends Asset {

    private int rackUnits;
    private int cpuCount;

    public Server(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            int rackUnits,
            int cpuCount) {

        super(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location
        );

        this.rackUnits = rackUnits;
        this.cpuCount = cpuCount;
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
    public String toString() {
        return "Server{"
                + "asset=" + super.toString()
                + ", rackUnits=" + rackUnits
                + ", cpuCount=" + cpuCount
                + '}';
    }
}
