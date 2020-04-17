package com.in28minutes.rest.webservices.restfulwebservices.jwt;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
        "$2a$10$hDNUpVjsj6qT6.WT1tW6u.SeWErSBtwKfVvI05Di7Ph7wDLrrUGCW", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(2L, "susenjit",
            "$2a$10$hDNUpVjsj6qT6.WT1tW6u.SeWErSBtwKfVvI05Di7Ph7wDLrrUGCW", "ROLE_USER_2"));
    
    //$2a$10$OsOjPK4UCyeBiMkcIEhbdOuYVZTBfY.pLukvI5HyGYIxgcNXINdRm
    //$2a$10$hDNUpVjsj6qT6.WT1tW6u.SeWErSBtwKfVvI05Di7Ph7wDLrrUGCW -- Susenjit
    //$2a$10$I.XUZar1ntCxZWqMfFKSjuHLF7RVNMkZFuMiG6bjmhcTcooO0k1qu--susenjit lates
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}
