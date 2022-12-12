package helloo.helloospring.repository;

import helloo.helloospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

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
    }


}
