package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
@Configuration
@ComponentScan
public class HellobootApplication {
  @Bean
  public ServletWebServerFactory servletWebServerFactory(){
    return new TomcatServletWebServerFactory();
  }
  @Bean
  public DispatcherServlet dispatcherServlet(){
    return new DispatcherServlet();
  }
  public static void main(String[] args) {
    //스프링 컨테이너 만들기
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
      @Override
      protected void onRefresh() {
        super.onRefresh();

        ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
        DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//        dispatcherServlet.setApplicationContext(this); //스프링컨테이너가 applicationContext를 주입해준다.

        WebServer webServer = serverFactory.getWebServer(servletContext -> {
          servletContext.addServlet("frontcontroller",dispatcherServlet)
              .addMapping("/*");

        }); //다른 서블릿컨테이너도 지원하도록 추상화되어있다.
        webServer.start();
      }
    };
    applicationContext.register(HellobootApplication.class);
    applicationContext.refresh();
  }

}
