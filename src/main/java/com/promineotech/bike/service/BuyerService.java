package com.promineotech.bike.service;

import java.util.List;
import com.promineotech.bike.entity.Buyer;

public interface BuyerService {
 
  List<Buyer> fetchAllBuyers();                                                         
  
  List<Buyer> fetchABuyer(String firstName, String lastName);                            

  List<Buyer> fetchBuyerByFirstName(String firstName);                                   

  Buyer createBuyer(String firstName, String lastName, String phone);                    
  
  Buyer updateBuyer(Long customerId, Buyer updatedBuyer);


 void deleteBuyer(Long deletedId);                           

}
