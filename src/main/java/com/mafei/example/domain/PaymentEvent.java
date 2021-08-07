package com.mafei.example.domain;

import lombok.Getter;

@Getter
public enum PaymentEvent {
    PRE_AUTHORIZE,
    PRE_AUTH_APPROVED,
    PRE_AUTH_DECLINED,
    AUTHORIZE,
    AUTH_PROVED,
    AUTH_DECLINED;
}
