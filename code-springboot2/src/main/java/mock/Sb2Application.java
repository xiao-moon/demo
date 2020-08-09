package mock;

import mock.initializer.E_SecondInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("mock.mapper")
public class Sb2Application {

    public static void main(String[] args) {
        //SpringApplication.run(Sb2Application.class, args);
        SpringApplication springApplication = new SpringApplication(Sb2Application.class);
        springApplication.addInitializers(new E_SecondInitializer());
        springApplication.run(args);
    }

}
