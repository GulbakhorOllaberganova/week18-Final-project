package com.promineotech.bike.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.bike.entity.Bike;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultBikeOrderDao implements BikeOrderDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate; 
  @Override
  public List<Bike>fetchAllBikes(){
    log.info("in bikeOrderDao.fetchallBikes");
    
    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM bikes;";
    //@formatter:on
    
    Map<String, Object> params = new HashMap<>();
    return jdbcTemplate.query(sql, 
        new RowMapper<Bike>() {
      @Override
      public Bike mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Bike.builder()
            .bikeId(rs.getLong("bike_id"))
            .model(rs.getString("bike_model"))
            .brand(rs.getString("brand"))
            .bikePrice(rs.getBigDecimal("bike_price"))
            .build();
        // @formatter:on
      }});
  }
  public class BikeResultSetExtractor implements ResultSetExtractor<Bike> {
    @Override
    public Bike extractData(ResultSet rs) 
        throws SQLException, DataAccessException {
      rs.next();
      // @formatter:off
      return Bike.builder()
          .bikeId(rs.getLong("bike_id"))
          .model(rs.getString("bike_model"))
          .brand(rs.getString("brand"))
          .bikePrice(rs.getBigDecimal("bike_price"))
          .build();
      // @formatter:on
    }
  } 
  
  public Bike fetchBikeById(Long bikeId) {
    //@formatter:off
    String sql = ""
          +"SELECT * "
          + "FROM bikes "
          + "WHERE bike_id = :bike_id";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("bike_id", bikeId);
  
    return 
        jdbcTemplate.query(sql,params, new BikeResultSetExtractor());
  }
  
  public List<Bike> fetchBikeByModel(String model) {                           
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM bikes "
        + "WHERE bike_model = :bike_model";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("bike_model", model);
    
    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
          @Override
          public Bike mapRow(ResultSet rs, int rowNum) throws SQLException {
         // @formatter:off
            return Bike.builder()
                .bikeId(rs.getLong("bike_id"))
                .model(rs.getString("bike_model"))
                .brand(rs.getString("brand"))
                .bikePrice(rs.getBigDecimal("bike_price"))
                .build();
         // @formatter:on
          }});
  }
  
  public List<Bike> fetchBikeByBrand(String brand) {                           
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM bikes "
        + "WHERE brand = :brand";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("brand", brand);
    
    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
          @Override
          public Bike mapRow(ResultSet rs, int rowNum) throws SQLException {
         // @formatter:off
            return Bike.builder()
                .bikeId(rs.getLong("bike_id"))
                .model(rs.getString("bike_model"))
                .brand(rs.getString("brand"))
                .bikePrice(rs.getBigDecimal("bike_price"))
                .build();
         // @formatter:on
          }});
  }

  
  public Bike createBike(String model, String brand, BigDecimal bikePrice) {            
    SqlParams sqlparams = new SqlParams();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    sqlparams.sql = ""
        + "INSERT into bikes "
        + "(bike_model, brand, bike_price)" 
        + "VALUES (:bike_model, :brand, :bike_price)" ;
    sqlparams.source.addValue("bike_model", model);
    sqlparams.source.addValue("brand", brand);
    sqlparams.source.addValue("bike_price", bikePrice);
    

    jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
    return Bike.builder()
        .bikeId(keyHolder.getKey().longValue())
        .model(model)
        .brand(brand)
        .bikePrice(bikePrice)
        .build();
  }
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
  
  public Bike updateBike(Long bikeId, Bike updatedBike) {                   
    // @formatter:off
    String sql = ""
        + "UPDATE bikes "
        + "SET "
        + "bike_model = :bike_model, "
        + "brand = :brand, "
        + "bike_price = :bike_price "
        + "WHERE bike_id = :bike_id;";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("bike_model", updatedBike.getModel());
    params.put("brand", updatedBike.getBrand());
    params.put("bike_price", updatedBike.getBikePrice());
    params.put("bike_id", bikeId);
    
  
      if (jdbcTemplate.update(sql, params) == 0) {
       throw new NoSuchElementException("update failed :( ");
      }
    return Bike.builder()
        .bikeId(bikeId)
        .model(updatedBike.getModel())
        .brand(updatedBike.getBrand())
        .bikePrice(updatedBike.getBikePrice())
        .build();
    
  }
 
 

}
