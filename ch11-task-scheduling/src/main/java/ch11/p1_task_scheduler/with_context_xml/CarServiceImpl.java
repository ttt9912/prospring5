package ch11.p1_task_scheduler.with_context_xml;

import ch11.entity.Car;
import ch11.repository.CarRepository;
import ch11.service.CarService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service("carService")
@Repository
@Transactional
class CarServiceImpl implements CarService {
    private final static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    public Car save(final Car car) {
        return carRepository.save(car);
    }

    @Override // scheduled in context xml
    public void updateCarAgeJob() {
        final List<Car> cars = findAll();
        final LocalDate now = LocalDate.now();

        logger.info("Car age update job started");

        cars.forEach(car -> {
            int years = Period.between(car.getManufactureDate(), now)
                    .getYears();
            car.setAge(years);
            save(car);
            logger.info("Car age updated {}", car);
        });

        logger.info("Car age update job completed successfully");
    }
}
