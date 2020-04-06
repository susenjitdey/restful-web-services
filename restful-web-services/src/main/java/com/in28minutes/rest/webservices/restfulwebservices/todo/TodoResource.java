package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoResource {
	
	
	@Autowired 
	private TodoHardcodedService todoService;
	
	//This method to be called from React
	//GET /users/{username}/todos
	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		
		return todoService.findAll();
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id){
		
		return todoService.findById(id);
	}
	
	//Note: ResponseEntity this is used when we need to send
	//no content back
	//DELETE /users/{username}/todos/{id}
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		
		//Call the todoservice
		Todo todo = todoService.deleteById(id);
		
		if(todo != null) {
			
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	
	}
	
	//3) Edit a todos
	//PUT /users/{username}/todos/{id}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
		
		Todo updatedTodo = todoService.save(todo);
		
		return new ResponseEntity<Todo>(updatedTodo,HttpStatus.OK);
		
	}
	
	//4)Create a new todo
	//POST /users/{username}/todos
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> updateTodo(@PathVariable String username,@RequestBody Todo todo){
		
		//Its void because we need to return the url of the created resource
		Todo createdTodo = todoService.save(todo);
		//GET "/users/{username}/todos" current resource of the URl and append the {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	

}
