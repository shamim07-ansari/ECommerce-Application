package com.example.ECommerce.Application.Service.Impl;

import com.example.ECommerce.Application.Dto.RequestDto.OrderRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Application.Enum.ProductStatus;
import com.example.ECommerce.Application.Exception.CustomerNotFoundException;
import com.example.ECommerce.Application.Exception.InsufficientQuantityException;
import com.example.ECommerce.Application.Exception.InvalidCardException;
import com.example.ECommerce.Application.Exception.ProductNotFoundException;
import com.example.ECommerce.Application.Model.*;
import com.example.ECommerce.Application.Repository.CardRepository;
import com.example.ECommerce.Application.Repository.CustomerRepository;
import com.example.ECommerce.Application.Repository.OrderRepository;
import com.example.ECommerce.Application.Repository.ProductRepository;
import com.example.ECommerce.Application.Service.OrderService;
import com.example.ECommerce.Application.Transformer.ItemTransformer;
import com.example.ECommerce.Application.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException {

        // checking customer
        Customer customer = customerRepository.findByEmailId(orderRequestDto.getEmailId());
        if(customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist !!");
        }

        // checking product
        Optional<Product> productOptional = productRepository.findById(orderRequestDto.getProductId());
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("Sorry! Product doesn't exist");
        }

        Product product = productOptional.get();

        // checking quantity
        if(product.getQuantity() < orderRequestDto.getRequiredQuantity()) {
            throw new InsufficientQuantityException("Sorry! required quantity is not available");
        }

        // checking card
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date date = new Date();
        if(card == null || card.getCvv() != orderRequestDto.getCvv() || date.before(card.getValidTill())) {
            throw new InvalidCardException("Sorry! You can't use this card");
        }

        // set the quantity
        int newQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity == 0) {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

        // create item
        Item item = ItemTransformer.itemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);

        // create order
        Ordered ordered = OrderTransformer.orderRequestDtoToOrder(item, customer);
        String maskedCard = generateMaskedCard(card);
        ordered.setCardUsed(maskedCard);
        ordered.getItems().add(item);
        item.setOrdered(ordered);

        // save both order and entity
        Ordered savedOrder = orderRepository.save(ordered);

        customer.getOrderedList().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        // Entity -> Response Dto
        return OrderTransformer.orderToOrderResponseDto(savedOrder);
    }

    public Ordered placeOrder(Cart cart, Card card) throws InsufficientQuantityException {

        Ordered ordered = new Ordered();
        ordered.setOrderNo(String.valueOf(UUID.randomUUID()));
        ordered.setCardUsed(generateMaskedCard(card));

        int totalValue = 0;
        for(Item item : cart.getItems()) {
            Product product = item.getProduct();
            if(item.getRequiredQuantity() > product.getQuantity()) {
                throw new InsufficientQuantityException("Required quantity is not available !!");
            }
            totalValue += item.getRequiredQuantity() * product.getPrice();
            int newQuantity = product.getQuantity() - item.getRequiredQuantity();
            product.setQuantity(newQuantity);

            if(newQuantity == 0) {
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            item.setOrdered(ordered);
        }

        ordered.setTotalValue(totalValue);
        ordered.setItems(cart.getItems());
        ordered.setCustomer(cart.getCustomer());

        return ordered;
    }

    private static String generateMaskedCard(Card card) {
        String cardNo = "";
        String originalCardNo = card.getCardNo();

        for(int i=0; i<originalCardNo.length()-4; i++) {
            cardNo += "X";
        }
        cardNo += originalCardNo.substring(originalCardNo.length()-4);
        return cardNo;
    }
}
