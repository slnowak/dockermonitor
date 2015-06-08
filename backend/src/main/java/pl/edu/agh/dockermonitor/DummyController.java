package pl.edu.agh.dockermonitor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by novy on 08.06.15.
 */

@RestController
@RequestMapping(value = "/hello")
public class DummyController {

    @RequestMapping(value = "/world", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello";
    }
}
