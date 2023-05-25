package com.example.ECommerce.Application.Repository;

import com.example.ECommerce.Application.Enum.Category;
import com.example.ECommerce.Application.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryAndPrice(Category category, int price);
}
