package helloo.helloospring.repository;

import helloo.helloospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);//실행시 sql로 번역됨.(select m from Member m where m,name = ?)로 : JPQL

    //Optional<Member> findByNameAAndId(String name,Long Id); 등 인터페이스의 이름만으로도 간단하게 해결..
}
