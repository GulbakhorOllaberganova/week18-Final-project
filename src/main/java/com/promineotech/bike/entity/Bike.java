package com.promineotech.bike.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Bike {

  private Long bikeId;
  private String model;
  private String brand;
  private BigDecimal bikePrice;
  
  
 
}
