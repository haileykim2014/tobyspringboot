package tobyspring.helloboot;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class HelloController {
  public String hello(String name){
    SimpleHelloService service = new SimpleHelloService();
//    if(name == null) throw

    return service.sayHello(Objects.requireNonNull(name));
  }
}
