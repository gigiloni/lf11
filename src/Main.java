import de.bsz.assethub.model.Asset;
import de.bsz.assethub.model.AssetInventory;
import de.bsz.assethub.model.AssetReport;
import de.bsz.assethub.model.Employee;
import de.bsz.assethub.model.Loan;
import de.bsz.assethub.model.Loanable;
import de.bsz.assethub.model.Location;
import de.bsz.assethub.model.Maintainable;
import de.bsz.assethub.model.Monitor;
import de.bsz.assethub.model.Notebook;
import de.bsz.assethub.model.Printer;
import de.bsz.assethub.model.Server;
import de.bsz.assethub.model.Smartphone;
import de.bsz.assethub.model.Tablet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        AssetInventory inventory = new AssetInventory();
        AssetReport report = new AssetReport();

        Notebook notebook1 = new Notebook(
                "NB-001",
                "Lenovo ThinkPad T14",
                LocalDate.of(2023, 4, 12),
                new BigDecimal("1499.99"),
                "Eisenach",
                16,
                "PF3X9K2",
                LocalDate.of(2025, 6, 15)
        );

        Notebook notebook2 = new Notebook(
                "NB-002",
                "Dell Latitude 5540",
                LocalDate.of(2024, 1, 20),
                new BigDecimal("1799.00"),
                "Erfurt",
                32,
                "DL5540-8821",
                LocalDate.of(2026, 5, 1)
        );

        Printer printer1 = new Printer(
                "PR-001",
                "HP LaserJet M404",
                LocalDate.of(2022, 5, 10),
                new BigDecimal("399.99"),
                "Ilmenau",
                "HP 59A",
                false,
                LocalDate.of(2026, 1, 20)
        );

        Printer printer2 = new Printer(
                "PR-002",
                "Brother MFC-L8690CDW",
                LocalDate.of(2023, 8, 15),
                new BigDecimal("649.00"),
                "Erfurt",
                "Brother TN-421",
                true,
                LocalDate.of(2026, 8, 15)
        );

        Server server1 = new Server(
                "SRV-001",
                "Dell PowerEdge R750",
                LocalDate.of(2021, 2, 5),
                new BigDecimal("7500.00"),
                "Data Center Ilmenau",
                2,
                2,
                LocalDate.of(2026, 4, 10)
        );

        Server server2 = new Server(
                "SRV-002",
                "HPE ProLiant DL380",
                LocalDate.of(2020, 11, 18),
                new BigDecimal("9200.00"),
                "Data Center Erfurt",
                2,
                4,
                LocalDate.of(2026, 8, 18)
        );

        Monitor monitor1 = new Monitor(
                "MON-001",
                "Dell UltraSharp U2723QE",
                LocalDate.of(2024, 3, 1),
                new BigDecimal("629.00"),
                "Eisenach",
                27.0
        );

        Monitor monitor2 = new Monitor(
                "MON-002",
                "Samsung Odyssey G5",
                LocalDate.of(2023, 9, 7),
                new BigDecimal("349.99"),
                "Ilmenau",
                32.0
        );

        Tablet tablet1 = new Tablet(
                "TAB-001",
                "Apple iPad Air",
                LocalDate.of(2025, 2, 14),
                new BigDecimal("799.00"),
                "Eisenach",
                256
        );

        Tablet tablet2 = new Tablet(
                "TAB-002",
                "Samsung Galaxy Tab S10",
                LocalDate.of(2024, 10, 3),
                new BigDecimal("949.00"),
                "Erfurt",
                512
        );

        Smartphone smartphone = new Smartphone(
                "SP-001",
                "Google Pixel 10",
                LocalDate.of(2026, 2, 12),
                new BigDecimal("899.00"),
                "Ilmenau",
                256,
                "356789123456789"
        );

        inventory.add(notebook1);
        inventory.add(notebook2);
        inventory.add(printer1);
        inventory.add(printer2);
        inventory.add(server1);
        inventory.add(server2);
        inventory.add(monitor1);
        inventory.add(monitor2);
        inventory.add(tablet1);
        inventory.add(tablet2);
        inventory.add(smartphone);

        System.out.println("Inventory list:");
        report.printInventoryList(inventory.getAll());

        System.out.println(
                "\nTotal residual value: "
                        + report.totalResidualValue(inventory.getAll())
                        + " EUR"
        );

        System.out.println("\nSorted by residual value (descending):");
        report.printInventoryList(
                report.sortedByResidualValue(inventory.getAll())
        );

        Asset duplicate = new Notebook(
                "NB-002",
                "Dell Latitude 5540",
                LocalDate.of(2024, 1, 20),
                new BigDecimal("1799.00"),
                "Erfurt",
                32,
                "DL5540-8821",
                LocalDate.of(2026, 5, 1)
        );

        System.out.println(
                "\nAttempt to add duplicate asset NB-001: "
                        + inventory.add(duplicate)
        );

        Employee employee = new Employee(
                "P-1001",
                "Anna Mueller",
                "Sales"
        );

        Loan loan = Loan.start(
                notebook1,
                employee,
                14
        );

        System.out.println("\nLoan:");
        System.out.println(
                "Notebook loaned to: "
                        + notebook1.getCurrentUser().orElseThrow()
        );
        System.out.println(
                "Due date: "
                        + loan.getExpectedReturnDate()
        );
        System.out.println(
                "Notebook available: "
                        + notebook1.isAvailable()
        );

        try {
            Loan.start(notebook1, employee, 7);
        } catch (IllegalStateException exception) {
            System.out.println(
                    "Loan could not be started: The notebook is already loaned."
            );
        }

        System.out.println("\nAvailable devices:");

        for (Loanable loanable
                : report.availableAssets(inventory.getAll())) {

            Asset asset = (Asset) loanable;
            System.out.println("  " + asset.getDescription());
        }

        LocalDate referenceDate = LocalDate.now();

        System.out.println(
                "\nMaintenance due on "
                        + referenceDate
                        + ":"
        );

        for (Maintainable maintainable
                : report.maintenanceDue(
                inventory.getAll(),
                referenceDate
        )) {

            Asset asset = (Asset) maintainable;

            System.out.println(
                    "  " + asset.getDescription()
                            + " since "
                            + maintainable.getNextMaintenanceDate()
            );
        }

        Location office = new Location(
                "Eisenach Office",
                "A-2.14"
        );

        office.addAsset(notebook1);
        office.addAsset(monitor1);

        loan.addLogEntry("Loan confirmed by AssetHub");

        System.out.println(
                "\nLocation "
                        + office.getName()
                        + " manages "
                        + office.getAssets().size()
                        + " assets."
        );

        System.out.println(
                "Loan contains "
                        + loan.getLogEntries().size()
                        + " log entries."
        );

        loan.complete(LocalDate.now());

        System.out.println("\nReturn:");
        System.out.println(
                "Returned on: "
                        + loan.getActualReturnDate()
        );
        System.out.println(
                "Notebook available: "
                        + notebook1.isAvailable()
        );
        System.out.println(
                "Loan now contains "
                        + loan.getLogEntries().size()
                        + " log entries."
        );
    }
}
