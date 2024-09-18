package com.Test.SampleProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	 @GetMapping("/home")
	    public String home() {
	        return "index"; // Ensure that index.html is placed in src/main/resources/static
	    }

}
