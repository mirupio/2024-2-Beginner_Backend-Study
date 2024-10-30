package com.example.todoapi.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(true) // 테스트 후 롤백하여 데이터베이스 상태 유지
    void saveMemberTest() {
        // 준비: 새 회원 생성
        Member member = new Member();
        member.updateLoginId("testUser");
        member.updatePassword("testPassword");

        // 실행: 회원 저장
        memberRepository.save(member);

        // 검증: 회원이 저장되었고 ID가 생성되었는지 확인
        Assertions.assertThat(member.getId()).isNotNull();
    }

    @Test
    @Transactional
    void findByIdTest() {
        // 준비: 회원 생성 및 저장
        Member member = new Member();
        member.updateLoginId("testUser");
        member.updatePassword("testPassword");
        memberRepository.save(member);

        // 실행: ID로 회원 검색
        Member foundMember = memberRepository.findById(member.getId());

        // 검증: 검색된 회원이 저장된 회원과 일치하는지 확인
        Assertions.assertThat(foundMember).isNotNull();
        Assertions.assertThat(foundMember.getLoginId()).isEqualTo("testUser");
    }

    @Test
    @Transactional
    void findAllTest() {
        // 준비: 여러 회원 생성 및 저장
        Member member1 = new Member();
        member1.updateLoginId("user1");
        member1.updatePassword("password1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.updateLoginId("user2");
        member2.updatePassword("password2");
        memberRepository.save(member2);

        // 실행: 모든 회원 검색
        List<Member> members = memberRepository.findAll();

        // 검증: 검색된 회원 수 확인
        Assertions.assertThat(members).hasSize(2);
    }

    @Test
    @Transactional
    void findByLoginIdTest() {
        // 준비: 회원 생성 및 저장
        Member member = new Member();
        member.updateLoginId("uniqueLogin");
        member.updatePassword("password");
        memberRepository.save(member);

        // 실행: 로그인 ID로 회원 검색
        Member foundMember = memberRepository.findByLoginId("uniqueLogin");

        // 검증: 검색된 회원이 저장된 회원과 일치하는지 확인
        Assertions.assertThat(foundMember).isNotNull();
        Assertions.assertThat(foundMember.getLoginId()).isEqualTo("uniqueLogin");
    }

    @Test
    @Transactional
    void deleteByIdTest() {
        // 준비: 회원 생성 및 저장
        Member member = new Member();
        member.updateLoginId("deleteUser");
        member.updatePassword("password");
        memberRepository.save(member);

        // 실행: ID로 회원 삭제
        memberRepository.deleteById(member.getId());

        // 검증: 회원이 더 이상 존재하지 않는지 확인
        Member deletedMember = memberRepository.findById(member.getId());
        Assertions.assertThat(deletedMember).isNull();
    }

    @AfterAll
    public static void doNotFinish() {
        System.out.println("모든 회원 리포지토리 테스트가 완료되었습니다.");
        // 디버깅 용도로 무한 루프, 프로덕션에서는 제거 필요
        while (true) {}
    }
}
