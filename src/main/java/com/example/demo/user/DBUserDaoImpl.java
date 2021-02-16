package com.example.demo.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository("db")
public class DBUserDaoImpl implements ApplicationUserDao{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DBUserDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	public Optional<ApplicationUser> loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(jdbcTemplate.query("select * from users where username='"+username+"'"
				,new UserExtractor(jdbcTemplate)));
	}

}
