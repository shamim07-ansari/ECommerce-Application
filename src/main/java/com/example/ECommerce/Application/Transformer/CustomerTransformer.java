package com.example.ECommerce.Application.Transformer;

import com.example.ECommerce.Application.Dto.RequestDto.CustomerRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.Application.Model.Customer;

public class CustomerTransformer {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {

        return Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .mobileNo(customerRequestDto.getMobileNo())
                .gender(customerRequestDto.getGender())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobilNo(customer.getMobileNo())
                .build();
    }
}
