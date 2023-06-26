package com.ahmad.controller;

import com.ahmad.dto.AuthRequest;
import com.ahmad.entity.Product;
import com.ahmad.entity.UserInfo;
import com.ahmad.service.JwtService;
import com.ahmad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return service.addUser(userInfo);
    }
    @GetMapping("/all")
    public List<Product> getAllTheProducts() {
        System.out.println("im in admin");
        return service.getProducts();
    }
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable int id) {
        System.out.println("im in user");
        return service.getProduct(id);
    }
    @PostMapping("/newProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product productInfo){
        System.out.println("IM HERE");
        return service.addProduct( productInfo );
    }




    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getName());
            System.out.println(authentication.getAuthorities());
            System.out.println(authRequest);
            return ResponseEntity.status(HttpStatus.FOUND).body("token: " + token );
        } else {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED).body( "wrong info" );
        }


    }
}
