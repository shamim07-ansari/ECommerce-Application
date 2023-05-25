package com.example.ECommerce.Application.Transformer;

import com.example.ECommerce.Application.Dto.RequestDto.ProductRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Application.Enum.ProductStatus;
import com.example.ECommerce.Application.Model.Product;

public class ProductTransformer {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .build();
    }
}
