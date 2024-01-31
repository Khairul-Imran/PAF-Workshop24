package com.example.day24workshopfinalised.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.day24workshopfinalised.Models.Order;

@Repository
public class OrdersRepository {
    
    @Autowired
    private JdbcTemplate template;

    // Insert order.
    public boolean insertOrder(Order order) {
        System.out.println("Repository: This is the order we want to insert: " + order.toString());

        int insertAttempt = 0;
        insertAttempt = template.update(Queries.SQL_INSERT_ORDER,
            order.getCustomerName(),
            order.getShipAddress(),
            order.getNotes(),
            order.getTax()
        );

        if (insertAttempt > 0) {
            System.out.println("Insert of order was successful.");
            return true;
        }

        return false;
    }

    // Get order by order id. (Tried using list here)
    public List<Order> findOrderByOrderId(Integer orderId) {
        System.out.println("Repository: Finding order: " + orderId);
        return template.query(Queries.SQL_GET_ORDER_BY_ORDER_ID, BeanPropertyRowMapper.newInstance(Order.class), orderId);
    }

}
