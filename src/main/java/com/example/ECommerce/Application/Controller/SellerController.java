package com.example.ECommerce.Application.Controller;

import com.example.ECommerce.Application.Dto.RequestDto.SellerRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Application.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) {
        try {
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(sellerRequestDto.getName() + " Already Exist", HttpStatus.BAD_REQUEST);
        }
    }
}
