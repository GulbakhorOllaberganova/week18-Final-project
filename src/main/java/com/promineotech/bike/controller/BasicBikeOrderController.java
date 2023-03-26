package com.promineotech.bike.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.bike.entity.Bike;
import com.promineotech.bike.service.BikeOrderService;

@RestController

public class BasicBikeOrderController implements BikeOrderController {
  
@Autowired
private BikeOrderService bikeOrderService;

@Override
public List<Bike> fetchAllBikes() {                                                    
  return bikeOrderService.fetchAllBikes();
}

@Override
public List<Bike> fetchBikeByModel(String model) {                      
  return bikeOrderService.fetchBikeModel(model);
}

@Override
public List<Bike> fetchBikeByBrand(String brand) {                             
  return bikeOrderService.fetchBikeByBrand(brand);}

@Override
public Bike createBike(String model, String brand, BigDecimal bikePrice) {              
  return bikeOrderService.createBike(model, brand, bikePrice);
}

@Override
public Bike updateBike(Long bikeId, @Valid Bike updatedBike) {              
  return bikeOrderService.updateBike(bikeId, updatedBike);
}
}
