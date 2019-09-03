package es.backend.meetup.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = "es.backend.meetup.repositories")

@ComponentScan
public class SolrConfig {
 
	
    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder("http://localhost:8988/solr").build();
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

