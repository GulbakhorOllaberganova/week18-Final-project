package com.promineotech.bike.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.bike.entity.Bike;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/bikes")
@OpenAPIDefinition(info = @Info(title = "Bike Order Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface BikeOrderController {
  // @formatter:off
  @Operation(
      summary = "Returns a list of bike",
      description = "Returns a list of bike for order",
      responses = { 
          @ApiResponse(
              responseCode = "201", 
              description = "The list of bike for order is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Bike.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "Bad Input, the request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "Not Found", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "Unplanned Error", 
              content = @Content(mediaType = "application/json"))
          }
      )
  
  @GetMapping("/all")
  @ResponseStatus(code = HttpStatus.OK)
  List<Bike> fetchAllBikes();

  
  // @formatter:off
  @Operation( //!!!
      summary = "Return a bike model",
      description = "Returns a bike given their model",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "All bike models are returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Bike.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Bikes found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "model",
              allowEmptyValue = false,
              required = false,
              description = "Bike's model"),
              }
  )
  @GetMapping("/BikeByModel")
  @ResponseStatus(code = HttpStatus.OK)
  List<Bike> fetchBikeByModel(  @RequestParam(required = false)
  String model); 
  
  // @formatter:off
  @Operation( //!!!
      summary = "Returns a Bike when you have only their brand",
      description = "Returns the brand entered",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A bike gets returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Bike.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Bike with that brand found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "brand",
              allowEmptyValue = false,
              required = false,
              description = "Bikes's brand"),
     }
  )
  @GetMapping("/brand")
  @ResponseStatus(code = HttpStatus.OK)
  List<Bike> fetchBikeByBrand(                                                     
      @RequestParam(required = false)
      String brand);


  // @formatter:off
  @Operation(
      summary = "Creates a new bike",
      description = "Returns the created bike",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A new bike has been added",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Bike.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Bike were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "model", 
              allowEmptyValue = false, 
              required = false, 
              description = "The bike's model"),
          @Parameter(name = "brand", 
          allowEmptyValue = false, 
          required = false, 
          description = "The bikess brand"),
          @Parameter(name = "bikePrice", 
          allowEmptyValue = false, 
          required = false, 
          description = "The bike's price")
          }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Bike createBike(@RequestParam(required = false)String model, @RequestParam(required = false)String brand, @RequestParam(required = false)BigDecimal bikePrice);                    
  
  
  
  // @formatter:off
  @Operation(
      summary = "updates a Bike",
      description = "Returns the updated Bike",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns updated Bike",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Bike.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Bike were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      }, 
          parameters = {
              @Parameter(name = "bikeId", 
                  allowEmptyValue = false, 
                  required = false, 
                  description = "The Bike's Id within our database")
      }
  )
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Bike updateBike(                                                                     
      @RequestParam(required = false)Long bikeId, 
      @Valid @RequestBody Bike updatedBike);

 
  

}
