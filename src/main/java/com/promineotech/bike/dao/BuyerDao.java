/**
 * 
 */
package com.promineotech.bike.dao;

import java.util.List;
import com.promineotech.bike.entity.Buyer;

public interface BuyerDao {
  
 List<Buyer> fetchAllBuyers();                                                          
  
  List<Buyer> fetchABuyer(String firstName, String lastName);                            
  
  List<Buyer> fetchBuyerByFirstName(String firstName);                                   

  Buyer createBuyer(String firstName, String lastName, String phone);                    

  Buyer updateBuyer(Long customerId, Buyer updatedBuyer);

 
  void deleteBuyer(Long deleteId);

  
               


}
