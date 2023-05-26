package com.example.ECommerce.Application.Service.Impl;

import com.example.ECommerce.Application.Dto.RequestDto.ProductRequestDto;
import com.example.ECommerce.Application.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Application.Enum.Category;
import com.example.ECommerce.Application.Exception.SellerNotFoundException;
import com.example.ECommerce.Application.Model.Product;
import com.example.ECommerce.Application.Model.Seller;
import com.example.ECommerce.Application.Repository.ProductRepository;
import com.example.ECommerce.Application.Repository.SellerRepository;
import com.example.ECommerce.Application.Service.ProductService;
import com.example.ECommerce.Application.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {

        // checking seller
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());
        if(seller == null) {
            throw new SellerNotFoundException("EmailId is not registered !!");
        }

        // Request Dto -> Entity
        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        // save product
        Seller savedSeller = sellerRepository.save(seller);
        Product savedProduct = savedSeller.getProducts().get(savedSeller.getProducts().size()-1);

        // Entity -> Response Dto
        return ProductTransformer.productToProductResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price) {
        List<Product> productList = productRepository.findByCategoryAndPrice(category, price);
        List<ProductResponseDto> list = new ArrayList<>();

        for(Product product : productList) {
            list.add(ProductTransformer.productToProductResponseDto(product));
        }
        return list;
    }
}
