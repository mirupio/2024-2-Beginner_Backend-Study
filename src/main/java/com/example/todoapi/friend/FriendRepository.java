package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    // 특정 회원의 친구 목록을 조회하는 메서드
    List<Friend> findByMember1(Member member);

    // 특정 회원과 친구 관계를 조회하는 메서드
    List<Friend> findByMember2(Member member);
}
