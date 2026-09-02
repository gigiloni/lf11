package de.bsz.assethub.model;

import java.time.LocalDate;
import java.util.Optional;

public interface Loanable {

    int MAX_LOAN_DURATION_DAYS = 90;

    boolean isAvailable();

    void loanTo(Employee employee, int days);

    void returnAsset();

    Optional<Employee> getCurrentUser();

    Optional<LocalDate> getDueDate();

    default boolean isOverdue() {
        return isOverdue(LocalDate.now());
    }

    default boolean isOverdue(LocalDate referenceDate) {
        return getDueDate()
                .map(dueDate -> dueDate.isBefore(referenceDate))
                .orElse(false);
    }
}
