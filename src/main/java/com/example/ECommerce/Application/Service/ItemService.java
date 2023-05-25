package com.example.ECommerce.Application.Service;

import com.example.ECommerce.Application.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Application.Exception.CustomerNotFoundException;
import com.example.ECommerce.Application.Exception.InsufficientQuantityException;
import com.example.ECommerce.Application.Exception.OutOfStockException;
import com.example.ECommerce.Application.Exception.ProductNotFoundException;
import com.example.ECommerce.Application.Model.Item;

public interface ItemService {
    Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException;
}
