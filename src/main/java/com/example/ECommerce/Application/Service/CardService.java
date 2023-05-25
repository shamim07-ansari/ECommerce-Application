package com.example.ECommerce.Application.Service;

import com.example.ECommerce.Application.Dto.RequestDto.CardRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CardResponseDto;
import com.example.ECommerce.Application.Exception.CustomerNotFoundException;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;
}
