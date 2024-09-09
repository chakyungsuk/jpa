package com.study.jpa.study;

import com.study.jpa.data.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class basic {

    public static void main(String[] args) {
        // 엔티티 매니저 팩토리 생성
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

    private static void logic (EntityManager em) {

        System.out.println("============================================");

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        em.persist(member); // 등록
        member.setAge(20); // 수정

        Member findMember = em.find(Member.class, id); // 한건 조회
        System.out.println("findMember = " + findMember.getUsername());

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList(); // 목록조회
        System.out.println("member Size :" + members.size());

        em.remove(member); // 삭제

    }
}
