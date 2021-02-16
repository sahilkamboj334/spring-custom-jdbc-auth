package com.example.demo.user;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.res.Roles;
@Repository("fake")
public class FakeUserDaoImpl implements ApplicationUserDao{
	
	private List<ApplicationUser> users;
	@Autowired
	public FakeUserDaoImpl(PasswordEncoder encoder) {
		//this.encoder=encoder;
		users=Arrays.asList(
				new ApplicationUser(Roles.ADMIN.authorities()
						, "james", encoder.encode("james"), true, true, true, true),
				new ApplicationUser(Roles.USER.authorities()
						, "anna", encoder.encode("anna"), true, true, true, true)
				);
	}
			
	@Override
	public Optional<ApplicationUser> loadUserByUsername(String username) throws UsernameNotFoundException {
			return users.stream().filter(user->user.getUsername().equals(username)).findFirst();
	}
	

}
