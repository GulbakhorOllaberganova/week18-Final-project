package com.promineotech.bike.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.bike.dao.BikeOrderDao;
import com.promineotech.bike.entity.Bike;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class DefaultBikeOrderservice implements BikeOrderService {
  
@Autowired
private BikeOrderDao bikeOrderDao;

@Override
public List<Bike> fetchAllBikes() {                                                  
  List<Bike> bike = bikeOrderDao.fetchAllBikes();
  if(bike.isEmpty()) {
    String msg = String.format("We have no bikes :(");
        throw new NoSuchElementException(msg);
  }

  return bike;
}
@Transactional(readOnly = true)
@Override
public List<Bike> fetchBikeModel(String model) {                    
  log.info("Fetch Bike in service layer");
  
  List<Bike> bike = bikeOrderDao.fetchBikeByModel(model);
        if(bike.isEmpty()) {
            String msg = String.format("No bike was found with model=%s", model);
              throw new NoSuchElementException(msg);
  } 
     return bike;
}
@Override
public List<Bike> fetchBikeByBrand(String brand) {                           
  log.info("Fetch Customer in service layer");
  
  List<Bike> bike = bikeOrderDao.fetchBikeByBrand(brand);
        if(brand.isEmpty()) {
            String msg = String.format("No brand was found with brand=%s", brand);
              throw new NoSuchElementException(msg);
  } 
     return bike;
}

@Override
public Bike createBike (String model, String brand, BigDecimal bikePrice) {           
  log.info("create bikes in service layer");
  return bikeOrderDao.createBike (model, brand, bikePrice);
}

@Override
public Bike updateBike(Long bikeId, Bike updatedBike) {                   
  log.info("updates bike in service layer");
  return bikeOrderDao.updateBike(bikeId, updatedBike);
}




}
