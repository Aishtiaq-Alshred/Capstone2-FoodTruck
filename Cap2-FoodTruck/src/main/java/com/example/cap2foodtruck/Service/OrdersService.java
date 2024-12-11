package com.example.cap2foodtruck.Service;

import com.example.cap2foodtruck.ApiResponse.ApiException;
import com.example.cap2foodtruck.Model.Menu;
import com.example.cap2foodtruck.Model.Orders;
import com.example.cap2foodtruck.Repository.MenuRepository;
import com.example.cap2foodtruck.Repository.OrdersRepository;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {


    private final OrdersRepository orderRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(Orders order) {
        orderRepository.save(order);
    }

    public void updateOrder(Integer id, Orders updatedOrder) {
        Optional<Orders> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new ApiException("Order not found.");
        }

        Orders existingOrder = optionalOrder.get();
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setMenuItemIds(updatedOrder.getMenuItemIds());
        existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
        orderRepository.save(existingOrder);
    }


    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    private  final MenuRepository menuRepository;

    public void createOrder(Integer customerId, Integer foodTruckId, List<Integer> menuItemIds) {
        Orders order = new Orders();
        order.setCustomerId(customerId);
        order.setFoodTruckId(foodTruckId);
        order.setMenuItemIds(menuItemIds);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        double totalPrice = 0;
        for (Integer menuId : menuItemIds) {
            Optional<Menu> optionalMenu = menuRepository.findById(menuId);
            if (optionalMenu.isEmpty()) {
                throw new ApiException("Menu item not found.");
            }
            totalPrice += optionalMenu.get().getPrice();
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }


    public void addReview(Integer orderId, Integer rating) {
        Optional<Orders> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new ApiException("Order not found.");
        }

        Orders existingOrder = order.get();
        existingOrder.setReview(rating);
        orderRepository.save(existingOrder);
    }

    public void payOrder(Integer orderId) {
        Optional<Orders> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new ApiException("Order not found.");
        }
        Orders existingOrder = order.get();
        if (!existingOrder.getStatus().equals("ACCEPTED")) {
            throw new ApiException("Order must be accepted before payment.");
        }
        existingOrder.setStatus("PAID");
        orderRepository.save(existingOrder);
    }


    public void updateOrderStatus(Integer orderId, String status) {
        Optional<Orders> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new ApiException("Order not found.");
        }
        Orders existingOrder = order.get();
        existingOrder.setStatus(status.toUpperCase());
        orderRepository.save(existingOrder);
    }

    public void cancelOrder(Integer orderId) {
        Optional<Orders> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new ApiException("Order not found.");
        }

        Orders existingOrder = order.get();
        existingOrder.setStatus("CANCELLED");
        orderRepository.save(existingOrder);
    }



    public String getOrderStatus(Integer orderId) {
        Optional<Orders> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new ApiException("Order not found.");
        }
        return order.get().getStatus();
    }


    public List<Orders> getTopOrders() {
        return orderRepository.findTopOrders();
    }

    public List<Orders> getOrdersByCustomer(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }


    public String getOrderStats(Integer foodTruckId) {
        List<Orders> orders = orderRepository.findByFoodTruckId(foodTruckId);
        int totalOrders = orders.size();
        double totalRevenue = 0;
        for (Orders order : orders) {
            totalRevenue += order.getTotalPrice();
        }
        return "Total Orders: " + totalOrders + ", Total Revenue: " + totalRevenue;
    }




}
