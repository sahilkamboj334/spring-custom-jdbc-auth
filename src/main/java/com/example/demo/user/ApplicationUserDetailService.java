package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailService implements UserDetailsService{

	private ApplicationUserDao applicationUserDao;
	
	@Autowired
	public ApplicationUserDetailService(@Qualifier("db") ApplicationUserDao applicationUserDao) {
		this.applicationUserDao=applicationUserDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return applicationUserDao.loadUserByUsername(username)
				.orElseThrow(()->{
					System.out.println("here...");
					throw new UsernameNotFoundException("dsdsds");
				});
		}

}
