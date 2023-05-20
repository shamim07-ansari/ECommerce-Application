package com.example.ECommerce.Application.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "email_id", unique = true, nullable = false)
    String emailId;

    @Column(name = "mobile_no", unique = true, nullable = false)
    String mobileNo;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
}
