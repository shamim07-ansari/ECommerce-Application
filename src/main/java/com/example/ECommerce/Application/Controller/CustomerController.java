package com.example.ECommerce.Application.Controller;

import com.example.ECommerce.Application.Dto.RequestDto.CustomerRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.Application.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
    }
}



