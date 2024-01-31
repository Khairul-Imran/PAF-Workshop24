package com.example.day24workshopfinalised.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.example.day24workshopfinalised.Models.OrderDetails;

@Repository
public class OrderDetailsRepository {
    
    @Autowired
    private JdbcTemplate template;

    // Insert order details (multiple).
    public boolean insertOrderDetails(List<OrderDetails> listOfOrderDetails) {
        System.out.println("Repository: These are the list of order details we want to insert: " + listOfOrderDetails.toString());

        int count = 0;
        int orderId = 0;

        // Getting the order id relating to these order details. (Latest order id created)
        SqlRowSet sqlRowSet = template.queryForRowSet(Queries.SQL_GET_LATEST_ORDER_ID);
        while (sqlRowSet.next()) {
            orderId = sqlRowSet.getInt("order_id");
        }

        for (OrderDetails orderDetail : listOfOrderDetails) {
            int insertAttempt = template.update(Queries.SQL_INSERT_ORDER_DETAILS,
                orderDetail.getProduct(),
                orderDetail.getUnitPrice(),
                orderDetail.getDiscount(),
                orderDetail.getQuantity(),
                orderId
            );
            count += insertAttempt;
        }
        
        if (count == listOfOrderDetails.size()) {
            System.out.println("Insertion of order details was successful.");
            return true;
        }

        return false;
    }

    // Get order details by order id.
    public List<OrderDetails> findOrderDetailsByOrderId(Integer orderId) {
        System.out.println("Repository: Finding order details for Order ID: " + orderId);
        return template.query(Queries.SQL_GET_ORDER_DETAILS_BY_ORDER_ID, BeanPropertyRowMapper.newInstance(OrderDetails.class), orderId);
    }

}
