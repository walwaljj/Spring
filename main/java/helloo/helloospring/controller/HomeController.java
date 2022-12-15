package helloo.helloospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")// localhost 8080 들어오면 호출됨.
    public String home(){
        return "home";
    }
}
// 참고 : static 에 있는 정적 리소스가 무시됨..
// 왜 ? localhost 8080 요청이 오면 스프링에서 Controller 에서 먼저 연결할 컨트롤러를 찾아보기때문.