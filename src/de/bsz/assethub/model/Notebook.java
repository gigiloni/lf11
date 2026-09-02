package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Notebook extends Asset implements Loanable, Maintainable {

    private int ram;
    private String serialNumber;
    private final LoanStatus loanStatus = new LoanStatus();
    private LocalDate lastMaintenanceDate;

    public Notebook(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            int ram,
            String serialNumber) {

        this(
                inventoryNumber,
                description,
                purchaseDate,
                purchasePrice,
                location,
                ram,
                serialNumber,
                purchaseDate
        );
    }

    public Notebook(
            String inventoryNumber,
            String description,
            LocalDate purchaseDate,
            BigDecimal purchasePrice,
            String location,
            int ram,
            String serialNumber,
            LocalDate lastMaintenanceDate) {

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
        this.lastMaintenanceDate = Objects.requireNonNull(
                lastMaintenanceDate,
                "Last maintenance date must not be null"
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
    public boolean isAvailable() {
        return loanStatus.isAvailable();
    }

    @Override
    public void loanTo(Employee employee, int days) {
        loanStatus.loanTo(employee, days, LocalDate.now());
    }

    @Override
    public void returnAsset() {
        loanStatus.returnAsset();
    }

    @Override
    public Optional<Employee> getCurrentUser() {
        return loanStatus.getCurrentUser();
    }

    @Override
    public Optional<LocalDate> getDueDate() {
        return loanStatus.getDueDate();
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
        return 12;
    }

    @Override
    public BigDecimal calculateResidualValue() {
        return calculateLinearResidualValue(3);
    }

    @Override
    public String getInventoryLine() {
        return String.format(
                Locale.GERMANY,
                "%-8s | %-25s | %3d GB RAM | SN: %-12s | Restwert: %10.2f EUR",
                getInventoryNumber(),
                getDescription(),
                ram,
                serialNumber,
                calculateResidualValue()
        );
    }

    @Override
    public String getTypeName() {
        return "Notebook";
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
