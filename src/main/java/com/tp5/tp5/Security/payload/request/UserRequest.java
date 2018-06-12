package com.tp5.tp5.Security.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequest {

    @NotNull
    String name;
    @NotNull
    String password;
}

