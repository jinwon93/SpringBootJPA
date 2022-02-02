package jpabook.jpashop.api;



//import jpabook.jpashop.domain.Order;
//
//import jpabook.jpashop.dto.SimpleOrderDto;
//import jpabook.jpashop.repository.OrderRepositoryOld;
//import jpabook.jpashop.repository.OrderSearch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;



import java.util.List;
import java.util.stream.Collectors;

//@RestController
//@RequiredArgsConstructor
//public class OrderSimpleApiController {
//
//
//    private  final OrderRepositoryOld orderRepository;
//
//
//    @GetMapping("/api/v2/simple-orders")
//    public List<SimpleOrderDto> orderV2(){
//        //ORDER
//        // N+!1  -->  1 + 회원N(2) + 배송N (2)
//        // fetch join으로 성능개선 (개조)
//        //지연로딩은 영속성 컨텍스트에서 조회하므로  이미 조회된 경우 쿼리를 생락한다
//        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
//        List<SimpleOrderDto> result = orders.stream()
//                .map(o -> new SimpleOrderDto(o))
//                .collect(Collectors.toList());
//
//        return result;
//    }
//
//
//    @GetMapping("/api/v3/simple-orders")
//    public List<SimpleOrderDto> orderV3(){
//        List<Order> orders = orderRepository.findAllWithMemberDelivery();
//        List<SimpleOrderDto> result = orders.stream()
//                .map(o -> new SimpleOrderDto(o))
//                .collect(Collectors.toList());
//
//        return result;
//    }
//
//}
