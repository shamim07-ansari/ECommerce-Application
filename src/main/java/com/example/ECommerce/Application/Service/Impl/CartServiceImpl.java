package com.example.ECommerce.Application.Service.Impl;

import com.example.ECommerce.Application.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Application.Model.Cart;
import com.example.ECommerce.Application.Model.Customer;
import com.example.ECommerce.Application.Model.Item;
import com.example.ECommerce.Application.Model.Product;
import com.example.ECommerce.Application.Repository.CartRepository;
import com.example.ECommerce.Application.Repository.CustomerRepository;
import com.example.ECommerce.Application.Repository.ProductRepository;
import com.example.ECommerce.Application.Service.CartService;
import com.example.ECommerce.Application.Transformer.CardTransformer;
import com.example.ECommerce.Application.Transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity() * product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);

        // save both cart and item
        Cart savedCart = cartRepository.save(cart);
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);

        product.getItems().add(savedItem);

        // Entity -> Response Dto
        return CartTransformer.cartToCartResponseDto(savedCart);
    }
}
