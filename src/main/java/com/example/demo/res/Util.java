package com.example.demo.res;

import java.util.HashSet;
import java.util.Set;

public abstract class Util {

	public static Set<Permissions> admin(){
		Set<Permissions> set=new HashSet<Permissions>();
		set.add(Permissions.RESOURCE_READ);
		set.add(Permissions.RESOURCE_WRITE);
		return set;
	}
	public static Set<Permissions> user(){
		Set<Permissions> set=new HashSet<Permissions>();
		set.add(Permissions.RESOURCE_READ);
		return set;
	}
}
