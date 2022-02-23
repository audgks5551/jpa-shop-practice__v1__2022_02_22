package jpabook.jpashop;


import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emf"); // 데이터베이스와 연결됨

        EntityManager em = emf.createEntityManager(); // 개체 매니저 생성 (한 트랜잭션당 하나있어야함)

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 생성

        tx.begin(); // 트랜잭션 시작

        try {

            Order order = new Order();
            order.addOrderItem(new OrderItem());

            em.persist(order);


            tx.commit(); // 1. 영속성컨텍스트 -> 데이터베이스 2. 영속성컨텍스트 비우기
        } catch (Exception e) {
            tx.rollback(); // 트랜젝션의 처리 과정에서 발생한 변경 사항을 취소하고, 트랜젝션 과정을 종료
        } finally {
            em.close(); // entity manager 반환
        }

        emf.close(); // entity manager factory 반환

    }
}
