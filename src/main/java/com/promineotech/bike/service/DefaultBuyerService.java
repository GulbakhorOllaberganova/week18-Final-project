/**
 * 
 */
package com.promineotech.bike.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.bike.dao.BuyerDao;
import com.promineotech.bike.entity.Buyer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultBuyerService implements BuyerService {
  @Autowired
  private BuyerDao buyerDao;
  
  //Default Customer Service class implements everything from the Customer Service interface.
  //Next it all get's handed off to the Customer Data Access Object (Dao).
  
  @Override
  public List<Buyer> fetchAllBuyers() {                                                  
    List<Buyer> buyer = buyerDao.fetchAllBuyers();
    if(buyer.isEmpty()) {
      String msg = String.format("We have no buyers :(");
          throw new NoSuchElementException(msg);
    }
   // Collections.sort((List<Employee>) employees);
    return buyer;
  }
  
  @Transactional(readOnly = true)
  @Override
  public List<Buyer> fetchABuyer(String firstName, String lastName) {                    
    log.info("Fetch Buyer in service layer");
    
    List<Buyer> buyer = buyerDao.fetchABuyer(firstName, lastName);
          if(buyer.isEmpty()) {
              String msg = String.format("No buyer was found with firstName=%s and lastName=%s", firstName, lastName);
                throw new NoSuchElementException(msg);
    } 
       return buyer;
  }
  
  @Override
  public List<Buyer> fetchBuyerByFirstName(String firstName) {                           
    log.info("Fetch Buyer in service layer");
    
    List<Buyer> buyer = buyerDao.fetchBuyerByFirstName(firstName);
          if(buyer.isEmpty()) {
              String msg = String.format("No buyer was found with firstName=%s", firstName);
                throw new NoSuchElementException(msg);
    } 
       return buyer;
  }
  

  @Override
  public Buyer createBuyer (String firstName, String lastName, String phone) {           
    log.info("create Buyers in service layer");
    return buyerDao.createBuyer (firstName, lastName, phone);
  }

  public Buyer updateBuyer(Long customerId, Buyer updatedBuyer) {                   
    log.info("updates Buyer in service layer");
    return buyerDao.updateBuyer(customerId, updatedBuyer);
  }

  
  @Override
  public void deleteBuyer(Long deleteId) {
      log.info("The deleteBuyer method was called with customerId={},deleteId"); 
     buyerDao.deleteBuyer(deleteId);
  }
}
