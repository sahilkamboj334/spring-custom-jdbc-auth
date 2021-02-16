package com.example.demo.res;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Roles {
	ADMIN(Util.admin()),
	USER(Util.user());
	
	private final Set<Permissions> permissions;
	private Roles(Set<Permissions> permissions) {
		this.permissions=permissions;
	}
	private Set<Permissions> getPermissions() {
		return this.permissions;
	}
	public Set<SimpleGrantedAuthority> authorities(){
		Set<SimpleGrantedAuthority> set=this.getPermissions().stream()
		.map(permission->new SimpleGrantedAuthority(permission.getPermission()))
		.collect(Collectors.toSet());
		set.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return set;
	}
}
