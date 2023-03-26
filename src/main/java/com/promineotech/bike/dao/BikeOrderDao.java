package com.promineotech.bike.dao;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.bike.entity.Bike;

public interface BikeOrderDao {
 
 List<Bike> fetchAllBikes();                                                          
  
  List<Bike> fetchBikeByModel(String model);                            
  
  List<Bike> fetchBikeByBrand(String brand);

  Bike createBike(String model, String brand, BigDecimal bikePrice);                    

  Bike updateBike(Long bikeId, Bike updatedBike);

}
