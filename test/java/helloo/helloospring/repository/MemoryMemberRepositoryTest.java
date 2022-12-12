package helloo.helloospring.repository;

import helloo.helloospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){ // 클리어 시켜주는 역할 , 콜백메소드
        repository.clearStore();// 테스트가 실행되고 끝날때 마다 저장소를 지워줌.
                                // 지우는 이유 ? class를 한번에 실행할때 작성 순서대로 실행되지 않는다.
                                // 테스트는 순서와 의존관계가 상관 없게 작성되어야 함. , 공용데이터를 깔끔하게 정리해줘야지만 문제가없음.
    }

    @Test
    public void save(){

        Member member = new Member();

        // test 로 이름을 셋팅
        member.setName("spring");

        // repository 에 member를 저장함.
        repository.save(member);

        // 클레스의 findById로 윗줄에서 save했던 아이디가 잘 저장되었는지 확인해봄
        Member result = repository.findById(member.getId()).get();
                                                            //테스트이기 때문에 get 으로 바로 꺼내봄.
//        System.out.println("result = " + (result == member));// result() 가 member(spring -20번째 줄)


//1.        Assertions.assertEquals(member , result); // 저장했던 member를 기대함? --> 같으면 에러없음
            //ㄴ java
        //Assertions.assertEquals(member , null); // member와 값이 다름 --> 에러.

//2.        Assertions.assertThat(member).isEqualTo(result);
           // ㄴ org.assertj.core.api
        assertThat(member).isEqualTo(result);// static import를 통해 Assertions 를 생략함.
//        assertThat(member).isEqualTo(null); // 마찬가지로 err남.
    }
    @Test
    public void findByName(){ // findbyname 메소드 테스트
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
            // ++ shift + f6 누르면 같은 이름을 동시에 변경가능.
        member2.setName("spring2");
        repository.save(member2);

//        Member result = repository.findByName("spring2").get();
//        assertThat(result).isEqualTo(member1); // spring2 <-> member1은 일치하지 않기때문에 에러.
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1); // spring2 <-> member1은 일치하지 않기때문에 에러.

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);// 총 2개의 내용을 저장해 size는 2개지만 4개와 비교했기 때문에 err
    }


}
