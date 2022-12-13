package helloo.helloospring.controller;

import helloo.helloospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

//    private  final MemberService memberService = new MemberService();
//              new 로 하게되면 어느 클레스든 객체를 생성한다. : 그럴 필요없이 하나만 생성하게 해야함.

    private  final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {//memberService가 지글지글거림 'helloo.helloospring.service.MemberService' that could not be found.
                                                            //MemberService 는 순수한 자바클레스
                                                            //@Service
                                                            //public class MemberService 를 해주면 스프링에서 서비스클레스임을 명시했기 때문에 서비스에 등록함. ==> 에러 해결.
                                                            //@Repository
                                                            //public class MemoryMemberRepository { 도 해주기. ==> 스프링에서 repository에 등록하게됨.
        this.memberService = memberService;
    }
}
