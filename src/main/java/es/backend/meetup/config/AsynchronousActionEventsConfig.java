/**
* AsynchronousActionEventsConfig configures the bean for asynchronous events
* 
* @author Lucia de Espona
* 
*/

package es.backend.meetup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class AsynchronousActionEventsConfig {
	
	public class AsynchronousSpringEventsConfig {
		
	    @Bean(name = "applicationEventMulticaster")
	    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
	    	
	        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
	         
	        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
	        
	        return eventMulticaster;
	    }
	}
}
