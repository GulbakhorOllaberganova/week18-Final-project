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
public class Order {
  private int orderId;
  private int customerId;
  private BigDecimal orderTotal;
  
  public int getOrderId() {
    return orderId;
  }


}
