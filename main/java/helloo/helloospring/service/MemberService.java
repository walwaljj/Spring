package helloo.helloospring.service;

import helloo.helloospring.domain.Member;
import helloo.helloospring.repository.MemberRepository;
import helloo.helloospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public class MemberService { // 서비스 클레스는 비지니스와 가까운 용어를 사용해야함
                            // 그래야 유지보수때 평함.



/** ================================== 문제 : 현재는 static 이라 상관없지만 아닐경우에 문제가 생길 수 있다.
 *                                             repository 가 같게 만들기 위해(DB통일을 위해) 변경해야함.*/
//    private final MemberRepository memberRepository = new MemoryMemberRepository();



/**============================== 해결 :외부에서 직접 값을 받을 수 있게 변경하기. ==> MemberServiceTest로 이동해 변경해주기.*/
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){// 서비스네 ? 스프링컨테이너에 등록 ==> 생성자 호출 => Autowired 를 확인 후
                                                        // 스프링컨테이너의 필요한 클레스를 호출해줌.
        this.memberRepository = memberRepository;
    }
    //=============================================


    /*회원 가입기능*/
    public Long join(Member member){


        long start = System.currentTimeMillis();

        try {
            //join시 같은 이름이 있는 중복회원은 가입되지 않게하기
            ValidateDuplicateMember(member); // 중복회원 검증
            memberRepository.save(member); // 통과했다면 저장.
            return member.getId();
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println(timeMs);
        }


    }

    private void ValidateDuplicateMember(Member member) {   //자주 쓰일 내용을 함수화 함
                                                            // 코드 드레그 후 Ctrl + Alt + m 을 하면 됨

        //방법1
        Optional<Member> result = memberRepository.findByName(member.getName());
        // if null~ 으로 코딩하지않고 , Optional을 반환하게 하자.
        result.ifPresent(m ->{ // (ifPresent) 만약 Member에 값이 null이 아니라 값이 있으면 m 으로 이동,
            throw new IllegalStateException("이미 존재하는 회원입니다."); // 예외를 던지기
        });
//        memberRepository.save(member);
//        return member.getId();

        /**
         //방법2 (Optional) 생략 ,findByName 값이 Optional의 값이기 때문에 바로 ifPresent 를 사용할 수 있다.
         memberRepository.findByName(member.getName()) // 같은 이름이 있는지 찾는다.
         .ifPresent(m -> {                      // (ifPresent) 만약 있다면, m으로 들어감.
         throw new IllegalStateException("이미 존재하는 회원입니다.");  // 예외 던지기.
         });
         memberRepository.save(member);
         return member.getId();

         */
    }
    /**전체 회원 조회*/
    public List<Member> findMembers(){

        return memberRepository.findAll(); // 반환 타입이 list<member>
    }

    /**멤버 찾기*/
    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }
}
