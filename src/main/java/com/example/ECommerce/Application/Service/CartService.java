package com.example.ECommerce.Application.Service;

import com.example.ECommerce.Application.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Application.Model.Item;

public interface CartService {
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);
}
