package com.example.ECommerce.Application.Service;

import com.example.ECommerce.Application.Dto.RequestDto.CustomerRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
