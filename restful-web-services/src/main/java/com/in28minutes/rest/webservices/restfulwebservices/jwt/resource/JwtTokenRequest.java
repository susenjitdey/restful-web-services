package com.in28minutes.rest.webservices.restfulwebservices.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;

  private String username;
    private String password;

	/*
	 * { "token":
	 * "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbjI4bWludXRlcyIsImV4cCI6MTU4Njk0NjQxMywiaWF0IjoxNTg2MzQxNjEzfQ.-tRLtNFIubLu9FVYm5hPRC3k36wVWR2F6MSu4cYCNr-_MLWtKW0h4nrERsPT4UL14hNjR3F3aWkjSKbpcA6QHw"
	 * }
	 */

    
    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
