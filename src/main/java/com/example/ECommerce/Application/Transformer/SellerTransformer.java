package com.example.ECommerce.Application.Transformer;

import com.example.ECommerce.Application.Dto.RequestDto.SellerRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Application.Model.Seller;

public class SellerTransformer {
    public static Seller sellerRequestDtoSeller(SellerRequestDto sellerRequestDto) {
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobileNo(sellerRequestDto.getMobileNo())
                .build();
    }
    public static SellerResponseDto sellerToSellerResponseDto(Seller seller) {
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobileNo(seller.getMobileNo())
                .build();
    }
}
