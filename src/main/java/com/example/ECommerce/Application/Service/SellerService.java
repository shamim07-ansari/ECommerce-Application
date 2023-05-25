package com.example.ECommerce.Application.Service;

import com.example.ECommerce.Application.Dto.RequestDto.SellerRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.SellerResponseDto;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);
}
