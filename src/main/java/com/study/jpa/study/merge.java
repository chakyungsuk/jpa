package com.study.jpa.study;

import com.study.jpa.data.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class merge {

    // 엔티티 매니저 팩토리 생성
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {

        Member member = createMemer("memberB", "회원1");

        System.out.println("@@@@ :" + member.getUsername());

        member.setUsername("회원명변경");

        mergeMember(member);

    }

    static Member createMemer(String id, String username) {

        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);

        em1.persist(member);
        tx1.commit();

        em1.close(); // member 엔티티는 준영속 상태가 된다.

        return member;
    }

    static void mergeMember(Member member) {

        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        Member mergeMember = em2.merge(member);
        tx2.commit();

        // 준영속 상태
        System.out.println("member = " + member.getUsername());

        // 영속 상태
        System.out.println("mergeMember = " + mergeMember.getUsername());

        em2.close();
    }
}
