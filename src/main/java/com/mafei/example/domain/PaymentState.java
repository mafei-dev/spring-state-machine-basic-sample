package com.mafei.example.domain;

import lombok.Getter;


@Getter
public enum PaymentState {
    NEW, PRE_AUTH, PRE_AUTH_ERROR, AUTH, AUTH_ERROR;
}
