package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
@Configuration
public class HellobootApplication {
  @Bean
  public HelloController helloController(HelloService helloService){
    return new HelloController(helloService);
  }
  @Bean
  public HelloService helloService(){ //인터페이스 타입으로 리턴할것
    return new SimpleHelloService();
  }
  public static void main(String[] args) {
    //스프링 컨테이너 만들기
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
      @Override
      protected void onRefresh() {
        super.onRefresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
          servletContext.addServlet("frontcontroller",
              new DispatcherServlet(this))
              .addMapping("/*");

        }); //다른 서블릿컨테이너도 지원하도록 추상화되어있다.
        webServer.start();
      }
    };
    applicationContext.register(HellobootApplication.class);
    applicationContext.refresh();
  }

}
