package com.example.day24workshopfinalised.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.day24workshopfinalised.Exceptions.OrderException;
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
    @Transactional (rollbackFor = OrderException.class)
    public boolean createOrder(Order order) throws OrderException {
        boolean result = ordersRepository.insertOrder(order) && orderDetailsRepository.insertOrderDetails(order.getOrderDetails());

        if (!result) {
            throw new OrderException();
        }

        return result;
    }


    // Find operations
    public List<OrderDetails> findOrderDetailsByOrderId(Integer orderId) {
        return orderDetailsRepository.findOrderDetailsByOrderId(orderId);
    }

    public List<Order> findOrderByOrderId(Integer orderId) {
        return ordersRepository.findOrderByOrderId(orderId);
    }


}
