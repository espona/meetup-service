package es.backend.meetup.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
* SolrConfig configures the beans for the Solr repository
* 
* @author Lucia de Espona
* 
*/
@Configuration
@EnableSolrRepositories(basePackages = "es.backend.meetup.repository")
@ComponentScan
public class SolrConfig {
 
	@Value("${solr.url}")
	private String solr_url;
	
    @Bean
    public SolrClient solrClient() {
    	
        return new HttpSolrClient.Builder(solr_url).build();
    }
 
    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
    	
        return new SolrTemplate(client);
    }
    
    @Bean
    public SolrOperations solrTemplate() {
    	
      return new SolrTemplate(solrClient());
    }

}

