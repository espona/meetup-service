/**
* ExceptionHandlerConfig configures the bean for handling exceptions 
* on the REST service
* 
* @author Lucia de Espona
* 
*/

package es.backend.meetup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@ComponentScan
@Configuration
public class ExceptionHandlerConfig {
	
	/**
	 * This bean sets an exception to be raised on 404
	 */
	@Bean
	DispatcherServlet dispatcherServlet () {
	    DispatcherServlet ds = new DispatcherServlet();
	    ds.setThrowExceptionIfNoHandlerFound(true);
	    return ds;
	}

}
