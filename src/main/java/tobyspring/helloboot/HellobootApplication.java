package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HellobootApplication {
	public static void main(String[] args) {
//		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//		ServletWebServerFactory serverFactory = new JettyServletWebServerFactory();
			ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
			WebServer webServer = serverFactory.getWebServer(); //다른 서블릿컨테이너도 지원하도록 추상화되어있다.
			webServer.start();
	}

}
