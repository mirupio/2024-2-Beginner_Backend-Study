package com.example.todoapi.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 생성
    public void save(Member member) {
        em.persist(member);
    }

    // 조회
    // 단건 조회 (ID 기반으로 조회)
    public Member findById(Long memberId) {
        return em.find(Member.class, memberId);
    }

    // 다건 조회 (모든 회원 데이터 조회)
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 조건 조회 (로그인 ID로 조회)
    public Member findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getSingleResult();
    }

    // 수정 (엔티티 수정)

    // 삭제
    public void deleteById(Long memberId) {
        Member member = findById(memberId);
        if (member != null) {
            em.remove(member);
        }
    }

    // Test 용도로만 사용
    public void flushAndClear() {
        em.flush();
        em.clear();
    }
}
