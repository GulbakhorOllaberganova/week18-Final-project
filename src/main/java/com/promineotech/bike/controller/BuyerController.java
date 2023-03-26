/**
 * 
 */
package com.promineotech.bike.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.bike.entity.Buyer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/buyer")
@OpenAPIDefinition(info = @Info(title = "Buyers Service"), servers = {
        @Server(url = "http://localhost:8080", description = "Local server.")
})
public interface BuyerController {

  @Operation(
          summary = "Returns a list of buyers",
          description = "Returns a list of buyers all first and last name",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Success",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = Buyer.class))),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Bad Input",
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
  List<Buyer> fetchAllBuyers(       
  );

  
  // @formatter:off
  @Operation( //!!!
      summary = "Return a buyer",
      description = "Returns a buyer given their first and last name",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "All buyers are returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Buyer.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Buyers found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "firstName",
              allowEmptyValue = false,
              required = false,
              description = "Buyer's first name"),
          @Parameter(name = "lastName",
          allowEmptyValue = false,
          required = false,
          description = "Buyer's last name"),      }
  )
  @GetMapping("/aBuyer")
  @ResponseStatus(code = HttpStatus.OK)
  List<Buyer> fetchABuyer(                                                               
      @RequestParam(required = false)
      String firstName,
      @RequestParam(required = false)
      String lastName);             
  
  // @formatter:off
  @Operation( //!!!
      summary = "Returns a Buyer when you have only their first name",
      description = "Returns the buyer entered",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A Buyer gets returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Buyer.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Buyer with that name found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "firstName",
              allowEmptyValue = false,
              required = false,
              description = "Buyer's first name"),
     }
  )
  @GetMapping("/firstName")
  @ResponseStatus(code = HttpStatus.OK)
  List<Buyer> fetchBuyerByFirstName(                                                     
      @RequestParam(required = false)
      String firstName);

  
  // @formatter:off
  @Operation(
      summary = "Creates a new Buyer",
      description = "Returns the created Buyer",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A new Buyer has been added",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Buyer.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Buyer were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "firstName", 
              allowEmptyValue = false, 
              required = false, 
              description = "The buyer's first name"),
          @Parameter(name = "lastName", 
          allowEmptyValue = false, 
          required = false, 
          description = "The buyer's last name"),
          @Parameter(name = "phone", 
          allowEmptyValue = false, 
          required = false, 
          description = "The buyer's phone number.")
          }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Buyer createBuyer( @RequestParam(required = false)String firstName,  @RequestParam(required = false)String lastName, @RequestParam(required = false) String phone);   
  
  @Operation(
      summary = "updates a Buyer",
      description = "Returns the updated Buyer",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns updated Buyer",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Buyer.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Buyer were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      }, 
          parameters = {
              @Parameter(name = "customerId", 
                  allowEmptyValue = false, 
                  required = false, 
                  description = "The Buyer's customerId within our database")
      }
  )
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Buyer updateBuyer(                                                                     
      @RequestParam(required = false) Long customerId, 
      @Valid @RequestBody Buyer updatedBuyer); 
  
  @Operation(
      summary = "Deletes a Buyer",
      description = "Deletes an Buyer given an id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Buyer was deleted",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Buyer.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Buyer were found with the input criteria",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "customerId", 
              allowEmptyValue = false, 
              required = false, 
              description = "The Buyer's id (i.e., 3)"),
      }
  )
   
   

  @DeleteMapping("/customerId")
  @ResponseStatus(code = HttpStatus.OK)
  void deleteBuyer(@RequestParam(required = false)Long deleteId);


}

