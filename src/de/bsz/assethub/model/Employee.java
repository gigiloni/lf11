package de.bsz.assethub.model;

import java.util.Objects;

public class Employee {

    private final String personnelNumber;
    private String name;
    private String department;

    public Employee(
            String personnelNumber,
            String name,
            String department) {

        this.personnelNumber = Objects.requireNonNull(
                personnelNumber,
                "Personnel number must not be null"
        );
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.department = Objects.requireNonNull(
                department,
                "Department must not be null"
        );
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name must not be null");
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = Objects.requireNonNull(
                department,
                "Department must not be null"
        );
    }

    @Override
    public String toString() {
        return name + " (" + department + ")";
    }
}
