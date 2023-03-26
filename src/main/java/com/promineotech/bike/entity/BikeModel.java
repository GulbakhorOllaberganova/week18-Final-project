package com.promineotech.bike.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeModel {
  private String MountainBike;
  private String RoadBike;
  private String ElectricBike;
}
