package com.javatechie.controller;

import com.javatechie.dto.AuthRequest;
import com.javatechie.entity.Product;
import com.javatechie.entity.UserInfo;
import com.javatechie.repository.ProductsRepository;
import com.javatechie.service.JwtService;
import com.javatechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> addNewUser(@RequestBody UserInfo userInfo) {
        System.out.println("printing userInfo: " + userInfo.toString());
        System.out.println("IM HERE DUDE!!");
        return service.addUser(userInfo);
    }

    @PostMapping("/newProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product productInfo){
        System.out.println("IM HERE");
        return service.addProduct( productInfo );
    }
    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Optional<Product> getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getName());
            System.out.println(authentication.getAuthorities());
            System.out.println(authRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("token: " + token );
        } else {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED).body( "wrong info" );
        }


    }
}
