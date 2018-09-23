package ch11.p1_simple_task;

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

import java.util.List;

@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService {
    private final static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    private boolean done;

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

    @Override
    public void updateCarAgeJob() {

    }

    @Override
    public boolean isDone() {
        return done;
    }
}
