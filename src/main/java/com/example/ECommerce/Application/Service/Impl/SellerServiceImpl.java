package com.example.ECommerce.Application.Service.Impl;

import com.example.ECommerce.Application.Dto.RequestDto.SellerRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Application.Model.Seller;
import com.example.ECommerce.Application.Repository.SellerRepository;
import com.example.ECommerce.Application.Service.SellerService;
import com.example.ECommerce.Application.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        Seller seller = SellerTransformer.sellerRequestDtoSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }
}
