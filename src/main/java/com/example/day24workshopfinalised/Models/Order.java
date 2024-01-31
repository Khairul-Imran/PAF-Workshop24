package com.example.day24workshopfinalised.Models;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    
    Integer orderId;
    Date orderDate;
    String customerName;
    String shipAddress;
    String notes;
    Double tax;
    // Each order can have multiple order details.
    List<OrderDetails> orderDetails = new LinkedList<>();

}
