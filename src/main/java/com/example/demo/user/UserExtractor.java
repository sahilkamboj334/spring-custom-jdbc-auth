package com.example.demo.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserExtractor implements ResultSetExtractor<ApplicationUser> {

	JdbcTemplate jdbcTemplate;

	public UserExtractor(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	@Override
	public ApplicationUser extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (rs.next()) {
			List<String> auths= jdbcTemplate.query("select * from user_authorities where username='" + rs.getString("username")+"'",
					new ResultSetExtractor<List<String>>() {
						final List<String> auths = new ArrayList<String>();
						@Override
						public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
							while (rs.next()) {
								auths.add(rs.getString("authority"));
							}
							return auths;
						}

					});
			Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
			for (String role : auths)
				set.add(new SimpleGrantedAuthority(role));
			return new ApplicationUser(set, rs.getString("username"), rs.getString("password"),
					rs.getBoolean("enabled"), true, true, true);
		}
		return null;
	}

}
