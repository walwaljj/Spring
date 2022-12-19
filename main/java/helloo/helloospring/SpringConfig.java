package helloo.helloospring;

import helloo.helloospring.aop.TimeTraceAop;
import helloo.helloospring.repository.MemberRepository;
import helloo.helloospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //1. jdbc
    //@Autowired DataSource dataSource; 또는
//    private DataSource dataSource;

    //2. jpa -1
//    private EntityManager em;
//
//
//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.em = em;
//    }

    //3. jpa -2
    private  final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){// 1. 스프링빈에 등록.
        return new MemberService(memberRepository); // 3. SpringConfig의 memberRepository()를 호출
    }
//    @Bean
//    public MemberService memberService(){// 1. 스프링빈에 등록.
//        return new MemberService(memberRepository()); // 3. SpringConfig의 memberRepository()를 호출
//    }


//    @Bean
//    public MemberRepository memberRepository(){//2. 스프링빈에 등록
//        return new MemoryMemberRepository();// 4. 호출된 후 MemoryMemberRepository() 객체생성.
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//         //5. Controller -> Service ->Repository 순서가 만들어짐.
//
//    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
