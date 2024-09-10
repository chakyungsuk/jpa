package com.study.jpa.study;

import com.study.jpa.data.entity.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AutoIncrement {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin(); // 트랜잭션 시작
            logic(em); // 로직 실행
            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e) {
            System.out.println("##### :" + e);
            tx.rollback(); // 트랜잭션 롤백
        } finally {
            em.close(); // 매니저 종료
        }
    }

    private static void logic(EntityManager em) {
        Board board = new Board();
        em.persist(board);
        System.out.println("board.id = " + board.getId());
    }
}

