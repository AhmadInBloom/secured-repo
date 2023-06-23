package com.ahmad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq{
    private String name;
    private String email;
    private String password;
    private String roles;
}
