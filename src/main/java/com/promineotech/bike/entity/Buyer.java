/**
 * 
 */
package com.promineotech.bike.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {
  private Long customerId;
  private String firstName;
  private String lastName;
  private String phone;
  
 
}
