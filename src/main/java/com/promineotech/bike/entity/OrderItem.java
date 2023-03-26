package com.promineotech.bike.entity;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
  
  @NotNull
private int orderId;
  
  @NotNull 
private int bikeId;

}
 
