package com.javatechie.dto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public record MessageResponse( String varMessage){
}
