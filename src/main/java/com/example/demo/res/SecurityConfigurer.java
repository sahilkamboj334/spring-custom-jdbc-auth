package com.example.demo.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.user.ApplicationUserDetailService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true) 
//it should be enabled if we want to use @secured or @rolesallowed annotation on class or method
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	private PasswordEncoder encoder;
	private ApplicationUserDetailService applicationUserDetailService;
	//private DataSource source;
	@Autowired
	 public SecurityConfigurer(PasswordEncoder encoder,ApplicationUserDetailService applicationUserDetailService) {
		 this.encoder=encoder;
		 this.applicationUserDetailService=applicationUserDetailService;
		// source=dataSource;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.headers()
		.frameOptions()
		.disable()
		.and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/api/**").hasAnyAuthority(Permissions.RESOURCE_WRITE.getPermission())
		.antMatchers(HttpMethod.PUT,"/api/**").hasAnyAuthority(Permissions.RESOURCE_WRITE.getPermission())
		.antMatchers(HttpMethod.DELETE,"/api/**").hasAnyAuthority(Permissions.RESOURCE_WRITE.getPermission())
		.antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(Roles.ADMIN.name(),Roles.USER.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider());
//		auth.jdbcAuthentication()
//		.dataSource(source)
//		.passwordEncoder(encoder)
//		.usersByUsernameQuery("select * from users where username=?")
//		.authoritiesByUsernameQuery("select * from user_authorities where username=?");
	}
	
	@Bean
	public DaoAuthenticationProvider provider() {
		DaoAuthenticationProvider pr=new DaoAuthenticationProvider();
		pr.setUserDetailsService(applicationUserDetailService);
		pr.setPasswordEncoder(encoder);
		return pr;
	}
	
}
