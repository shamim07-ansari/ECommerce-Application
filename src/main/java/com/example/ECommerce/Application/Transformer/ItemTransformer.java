package com.example.ECommerce.Application.Transformer;

import com.example.ECommerce.Application.Dto.ResponseDto.ItemResponseDto;
import com.example.ECommerce.Application.Model.Customer;
import com.example.ECommerce.Application.Model.Item;
import com.example.ECommerce.Application.Model.Product;

public class ItemTransformer {
    public static Item itemRequestDtoToItem(int requiredQuantity) {
        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }

    public static ItemResponseDto itemToItemResponseDto(Item item) {
        return ItemResponseDto.builder()
                .quantityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();
    }
}
