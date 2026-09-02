package de.bsz.assethub.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Loan {

    private final Asset asset;
    private final Loanable loanableAsset;

    // Association: the loan references an existing employee without controlling the employee's lifecycle.
    private final Employee employee;

    private final LocalDate issueDate;
    private final LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    // Composition: log entries are created and owned exclusively by their loan.
    private final List<LogEntry> logEntries = new ArrayList<>();

    private Loan(
            Asset asset,
            Loanable loanableAsset,
            Employee employee,
            LocalDate issueDate,
            LocalDate expectedReturnDate) {

        this.asset = asset;
        this.loanableAsset = loanableAsset;
        this.employee = employee;
        this.issueDate = issueDate;
        this.expectedReturnDate = expectedReturnDate;

        addLogEntry("Asset issued to " + employee.getName());
    }

    public static Loan start(
            Asset asset,
            Employee employee,
            int days) {

        Asset validatedAsset = Objects.requireNonNull(
                asset,
                "Asset must not be null"
        );

        Employee validatedEmployee = Objects.requireNonNull(
                employee,
                "Employee must not be null"
        );

        if (!(validatedAsset instanceof Loanable loanableAsset)) {
            throw new IllegalArgumentException(
                    "Asset is not loanable"
            );
        }

        loanableAsset.loanTo(validatedEmployee, days);

        LocalDate expectedReturnDate = loanableAsset
                .getDueDate()
                .orElseThrow(() -> new IllegalStateException(
                        "Loanable asset did not provide a due date"
                ));

        LocalDate issueDate = expectedReturnDate.minusDays(days);

        return new Loan(
                validatedAsset,
                loanableAsset,
                validatedEmployee,
                issueDate,
                expectedReturnDate
        );
    }

    public Asset getAsset() {
        return asset;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void complete(LocalDate returnDate) {
        LocalDate validatedReturnDate = Objects.requireNonNull(
                returnDate,
                "Return date must not be null"
        );

        if (actualReturnDate != null) {
            throw new IllegalStateException(
                    "Loan has already been completed"
            );
        }

        if (validatedReturnDate.isBefore(issueDate)) {
            throw new IllegalArgumentException(
                    "Return date must not be before issue date"
            );
        }

        loanableAsset.returnAsset();
        actualReturnDate = validatedReturnDate;

        addLogEntry("Asset returned");
    }

    public void addLogEntry(String message) {
        logEntries.add(new LogEntry(
                LocalDateTime.now(),
                Objects.requireNonNull(
                        message,
                        "Message must not be null"
                )
        ));
    }

    public List<LogEntry> getLogEntries() {
        return Collections.unmodifiableList(logEntries);
    }
}
