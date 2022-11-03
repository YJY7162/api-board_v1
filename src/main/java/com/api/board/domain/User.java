package com.api.board.domain;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class User {

    private int userSeq;

    @NotBlank
    private String userId;

    @NotBlank
    private String userPassword;


}
