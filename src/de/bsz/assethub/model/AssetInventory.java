package de.bsz.assethub.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AssetInventory {

    private final List<Asset> assets = new ArrayList<>();

    public boolean add(Asset asset) {
        Objects.requireNonNull(asset, "Asset must not be null");

        if (assets.contains(asset)) {
            return false;
        }

        return assets.add(asset);
    }

    public boolean remove(String inventoryNumber) {
        Objects.requireNonNull(
                inventoryNumber,
                "Inventory number must not be null"
        );

        return assets.removeIf(
                asset -> asset.getInventoryNumber().equals(inventoryNumber)
        );
    }

    public Optional<Asset> find(String inventoryNumber) {
        Objects.requireNonNull(
                inventoryNumber,
                "Inventory number must not be null"
        );

        return assets.stream()
                .filter(asset ->
                        asset.getInventoryNumber().equals(inventoryNumber)
                )
                .findFirst();
    }

    public int removeFullyDepreciated() {
        int previousSize = assets.size();

        assets.removeIf(
                asset -> asset.calculateResidualValue()
                        .compareTo(BigDecimal.ZERO) == 0
        );

        return previousSize - assets.size();
    }

    public int size() {
        return assets.size();
    }

    public List<Asset> getAll() {
        return Collections.unmodifiableList(assets);
    }
}
