package com.example.day24workshopfinalised.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.day24workshopfinalised.Models.Order;
import com.example.day24workshopfinalised.Models.OrderDetails;
import com.example.day24workshopfinalised.Repositories.OrderDetailsRepository;
import com.example.day24workshopfinalised.Repositories.OrdersRepository;

@Service
public class OrderServices {
    
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    // Transaction - 
    


    // Find operations
    public List<OrderDetails> findOrderDetailsByOrderId(Integer orderId) {
        return orderDetailsRepository.findOrderDetailsByOrderId(orderId);
    }

    public List<Order> findOrderByOrderId(Integer orderId) {
        return ordersRepository.findOrderByOrderId(orderId);
    }


}
