package helloo.helloospring.repository;

import helloo.helloospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성 문제로 공유되는 변수는 HashMap을 쓰지않는다.
    //                  키   값


    private static Long sequence = 0L;// 순서를 나타낼 변수

    @Override
    public Member save(Member member) { // 멤버를 저장할 때 변화 될 것.
        member.setId(++sequence); //저장하면 sequence가 올라감
        store.put(member.getId(),member); // store에 아이디와 멤버를 넣는다.
        return member;// 리턴해줌.
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return store.get(id);// 스토어에서 아이디를 꺼낸다, 하지만 해당 값이 없으면 null을 처리해야하기 때문에 아래와 같이 작성.
        return Optional.ofNullable(store.get(id)); // 스토어에서 아이디를 꺼냄

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        // 스토어에서 루프를 돌림. -> 멤버의 getName 과 파라미터의 name과 같은지 확인하고 , 같다면 반환. 없다면 Optional 에 넣어 반환.
        // ㄴ findAny() : 찾는 함수.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }

}
