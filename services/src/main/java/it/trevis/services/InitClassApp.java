package it.trevis.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class InitClassApp extends Application{
	public Set<Class<?>> getClasses() {
	       Set<Class<?>> s = new HashSet<Class<?>>();
	       s.add(HelloWorldRS.class);
	       return s;
	   }
}
