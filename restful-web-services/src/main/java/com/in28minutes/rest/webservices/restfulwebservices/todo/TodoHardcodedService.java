package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {

	// Currently this will act as the database
	private static List<Todo> todos = new ArrayList();

	private static long idCounter = 0;

	static {

		todos.add(new Todo(++idCounter, "in28Minutes", "Learn about React", new Date(), false));
		todos.add(new Todo(++idCounter, "in28Minutes", "Learn about Miscroservice", new Date(), false));
		todos.add(new Todo(++idCounter, "in28Minutes", "Learn about SpringBoot", new Date(), false));
		
	}

	// Need to return the Todos
	public List<Todo> findAll() {
		return todos;
	}
	
	public Todo save(Todo todo) {
		if(todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		
		return todo;
	}
	//Service method to delete
	// Need to return the Todos
		public Todo deleteById(long id) {
			Todo todo = findById(id);
			if(todo == null) {
				return null;
			}
			if(todos.remove(todo)) {// This uses the equal method, so lets create it on the Todo.java fo the id
				
				return todo;
				
			}
			return null;
		}

		public Todo findById(long id) {
			
			for(Todo todo : todos) {
				
				if(todo.getId() == id) {
					
					return todo;
				}
				
			}
			
			return null;
		}

}
