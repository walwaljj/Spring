package helloo.helloospring.repository;

import helloo.helloospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    
    private final EntityManager em;//jpa는 EntityManager로 동작

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
                                //ㄴ 조회할 타입 ㄴ 식별자 pk
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from  Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from  Member m", Member.class)// 테이블 대상이 아닌 객체자체(member as m)를 대상으로 query 를 날리고 , sql로 번역됨
                            //"select m from  Member as m", Member.class
                .getResultList();
    }
}
