package helloo.helloospring.repository;

import helloo.helloospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
        System.out.println("result = " + (result == member));
        Assertions.assertEquals(member , result); //
        Assertions.assertThat(member).isEqualTo(result);
    }

}
