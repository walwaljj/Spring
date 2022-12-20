package helloo.helloospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //: bean 으로 bean으로 등록하거나 springConfig에 bean으로 등록하는것을 더 선호.(componentscan아님)
@Aspect
public class TimeTraceAop {
    @Around("execution(* helloo.helloospring..*(..))") // 문법 : 패키지.디렉토리..*클레스(..등등파라미터타입)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
            //
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms" );
        }

    }
}
