package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.util.List;

public class AssetReport {

    public BigDecimal totalResidualValue(List<Asset> assets) {
        return assets.stream()
                .map(Asset::calculateResidualValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
