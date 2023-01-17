package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
@Import(AspectV6Advice.class)
public class AopTest {

    @Autowired
    OrderService service;
    @Autowired
    OrderRepository repository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(service));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(repository));
    }

    @Test
    void success() {
        service.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> service.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }
}
