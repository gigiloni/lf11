package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AssetReport {

    public BigDecimal totalResidualValue(List<Asset> assets) {
        return assets.stream()
                .map(Asset::calculateResidualValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Asset> sortedByResidualValue(List<Asset> assets) {
        return assets.stream()
                .sorted(
                        Comparator.comparing(Asset::calculateResidualValue)
                                .reversed()
                )
                .collect(Collectors.toList());
    }

    public void printInventoryList(List<Asset> assets) {
        assets.forEach(asset -> System.out.printf(
                "%-12s | %s%n",
                asset.getTypeName(),
                asset.getInventoryLine()
        ));
    }

    public List<Loanable> availableAssets(List<Asset> assets) {
        List<Loanable> availableAssets = new ArrayList<>();

        for (Asset asset : assets) {
            if (asset instanceof Loanable loanable && loanable.isAvailable()) {
                availableAssets.add(loanable);
            }
        }

        return availableAssets;
    }

    public List<Maintainable> maintenanceDue(
            List<Asset> assets,
            LocalDate referenceDate) {

        List<Maintainable> maintenanceDue = new ArrayList<>();

        for (Asset asset : assets) {
            if (asset instanceof Maintainable maintainable
                    && maintainable.isMaintenanceDue(referenceDate)) {
                maintenanceDue.add(maintainable);
            }
        }

        maintenanceDue.sort(
                Comparator.comparing(Maintainable::getNextMaintenanceDate)
        );

        return maintenanceDue;
    }

    public List<Loanable> overdueAssets(
            List<Asset> assets,
            LocalDate referenceDate) {

        List<Loanable> overdueAssets = new ArrayList<>();

        for (Asset asset : assets) {
            if (asset instanceof Loanable loanable
                    && loanable.isOverdue(referenceDate)) {
                overdueAssets.add(loanable);
            }
        }

        overdueAssets.sort(Comparator.comparing(
                loanable -> loanable.getDueDate().orElseThrow()
        ));

        return overdueAssets;
    }
}
