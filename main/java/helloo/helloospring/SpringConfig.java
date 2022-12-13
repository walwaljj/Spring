package helloo.helloospring;

import helloo.helloospring.repository.MemberRepository;
import helloo.helloospring.repository.MemoryMemberRepository;
import helloo.helloospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){// 1. 스프링빈에 등록.
        return new MemberService(memberRepository()); // 3. SpringConfig의 memberRepository()를 호출
    }

    @Bean
    public MemberRepository memberRepository(){//2. 스프링빈에 등록
        return new MemoryMemberRepository();// 4. 호출된 후 MemoryMemberRepository() 객체생성.

         //5. Controller -> Service ->Repository 순서가 만들어짐.
    }
}
