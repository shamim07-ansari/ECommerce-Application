package com.example.ECommerce.Application.Service.Impl;

import com.example.ECommerce.Application.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Application.Exception.CustomerNotFoundException;
import com.example.ECommerce.Application.Exception.InsufficientQuantityException;
import com.example.ECommerce.Application.Exception.OutOfStockException;
import com.example.ECommerce.Application.Exception.ProductNotFoundException;
import com.example.ECommerce.Application.Model.Customer;
import com.example.ECommerce.Application.Model.Item;
import com.example.ECommerce.Application.Model.Product;
import com.example.ECommerce.Application.Repository.CustomerRepository;
import com.example.ECommerce.Application.Repository.ItemRepository;
import com.example.ECommerce.Application.Repository.ProductRepository;
import com.example.ECommerce.Application.Service.ItemService;
import com.example.ECommerce.Application.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {
        // checking product is present
        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(!productOptional.isPresent()) {
            throw new ProductNotFoundException("Product doesn't exist !!");
        }

        // checking customer is present
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist !!");
        }

        Product product = productOptional.get();
        // checking quantity is available or not
        if(product.getQuantity() == 0) {
            throw new OutOfStockException("Product is out of stock");
        }

        // checking quantity if less than required
        if(product.getQuantity() < itemRequestDto.getRequiredQuantity()) {
            throw new InsufficientQuantityException("Sorry! The required quantity is not available");
        }

        // Request Dto -> Entity
        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto.getRequiredQuantity());

        return item;
    }
}
