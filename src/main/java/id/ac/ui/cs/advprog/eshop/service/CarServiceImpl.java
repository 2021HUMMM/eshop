package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    public void validation(Car car){
        if (car.getCarName() == null || car.getCarName().isEmpty()) {
            throw new IllegalArgumentException("Car name cannot be empty");
        }
        if (car.getCarColor() == null || car.getCarColor().isEmpty()){
            throw new IllegalArgumentException("Car color cannot be empty");
        }

        if (car.getCarQuantity() < 0) {
            throw new IllegalArgumentException("Car quantity cannot be negative");
        }
    }

    @Override
    public Car create(Car car) {
        // TODO Auto-generated method stub
        validation(car);
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }
    @Override
    public Car findById(String carId) {
        Car car = carRepository.findById(carId);
        return car;
    }

    @Override
    public void update(String carId, Car car) {
        // TODO Auto-generated method stub
        carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        // TODO Auto-generated method stub
        carRepository.delete(carId);
    }

}
