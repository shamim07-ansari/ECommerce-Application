package com.example.ECommerce.Application.Controller;

import com.example.ECommerce.Application.Dto.RequestDto.ProductRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Application.Enum.Category;
import com.example.ECommerce.Application.Exception.SellerNotFoundException;
import com.example.ECommerce.Application.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) throws SellerNotFoundException {
        try {
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getAllProductsByCategoryAndPrice(@PathVariable("category") Category category,
                                                           @PathVariable("price") int price) {
        List<ProductResponseDto> list = productService.getAllProductsByCategoryAndPrice(category, price);
        return new ResponseEntity(list, HttpStatus.FOUND);
    }
}
