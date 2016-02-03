import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@EnableAutoConfiguration
//@Configuration
@EnableConfigurationProperties
public class Example {

    static final String BR = "<br/>";
    static final String APP_ENV = "app.env";
    static final String APP_NAME = "app.name";
    static final String APP_DESCRIPTION = "app.description";

    private ApplicationArguments arguments;

    private ObjectMapper mapper = new ObjectMapper().setDefaultPrettyPrinter(new DefaultPrettyPrinter());

    @Autowired
    private Environment environment;

    @Bean
    @ConfigurationProperties(prefix = "my.servers")
    public List<String> getServers() {
        return new ArrayList<String>();
    }

//    @Autowired
//    private ConnectionSettings connection;

    @Autowired
    public Example(ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    @RequestMapping("/")
    String home() throws JsonProcessingException {
//        System.out.println(connection.getRemoteAddress().toString());
        return "Hello World!"
                + BR + arguments.getOptionValues("debug")
                + BR + arguments.getOptionValues("foo")
                + BR + arguments.getNonOptionArgs()
                + BR + APP_NAME + "=" + environment.getProperty(APP_NAME)
                + BR + APP_DESCRIPTION + "=" + environment.getProperty(APP_DESCRIPTION)
                + BR + APP_ENV + "=" + environment.getProperty(APP_ENV)
                + BR + "foo" + "=" + environment.getProperty("foo")
                + BR + "servers" + "=" + mapper.writeValueAsString(getServers())
                + BR + "servers" + "=" + environment.getProperty("my.servers");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(Example.class, args);
    }

}
