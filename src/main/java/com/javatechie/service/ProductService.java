package com.javatechie.service;

import com.javatechie.dto.MessageResponse;
import com.javatechie.entity.Product;
import com.javatechie.entity.UserInfo;
import com.javatechie.repository.ProductsRepository;
import com.javatechie.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
private ProductsRepository productsRepository;
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostConstruct
//    public void loadProductsFromDB() {
//        productList = IntStream.rangeClosed(1, 100)
//                .mapToObj(i -> Product.builder()
//                        .productId(i)
//                        .name("product " + i)
//                        .qty(new Random().nextInt(10))
//                        .price(new Random().nextInt(5000)).build()
//                ).collect(Collectors.toList());
//    }



    public List<Product> getProducts() {
        return productsRepository.findAll();
    }

    public Optional<Product> getProduct(int id) {
        return productsRepository.findById(id);
    }
    // adding a new product
    public ResponseEntity<?> addProduct(Product productInfo){
    productsRepository.save(productInfo);
    return ResponseEntity.status(HttpStatus.CREATED ).body( new MessageResponse( "new product created" ) );
    }

//     adding a new user
    public ResponseEntity<?> addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return ResponseEntity.status( HttpStatus.CREATED).body(new MessageResponse("new user created"));
    }
}
