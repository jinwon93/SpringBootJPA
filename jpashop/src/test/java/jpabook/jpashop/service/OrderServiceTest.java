package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public  void 상품주문() throws  Exception{
        //given
        Member member = new Member();
        member.setName("member1");
        member.setAddress(new Address("seoul" , "river" ,"123-123"));
        em.persist(member);


//        Book book = new Book();
//        book.getName("ddddd");
//        book.getPrice(10000);
//        book.getStockQuantity(10);
//        em.persist(book);

//        int orderCount = 2;
//
//        Long orderId = orderService.order(member.getId() , book.getId() , orderCount);
//
//        orderRepository.findOne(orderId);
//
//        Assert.assertEquals("상품 주무시 상태는 ORDER", OrderStatus.ORDER);

    }

}