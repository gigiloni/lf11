package de.bsz.assethub.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class LoanStatus {

    private Employee currentUser;
    private LocalDate dueDate;

    public boolean isAvailable() {
        return currentUser == null;
    }

    public void loanTo(Employee employee, int days, LocalDate issueDate) {
        Objects.requireNonNull(employee, "Employee must not be null");
        Objects.requireNonNull(issueDate, "Issue date must not be null");

        if (!isAvailable()) {
            throw new IllegalStateException(
                    "Asset is already loaned to " + currentUser.getName()
            );
        }

        if (days <= 0 || days > Loanable.MAX_LOAN_DURATION_DAYS) {
            throw new IllegalArgumentException(
                    "Loan duration must be between 1 and "
                            + Loanable.MAX_LOAN_DURATION_DAYS
                            + " days"
            );
        }

        currentUser = employee;
        dueDate = issueDate.plusDays(days);
    }

    public void returnAsset() {
        currentUser = null;
        dueDate = null;
    }

    public Optional<Employee> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }

    public Optional<LocalDate> getDueDate() {
        return Optional.ofNullable(dueDate);
    }
}
