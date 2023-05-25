package com.example.ECommerce.Application.Service.Impl;

import com.example.ECommerce.Application.Dto.RequestDto.CardRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.CardResponseDto;
import com.example.ECommerce.Application.Exception.CustomerNotFoundException;
import com.example.ECommerce.Application.Model.Card;
import com.example.ECommerce.Application.Model.Customer;
import com.example.ECommerce.Application.Repository.CardRepository;
import com.example.ECommerce.Application.Repository.CustomerRepository;
import com.example.ECommerce.Application.Service.CardService;
import com.example.ECommerce.Application.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());
        if(customer == null) {
            throw new CustomerNotFoundException("Invalid email id !!");
        }
        // Request Dto -> Entity
        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        // save customer and card
        Customer savedCustomer = customerRepository.save(customer);

        // Entity -> Response Dto
        return CardTransformer.cardToCardResponseDto(card);
    }
}
