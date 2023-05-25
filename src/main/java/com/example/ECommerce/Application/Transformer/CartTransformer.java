package com.example.ECommerce.Application.Transformer;

import com.example.ECommerce.Application.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Application.Dto.ResponseDto.ItemResponseDto;
import com.example.ECommerce.Application.Model.Cart;
import com.example.ECommerce.Application.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto cartToCartResponseDto(Cart cart) {
        List<ItemResponseDto> list = new ArrayList<>();
        for(Item item : cart.getItems()) {
            list.add(ItemTransformer.itemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(list)
                .build();
    }
}
