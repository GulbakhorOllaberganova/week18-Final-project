package com.promineotech.bike.dao;


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
import com.promineotech.bike.entity.Buyer;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DefaultBuyerDao implements BuyerDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  public List<Buyer> fetchAllBuyers() {                                                  
    log.info("In Buyer.dao.fetchAllBuyers");
    
      String sql = ""
          +"SELECT * "
          + "FROM buyer;";
      // @formatter:on  
      
      
      Map<String, Object> params = new HashMap<>();
      return jdbcTemplate.query(sql,
          new RowMapper<Buyer>() {
            @Override
            public Buyer mapRow(ResultSet rs, int rowNum) throws SQLException {
              // @formatter:off
              return Buyer.builder()
                  .customerId(rs.getLong("customer_id"))
                  .firstName(rs.getString("first_name"))
                  .lastName(rs.getString("last_name"))
                  .phone(rs.getString("phone"))
                  .build();
              // @formatter:on
            }});
    }
 
  public class BuyerResultSetExtractor implements ResultSetExtractor<Buyer> {
    @Override
    public Buyer extractData(ResultSet rs) 
        throws SQLException, DataAccessException {
      rs.next();
      // @formatter:off
      return Buyer.builder()
          .customerId(rs.getLong("customer_pk"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .phone(rs.getString("phone"))
          .build();
      // @formatter:on
    }
  } 
  protected Buyer fetchBuyerById(int customerId) {
    //@formatter:off
    String sql = ""
          +"SELECT * "
          + "FROM buyer "
          + "WHERE customer_id = :customer_id";
    // @formatter:on
 
    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
  
    return 
        jdbcTemplate.query(sql,params, new BuyerResultSetExtractor());
  }
 
  public List<Buyer> fetchABuyer(String firstName, String lastName) {                    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM buyer "
        + "WHERE first_name = :first_name AND last_name = :last_name";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("first_name", firstName);
    params.put("last_name", lastName);
    
    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
          @Override
          public Buyer mapRow(ResultSet rs, int rowNum) throws SQLException {
         // @formatter:off
            return Buyer.builder()
                .customerId(rs.getLong("customer_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .phone(rs.getString("phone"))
                .build();
         // @formatter:on
          }});

  }
  
  public List<Buyer> fetchBuyerByFirstName(String firstName) {                           
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM buyer "
        + "WHERE first_name = :first_name";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("first_name", firstName);
    
    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
          @Override
          public Buyer mapRow(ResultSet rs, int rowNum) throws SQLException {
         // @formatter:off
            return Buyer.builder()
                .customerId(rs.getLong("customer_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .phone(rs.getString("phone"))
                .build();
         // @formatter:on
          }});
  }
 

  public Buyer createBuyer(String firstName, String lastName, String phone) {            
    SqlParams sqlparams = new SqlParams();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    sqlparams.sql = ""
        + "INSERT into buyer "
        + "(first_name, last_name, phone)" 
        + "VALUES (:first_name, :last_name, :phone)" ;
    sqlparams.source.addValue("first_name", firstName);
    sqlparams.source.addValue("last_name", lastName);
    sqlparams.source.addValue("phone", phone);
    

    jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
    return Buyer.builder()
        .customerId(keyHolder.getKey().longValue())
        .firstName(firstName)
        .lastName(lastName)
        .phone(phone)
        .build();
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
  
  
  
  
  public Buyer updateBuyer(Long customerId, Buyer updatedBuyer) {                   
    // @formatter:off
    String sql = ""
        + "UPDATE buyer "
        + "SET "
        + "first_name = :first_name, "
        + "last_name = :last_name, "
        + "phone = :phone "
        + "WHERE customer_id = :customer_id;";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("first_name", updatedBuyer.getFirstName());
    params.put("last_name", updatedBuyer.getLastName());
    params.put("phone", updatedBuyer.getPhone());
    params.put("customer_id", customerId);
    
 
      if (jdbcTemplate.update(sql, params) == 0) {
       throw new NoSuchElementException("update failed :( ");
      }
    return Buyer.builder()
        .customerId(customerId)
        .firstName(updatedBuyer.getFirstName())
        .lastName(updatedBuyer.getLastName())
        .phone(updatedBuyer.getPhone())
        .build();
    
  }
  
 
  @Override
  public void deleteBuyer(Long deleteId) {
    // @formatter:off
    String sql = ""
        + "DELETE FROM buyer "
        + "WHERE customer_id = :customer_id;";
    // @formatter:on    
       
    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", deleteId); 
   
    
    if (jdbcTemplate.update(sql,  params) == 0)
      throw new NoSuchElementException();
    
    
  }

}
