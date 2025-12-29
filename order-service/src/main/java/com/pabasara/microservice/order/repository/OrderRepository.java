package com.pabasara.microservice.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pabasara.microservice.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
