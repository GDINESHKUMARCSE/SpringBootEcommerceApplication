package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository){

       this.customerRepository = customerRepository;

    }


    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Order order = purchase.getOrder();
        System.out.println("order"+order);

        String orderTrackingNumber = generateOrderTrackingNumber();
        System.out.println("ordertrackingnumber"+orderTrackingNumber);
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item ->order.add(item));
        System.out.println("orderitems"+orderItems);
        order.setBillingAddress(purchase.getBillingAddress());
        System.out.println("billingaddress"+purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        System.out.println("shippingaddress"+purchase.getShippingAddress());
        Customer customer = purchase.getCustomer();
        System.out.println("customer"+customer);
        customerRepository.save(customer);



        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
