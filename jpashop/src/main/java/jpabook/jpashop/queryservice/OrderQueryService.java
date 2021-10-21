package jpabook.jpashop.queryservice;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.queryservice.queryDto.QueryDtoService;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;

    //test
    public List<QueryDtoService> orderV2_1(){

        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        List<OrderDto> result = orders.stream()
            .map(o -> new OrderDto(o))
            .collect(Collectors.toList());
        return result;
    }
}
