package com.example.ECommerce.Application.Service;

import com.example.ECommerce.Application.Dto.RequestDto.ProductRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Application.Enum.Category;
import com.example.ECommerce.Application.Exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price);
}
