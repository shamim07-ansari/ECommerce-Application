package com.example.ECommerce.Application.Dto.ResponseDto;

import com.example.ECommerce.Application.Enum.Category;
import com.example.ECommerce.Application.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String productName;

    String sellerName;

    Category category;

    Integer price;

    ProductStatus productStatus;
}
