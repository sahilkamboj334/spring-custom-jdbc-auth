package com.example.demo.res;

public enum Permissions {
	RESOURCE_READ("read"),
	RESOURCE_WRITE("write");
	
	private final String permission;
	private Permissions(String permission) {
		this.permission=permission;
	}
	public String getPermission() {
		return this.permission;
	}
}
