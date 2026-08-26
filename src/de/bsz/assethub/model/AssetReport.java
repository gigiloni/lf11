package de.bsz.assethub.model;

import java.math.BigDecimal;
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
        for (Asset asset : assets) {
            System.out.printf(
                    "%-12s | %s%n",
                    asset.getTypeName(),
                    asset.getInventoryLine()
            );
        }
    }
}
