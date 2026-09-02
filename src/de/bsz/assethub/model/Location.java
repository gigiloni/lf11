package de.bsz.assethub.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Location {

    private String name;
    private String room;
    // Aggregation: the location stores existing assets that can continue to exist independently.
    private final List<Asset> assets = new ArrayList<>();

    public Location(String name, String room) {
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.room = Objects.requireNonNull(room, "Room must not be null");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name must not be null");
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = Objects.requireNonNull(room, "Room must not be null");
    }

    public void addAsset(Asset asset) {
        Objects.requireNonNull(asset, "Asset must not be null");

        if (assets.contains(asset)) {
            return;
        }

        assets.add(asset);
    }

    public boolean removeAsset(Asset asset) {
        return assets.remove(asset);
    }

    public List<Asset> getAssets() {
        return Collections.unmodifiableList(assets);
    }
}
