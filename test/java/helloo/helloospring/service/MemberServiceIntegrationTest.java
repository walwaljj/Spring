package helloo.helloospring.service;

import helloo.helloospring.domain.Member;
import helloo.helloospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    //@SpringBootTest 은 스프링 컨테이너와 테스트를 함께 실행함.
    // @Transactional 은 testcase에서 사용하면 테스트한 데이터를 커밋 후 롤백시켜준다.
/**===================================== 변경전*/
//    MemberService memberService = new MemberService();
//
//    // 메모리를 클리어시키기 위해 import
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();




    /** ===================================== 변경 후*/

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    //====================================================================


    @Test
    void 회원가입() { // join의 테스트
                // 테스트는 한글로 메서드를 적어도 무관함. 그리고 테스트는 빌드에 포함되지 않는다.

        //문법 given , when , then : 테스트 코드가 길어질 때 단락을 보고 알기 쉽다.


                //given(주어짐)
        Member member = new Member();
        member.setName("spring"); // 만약 spring 으로 변경하면 에러남.
                                    // 왜? 스프링이 이미 가입이 됬기 때문에 다른메소드에서 에러.
                                // 해결 : afterEach 로 메모리 해제해주기.

        //when(실행할 때)
        Long saveId = memberService.join(member); // join 저장한 아이디가 나오도록 만들었음.
                                                                // ==> saveId 에 아이디가 저장됨.

        //then(나올 결과) : 검증단계
        Member findMember =  memberService.findOne(saveId).get(); // 결과가 나옴. 찾은 id를 get 으로 꺼내오고 findMember로 들어감
                                            // .findOne Optional 임.
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 저장한게 repository에 저장한게 맞아 ? 하고 검증하기.
                                                                    // findmember 와 저장된이름이 같은지 비교함.

    }
    @Test
    public void 중복_회원_예외(){ // join이 예외가 났을 경우 실행되는 메서드임.
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        //when

        /**두번 조인하는 경우(현재 member1 , member2 모두 사용자 이름은 spring이기 때문에 두 객체를 모두 조인하면 에러가 남. 중복불가이기 때문에) */

        /**방법 1*/
//        memberService.join(member1);
//
//        try{ // 예외처리하기
//            memberService.join(member2);//member1 과 같이 실행하게되면 이름이 spring으로 같기때문에
//                                        // validateDup.. 에서 중복으로 예외가남. --> 예외처리를 해줘야함.
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.12343"); // 에러가 잡혔는지 확인하기 위해 우리가 정했던 메세지를 확인하는 구문
//                                                            //ㄴ 예외메세지가 MemberService 클레스의 validateDuplicateMember 의
//            //                                                  예외메세지와 같아야 오류가 나지 않는다.
//        }


                                                    /** 또는 */
        /**방법 2*/
        memberService.join(member1);
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); ==> 성공
        //                ㄴ2. exception 이 발생하길 기대         ㄴ1. 해당 메서드를 실행할예정인데 ,
        //assertThrows(NullPointException.class, () -> memberService.join(member2)); ==> 실패 : 메세지가 다르기 때문에.

        //메세지 검증.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
                                    //          ㄴ2. exception 이 발생하길 기대         ㄴ1. 해당 메서드를 실행할예정인데 ,
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }//                 ㄴ e 메세지가    ㄴ 이것과 같니?

}