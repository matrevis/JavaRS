package javars;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class InitClassApp extends Application{
	public Set<Class<?>> getClasses() {
	       Set<Class<?>> s = new HashSet<Class<?>>();
	       s.add(HelloWorldRS.class);
	       return s;
	   }
}
