package ch4.p8_profiles.beans.highschool;

import ch4.p8_profiles.beans.Food;
import ch4.p8_profiles.beans.FoodProvierService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProvierService {

    @Override
    public List<Food> provideLunchSet() {
        final List<Food> lunchSet = new ArrayList<>();
        lunchSet.add(new Food("Coke"));
        lunchSet.add(new Food("Hamburger"));
        lunchSet.add(new Food("Fries"));
        return lunchSet;
    }
}
