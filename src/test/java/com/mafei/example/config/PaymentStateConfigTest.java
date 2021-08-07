package com.mafei.example.config;

import com.mafei.example.domain.PaymentEvent;
import com.mafei.example.domain.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@Slf4j
@SpringBootTest
class PaymentStateConfigTest {

    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> stateMachineFactory;

    @Test
    void testPaymentStateConfig() {
        StateMachine<PaymentState, PaymentEvent> sm = stateMachineFactory.getStateMachine(UUID.randomUUID().toString());
        sm.start();
        System.out.println("State = " + sm.getState().getId());
        sm.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        sm.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        System.out.println("State = " + sm.getState().getId());
     /*   sm.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
        System.out.println("State = " + sm.getState().getId());

        sm.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
        System.out.println("State = " + sm.getState().getId());
*/


//        sm.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
//        System.out.println("State = " + sm.getState().getId());


    }


}