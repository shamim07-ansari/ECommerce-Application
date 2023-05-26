package com.example.ECommerce.Application.Service;

import com.example.ECommerce.Application.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.ECommerce.Application.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Application.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Application.Exception.CustomerNotFoundException;
import com.example.ECommerce.Application.Exception.EmptyCartException;
import com.example.ECommerce.Application.Exception.InsufficientQuantityException;
import com.example.ECommerce.Application.Exception.InvalidCardException;
import com.example.ECommerce.Application.Model.Item;

public interface CartService {
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);

    OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws InvalidCardException, EmptyCartException, InsufficientQuantityException, CustomerNotFoundException;
}
