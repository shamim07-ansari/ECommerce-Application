package com.example.ECommerce.Application.Service.Impl;

import com.example.ECommerce.Application.Dto.RequestDto.CustomerRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.Application.Model.Cart;
import com.example.ECommerce.Application.Model.Customer;
import com.example.ECommerce.Application.Repository.CustomerRepository;
import com.example.ECommerce.Application.Service.CustomerService;
import com.example.ECommerce.Application.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        // Request Dto -> Entity
        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);
        Customer savedCustomer = customerRepository.save(customer); // save both customer and cart

        // Entity -> Response Dto
        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }
}
