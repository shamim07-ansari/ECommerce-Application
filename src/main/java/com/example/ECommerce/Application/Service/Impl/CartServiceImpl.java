package com.example.ECommerce.Application.Service.Impl;

import com.example.ECommerce.Application.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.ECommerce.Application.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Application.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Application.Exception.CustomerNotFoundException;
import com.example.ECommerce.Application.Exception.EmptyCartException;
import com.example.ECommerce.Application.Exception.InsufficientQuantityException;
import com.example.ECommerce.Application.Exception.InvalidCardException;
import com.example.ECommerce.Application.Model.*;
import com.example.ECommerce.Application.Repository.*;
import com.example.ECommerce.Application.Service.CartService;
import com.example.ECommerce.Application.Service.OrderService;
import com.example.ECommerce.Application.Transformer.CardTransformer;
import com.example.ECommerce.Application.Transformer.CartTransformer;
import com.example.ECommerce.Application.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {

        // checking customer and product
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

    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws InvalidCardException, EmptyCartException, InsufficientQuantityException, CustomerNotFoundException {

        // checking customer
        Customer customer = customerRepository.findByEmailId(checkoutCartRequestDto.getEmailId());
        if(customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist !!");
        }

        // checking card details
        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date date = new Date();
        if(card == null || card.getCvv() != checkoutCartRequestDto.getCvv() || date.before(card.getValidTill())) {
            throw new InvalidCardException("Sorry! You can't use this card");
        }

        Cart cart = customer.getCart();
        if(cart.getItems().size() == 0) {
            throw new EmptyCartException("Cart is empty !!");
        }

        try {
            Ordered ordered = orderService.placeOrder(cart, card);
            resetCart(cart);

            Ordered savedOrder = orderRepository.save(ordered);
            customer.getOrderedList().add(savedOrder);
            return OrderTransformer.orderToOrderResponseDto(savedOrder);
        }
        catch (InsufficientQuantityException e) {
            throw e;
        }
    }

    private void resetCart(Cart cart) {
        cart.setCartTotal(0);
        for(Item item : cart.getItems()) {
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());
    }
}
