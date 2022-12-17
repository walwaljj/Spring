package helloo.helloospring.repository;

import helloo.helloospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);//회원을 저장하면 반환되는 메서드.


    Optional<Member> findById(Long id);// 멤버를 찾는 메서드
    //++ Optional : null을 처리할때  사용하는 옵션.
    Optional<Member> findByName(String name);// 동일

    List<Member> findAll();//


}
