package com.promineotech.bike.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.bike.entity.Buyer;
import com.promineotech.bike.service.BuyerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class BasicBuyerController implements BuyerController {
@Autowired
private BuyerService buyerService;



@Override
public List<Buyer> fetchAllBuyers() {                                                    
return buyerService.fetchAllBuyers();
}

@Override
public List<Buyer> fetchABuyer(String firstName, String lastName) {                      
return buyerService.fetchABuyer(firstName, lastName);
}

@Override
public List<Buyer> fetchBuyerByFirstName(String firstName) {                             
return buyerService.fetchBuyerByFirstName(firstName);}

@Override
public Buyer createBuyer(String firstName, String lastName, String phone) {              
return buyerService.createBuyer(firstName, lastName, phone);
}

@Override
public Buyer updateBuyer(Long customerId, @Valid Buyer updatedBuyer) {              
return buyerService.updateBuyer(customerId, updatedBuyer);
}
@Override
public void deleteBuyer(Long deleteId) {
  log.debug("customerId={}", deleteId);
  buyerService.deleteBuyer(deleteId);
}

}
