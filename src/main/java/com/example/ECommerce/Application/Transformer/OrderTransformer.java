package com.example.ECommerce.Application.Transformer;

import com.example.ECommerce.Application.Dto.RequestDto.OrderRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.ItemResponseDto;
import com.example.ECommerce.Application.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Application.Model.Customer;
import com.example.ECommerce.Application.Model.Item;
import com.example.ECommerce.Application.Model.Ordered;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {

    public static Ordered orderRequestDtoToOrder(Item item, Customer customer) {
        return Ordered.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity() * item.getProduct().getPrice())
                .build();
    }

    public static OrderResponseDto orderToOrderResponseDto(Ordered ordered) {

        List<ItemResponseDto> list = new ArrayList<>();
        for(Item item : ordered.getItems()) {
            list.add(ItemTransformer.itemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderDate(ordered.getOrderDate())
                .cardUsed(ordered.getCardUsed())
                .customerName(ordered.getCustomer().getName())
                .totalValue(ordered.getTotalValue())
                .orderNo(ordered.getOrderNo())
                .items(list)
                .build();
    }
}
