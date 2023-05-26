package com.example.ECommerce.Application.Service;

import com.example.ECommerce.Application.Dto.RequestDto.OrderRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Application.Exception.CustomerNotFoundException;
import com.example.ECommerce.Application.Exception.InsufficientQuantityException;
import com.example.ECommerce.Application.Exception.InvalidCardException;
import com.example.ECommerce.Application.Exception.ProductNotFoundException;
import com.example.ECommerce.Application.Model.Card;
import com.example.ECommerce.Application.Model.Cart;
import com.example.ECommerce.Application.Model.Ordered;

public interface OrderService {

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException;

    Ordered placeOrder(Cart cart, Card card) throws InsufficientQuantityException;
}
