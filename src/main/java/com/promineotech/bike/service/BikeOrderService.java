/**
 * 
 */
package com.promineotech.bike.service;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.bike.entity.Bike;

public interface BikeOrderService {

  List<Bike> fetchAllBikes();

  List<Bike> fetchBikeModel(String model);

  List<Bike> fetchBikeByBrand(String brand);

  
  Bike createBike(String model, String brand, BigDecimal bikePrice);

 
  Bike updateBike(Long bikeId, Bike updatedBike);
}