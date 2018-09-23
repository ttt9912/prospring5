package ch11.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;

    @Column(name = "age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(final String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(final LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

        return String.format("%s{License: %s, Manufacturer: %s, Manufacture Date: %s, Age: %d}",
                getClass().getSimpleName(),
                licensePlate,
                manufacturer,
                dtf.format(manufactureDate),
                age);
    }
}
