package com.mafei.example.config;

import com.mafei.example.domain.PaymentEvent;
import com.mafei.example.domain.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@EnableStateMachineFactory
@Configuration
@Slf4j
public class PaymentStateConfig extends StateMachineConfigurerAdapter<PaymentState, PaymentEvent> {


    @Override
    public void configure(StateMachineStateConfigurer<PaymentState, PaymentEvent> states) throws Exception {

        states.withStates().initial(PaymentState.NEW)
                .states(EnumSet.allOf(PaymentState.class))
                //happy end
                .end(PaymentState.AUTH)
                //unhappy end
                .end(PaymentState.PRE_AUTH_ERROR)
                //unhappy end
                .end(PaymentState.AUTH_ERROR);
    }


    @Override
    public void configure(StateMachineTransitionConfigurer<PaymentState, PaymentEvent> transitions) throws Exception {
        transitions
                //not state change here
                .withExternal().source(PaymentState.NEW).target(PaymentState.NEW).event(PaymentEvent.PRE_AUTHORIZE)
                .and()
                .withExternal().source(PaymentState.NEW).target(PaymentState.PRE_AUTH).event(PaymentEvent.PRE_AUTH_APPROVED)
                .and()
                .withExternal().source(PaymentState.NEW).target(PaymentState.PRE_AUTH_ERROR).event(PaymentEvent.PRE_AUTH_DECLINED)


        ;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<PaymentState, PaymentEvent> config) throws Exception {
        super.configure(config);
        config.withConfiguration().listener(new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<PaymentState, PaymentEvent> from, State<PaymentState, PaymentEvent> to) {
                super.stateChanged(from, to);
                log.info("changed {} {}", from.getId(), to.getId());
            }
        });

    }
}
