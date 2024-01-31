package com.example.day24workshopfinalised.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    
    Integer id;
    String product;
    Double unitPrice;
    Double discount;
    Integer quantity;
    Integer orderId;

}
