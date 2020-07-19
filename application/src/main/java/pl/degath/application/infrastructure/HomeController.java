package pl.degath.application.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * redirects / into swagger
 */
@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String toSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
