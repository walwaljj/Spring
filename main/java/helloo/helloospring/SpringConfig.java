package helloo.helloospring;

import helloo.helloospring.repository.JdbcMemberRepository;
import helloo.helloospring.repository.JdbcTemplateMemberRepository;
import helloo.helloospring.repository.MemberRepository;
import helloo.helloospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    //@Autowired DataSource dataSource; 또는
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){// 1. 스프링빈에 등록.
        return new MemberService(memberRepository()); // 3. SpringConfig의 memberRepository()를 호출
    }

    @Bean
    public MemberRepository memberRepository(){//2. 스프링빈에 등록
//        return new MemoryMemberRepository();// 4. 호출된 후 MemoryMemberRepository() 객체생성.
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
         //5. Controller -> Service ->Repository 순서가 만들어짐.
    }
}
