import de.bsz.assethub.model.Asset;
import de.bsz.assethub.model.AssetReport;
import de.bsz.assethub.model.Monitor;
import de.bsz.assethub.model.Notebook;
import de.bsz.assethub.model.Printer;
import de.bsz.assethub.model.Server;
import de.bsz.assethub.model.Tablet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        List<Asset> assets = new ArrayList<>();
        Set<Asset> uniqueAssets = new HashSet<>();

        assets.add(new Notebook(
                "NB-001",
                "Lenovo ThinkPad T14",
                LocalDate.of(2023, 4, 12),
                new BigDecimal("1499.99"),
                "Eisenach",
                16,
                "PF3X9K2"
        ));

        assets.add(new Notebook(
                "NB-002",
                "Dell Latitude 5540",
                LocalDate.of(2024, 1, 20),
                new BigDecimal("1799.00"),
                "Erfurt",
                32,
                "DL5540-8821"
        ));

        assets.add(new Printer(
                "PR-001",
                "HP LaserJet M404",
                LocalDate.of(2022, 5, 10),
                new BigDecimal("399.99"),
                "Ilmenau",
                "HP 59A",
                false
        ));

        assets.add(new Printer(
                "PR-002",
                "Brother MFC-L8690CDW",
                LocalDate.of(2023, 8, 15),
                new BigDecimal("649.00"),
                "Erfurt",
                "Brother TN-421",
                true
        ));

        assets.add(new Server(
                "SRV-001",
                "Dell PowerEdge R750",
                LocalDate.of(2021, 2, 5),
                new BigDecimal("7500.00"),
                "Data Center Ilmenau",
                2,
                2
        ));

        assets.add(new Server(
                "SRV-002",
                "HPE ProLiant DL380",
                LocalDate.of(2020, 11, 18),
                new BigDecimal("9200.00"),
                "Data Center Erfurt",
                2,
                4
        ));

        assets.add(new Monitor(
                "MON-001",
                "Dell UltraSharp U2723QE",
                LocalDate.of(2024, 3, 1),
                new BigDecimal("629.00"),
                "Eisenach",
                27.0
        ));

        assets.add(new Monitor(
                "MON-002",
                "Samsung Odyssey G5",
                LocalDate.of(2023, 9, 7),
                new BigDecimal("349.99"),
                "Ilmenau",
                32.0
        ));

        assets.add(new Tablet(
                "TAB-001",
                "Apple iPad Air",
                LocalDate.of(2025, 2, 14),
                new BigDecimal("799.00"),
                "Eisenach",
                256
        ));

        assets.add(new Tablet(
                "TAB-002",
                "Samsung Galaxy Tab S10",
                LocalDate.of(2024, 10, 3),
                new BigDecimal("949.00"),
                "Erfurt",
                512
        ));

        System.out.println("Inventarliste:");

        for (Asset asset : assets) {
            System.out.println(asset.getInventoryLine());
        }

        AssetReport report = new AssetReport();

        System.out.println(
                "\nGesamtrestwert: "
                        + report.totalResidualValue(assets)
                        + " EUR"
        );

        System.out.println("\nNach Restwert absteigend sortiert:");

        report.sortedByResidualValue(assets)
                .forEach(asset ->
                        System.out.println(asset.getInventoryLine())
                );

        Asset original = assets.get(0);

        Asset notebookWithSameInventoryNumber = new Notebook(
                "NB-001",
                "Lenovo ThinkPad T14",
                LocalDate.of(2023, 4, 12),
                new BigDecimal("1499.99"),
                "Eisenach",
                16,
                "PF3X9K2"
        );

        uniqueAssets.add(original);

        boolean duplicateWasAdded = uniqueAssets.add(notebookWithSameInventoryNumber);

        System.out.println(
                "\nZweites Objekt mit NB-001 aufgenommen: "
                        + duplicateWasAdded
        );
    }
}
