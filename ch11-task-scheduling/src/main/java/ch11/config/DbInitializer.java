package ch11.config;

import ch11.entity.Car;
import ch11.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DbInitializer {
    private final static Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    @Autowired
    private CarRepository carRepository;

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization...");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        final Car car1 = new Car();
        car1.setLicensePlate("GRAVITY-0405");
        car1.setManufacturer("Ford");
        car1.setManufactureDate(LocalDate.parse("2006-09-12", formatter));
        carRepository.save(car1);

        final Car car2 = new Car();
        car2.setLicensePlate("CLARITY-0432");
        car2.setManufacturer("Toyota");
        car2.setManufactureDate(LocalDate.parse("2003-09-09", formatter));
        carRepository.save(car2);

        final Car car3 = new Car();
        car3.setLicensePlate("ROSIE-0402");
        car3.setManufacturer("Toyota");
        car3.setManufactureDate(LocalDate.parse("2017-04-16", formatter));
        carRepository.save(car3);

        logger.info("Database initialization finished.");
    }
}
