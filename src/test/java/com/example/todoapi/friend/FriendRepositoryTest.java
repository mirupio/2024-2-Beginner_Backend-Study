package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class FriendRepositoryTest {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(true) // 테스트 후 롤백하여 데이터베이스 상태 유지
    void saveFriendTest() {
        // 준비: 두 회원 생성 및 저장
        Member member1 = new Member();
        member1.updateLoginId("user1");
        member1.updatePassword("password1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.updateLoginId("user2");
        member2.updatePassword("password2");
        memberRepository.save(member2);

        // 친구 관계 생성
        Friend friend = new Friend();
        friend.setMember1(member1);
        friend.setMember2(member2);

        // 실행: 친구 저장
        friendRepository.save(friend);

        // 검증: 친구가 저장되었는지 확인
        Assertions.assertThat(friend.getId()).isNotNull();
    }

    @Test
    @Transactional
    void findByMember1Test() {
        // 준비: 두 회원 생성 및 친구 관계 저장
        Member member1 = new Member();
        member1.updateLoginId("user1");
        member1.updatePassword("password1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.updateLoginId("user2");
        member2.updatePassword("password2");
        memberRepository.save(member2);

        Friend friend = new Friend();
        friend.setMember1(member1);
        friend.setMember2(member2);
        friendRepository.save(friend);

        // 실행: member1의 친구 목록 조회
        List<Friend> friends = friendRepository.findByMember1(member1);

        // 검증: 친구 목록에 member2가 포함되어 있는지 확인
        Assertions.assertThat(friends).hasSize(1);
        Assertions.assertThat(friends.get(0).getMember2()).isEqualTo(member2);
    }

    @Test
    @Transactional
    void findByMember2Test() {
        // 준비: 두 회원 생성 및 친구 관계 저장
        Member member1 = new Member();
        member1.updateLoginId("user1");
        member1.updatePassword("password1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.updateLoginId("user2");
        member2.updatePassword("password2");
        memberRepository.save(member2);

        Friend friend = new Friend();
        friend.setMember1(member1);
        friend.setMember2(member2);
        friendRepository.save(friend);

        // 실행: member2의 친구 목록 조회
        List<Friend> friends = friendRepository.findByMember2(member2);

        // 검증: 친구 목록에 member1이 포함되어 있는지 확인
        Assertions.assertThat(friends).hasSize(1);
        Assertions.assertThat(friends.get(0).getMember1()).isEqualTo(member1);
    }

    @AfterAll
    public static void doNotFinish() {
        System.out.println("모든 친구 리포지토리 테스트가 완료되었습니다.");
        // 디버깅 용도로 무한 루프, 프로덕션에서는 제거 필요
        while (true) {}
    }
}
