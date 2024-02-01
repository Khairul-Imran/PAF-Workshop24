package com.example.day24workshopfinalised.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.day24workshopfinalised.Utils;
import com.example.day24workshopfinalised.Exceptions.OrderException;
import com.example.day24workshopfinalised.Models.Order;
import com.example.day24workshopfinalised.Models.OrderDetails;
import com.example.day24workshopfinalised.Services.OrderServices;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    
    // Get the homepage, and any existing order information from the session.
    @GetMapping(path = {"/", "/index.html"})
    public ModelAndView getIndex(HttpSession httpSession) {
        ModelAndView mav = new ModelAndView("index");
        Order order = getOrder(httpSession);

        mav.addObject(Utils.ATTR_ORDER, order);

        return mav;
    }

    // Posting the order, to be stored in the session.
    @PostMapping(path = "/order")
    public ModelAndView sendOrder(HttpSession httpSession, @ModelAttribute Order inputOrder, @RequestBody MultiValueMap<String, String> form) {
        ModelAndView mav = new ModelAndView("index");
        Order order = getOrder(httpSession);
        OrderDetails orderDetails = new OrderDetails();

        order.setCustomerName(inputOrder.getCustomerName());
        order.setShipAddress(inputOrder.getShipAddress());
        order.setNotes(inputOrder.getNotes());
        order.setTax(inputOrder.getTax());

        orderDetails.setProduct(form.getFirst(Utils.CTRL_PRODUCT));
        orderDetails.setUnitPrice(Double.parseDouble(form.getFirst(Utils.CTRL_UNIT_PRICE)));
        orderDetails.setDiscount(Double.parseDouble(form.getFirst(Utils.CTRL_DISCOUNT)));
        orderDetails.setQuantity(Integer.parseInt(form.getFirst(Utils.CTRL_QUANTITY)));
        order.getOrderDetails().add(orderDetails);

        mav.addObject(Utils.ATTR_ORDER, order);

        return mav;
    }

    // Posting the order and OrderDetails into the repos.
    @PostMapping(path = "/checkout")
    public ModelAndView sendCheckout(HttpSession httpSession) {
        ModelAndView mav = new ModelAndView("index");
        Order order = getOrder(httpSession);

        try {
            orderServices.createOrder(order);
            mav.setStatus(HttpStatusCode.valueOf(200));
            httpSession.invalidate();
            mav.addObject(Utils.ATTR_ORDER, new Order());
        } catch (OrderException oe) {
            System.out.println("Encountered an error here while checking out.");
            mav.setStatus(HttpStatusCode.valueOf(500));
            mav.addObject(Utils.ATTR_ORDER, order);
        }
        
        return mav;
    }

    // Method for us to get the order information from the session (if any).
    private Order getOrder(HttpSession httpSession) {
        Object object = httpSession.getAttribute(Utils.ATTR_ORDER);
        Order order;

        if (null == object) {
            order = new Order();
            httpSession.setAttribute(Utils.ATTR_ORDER, order);
        } else {
            order = (Order) object;
        }

        return order;
    }
    
}
