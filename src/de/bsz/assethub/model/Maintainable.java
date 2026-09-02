package de.bsz.assethub.model;

import java.time.LocalDate;

public interface Maintainable {

    LocalDate getLastMaintenanceDate();

    int getMaintenanceIntervalInMonths();

    default LocalDate getNextMaintenanceDate() {
        return getLastMaintenanceDate()
                .plusMonths(getMaintenanceIntervalInMonths());
    }

    default boolean isMaintenanceDue(LocalDate referenceDate) {
        return !getNextMaintenanceDate().isAfter(referenceDate);
    }
}
