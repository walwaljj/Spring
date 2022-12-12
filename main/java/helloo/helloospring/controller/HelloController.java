package helloo.helloospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false) String name, Model model){
                                                        //required의 디폴트는 true
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//http의 통신 프로토콜에 return "hello" + name; 의 내용을 직접 넣어 주겠다는 뜻.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // 요청한 클라이언트에 그대로 들어감.
    }



    @GetMapping("hello-api")
    @ResponseBody//http의 통신 프로토콜에 return "hello" + name; 의 내용을 직접 넣어 주겠다는 뜻.
    public Hello helloSApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;// 객체를 반환하는 api방식 , 웹에서 소스를 보면 키 , 값 형태인 json 방식으로 전달됨.
    }
    static class Hello {
        private String name; // key = name

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }

}
