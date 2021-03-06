package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloworldController {
	
	//GET
	//URI: /hello-world
	//method return : "Hello World"
	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World ";
	}
	
	//Need to now make a bean call.
	//hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello-World");
	}
	
	//How to use path variable.
		//hello-world-bean : eg /hello-world/path-variable/in28Minutes
		@GetMapping(path = "/hello-world/path-variable/{name}")
		public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
			
			//throw new RuntimeException("Something went wrong");
			return new HelloWorldBean(String.format("Hello-World, %s", name));
		}


}
