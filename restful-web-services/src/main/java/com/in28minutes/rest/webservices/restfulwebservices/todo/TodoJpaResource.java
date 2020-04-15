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
public class TodoJpaResource {
	
	
	@Autowired 
	private TodoHardcodedService todoService;
	
	@Autowired 
	private TodoJpaRepository todoJpaRepository;
	
	
	//This method to be called from React
	//GET /users/{username}/todos
	@GetMapping("/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		
		return todoJpaRepository.findByUsername(username);
		//return todoService.findAll();
	}
	
	@GetMapping("/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id){
		
		return todoJpaRepository.findById(id).get();
		//return todoService.findById(id);
	}
	
	//Note: ResponseEntity this is used when we need to send
	//no content back
	//DELETE /users/{username}/todos/{id}
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		
		//Call the todoservice
		todoJpaRepository.deleteById(id);
		return ResponseEntity.notFound().build();
	
	}
	
	//3) Edit a todos
	//PUT /users/{username}/todos/{id}

	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
		
		todo.setUsername(username);
		Todo updatedTodo = todoJpaRepository.save(todo);
		
		return new ResponseEntity<Todo>(updatedTodo,HttpStatus.OK);
		
	}
	
	//4)Create a new todo
	//POST /users/{username}/todos
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username,@RequestBody Todo todo){
		
		todo.setUsername(username);
		//Its void because we need to return the url of the created resource
		Todo createdTodo = todoJpaRepository.save(todo);
		//GET "/users/{username}/todos" current resource of the URl and append the {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	

}
