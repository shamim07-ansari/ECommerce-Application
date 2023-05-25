package com.example.ECommerce.Application.Repository;

import com.example.ECommerce.Application.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmailId(String sellerEmailId);
}
