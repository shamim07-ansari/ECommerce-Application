package com.example.ECommerce.Application.Transformer;

import com.example.ECommerce.Application.Dto.RequestDto.CardRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CardResponseDto;
import com.example.ECommerce.Application.Model.Card;

public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto) {
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .validTill(cardRequestDto.getValidTill())
                .cardType(cardRequestDto.getCardType())
                .build();
    }

    public static CardResponseDto cardToCardResponseDto(Card card) {
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .build();
    }
}
