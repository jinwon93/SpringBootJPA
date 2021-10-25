package jpabook.jpashop.queryservice;

import jpabook.jpashop.repository.OrderRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepositoryOld orderRepository;

    //test
//    public List<QueryDtoService> orderV2_1(){
//
//        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
//
//        List<QueryD> result = orders.stream()
//            .map(o -> new OrderDto(o))
//            .collect(Collectors.toList());
//        return result;
//    }
}
