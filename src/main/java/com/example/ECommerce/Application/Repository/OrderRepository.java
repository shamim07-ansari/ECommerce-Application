package com.example.ECommerce.Application.Repository;

import com.example.ECommerce.Application.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordered, Integer> {
}
