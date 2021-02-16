package com.example.demo.user;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserDao {

	public Optional<ApplicationUser> loadUserByUsername(String username) throws UsernameNotFoundException;
}
