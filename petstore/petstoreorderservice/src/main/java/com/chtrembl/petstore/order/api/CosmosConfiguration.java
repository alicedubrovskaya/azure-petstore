package com.chtrembl.petstore.order.api;
/*
import com.azure.data.cosmos.CosmosKeyCredential;
import com.chtrembl.petstore.order.repository.OrderRepository;
import com.microsoft.azure.spring.data.cosmosdb.config.AbstractCosmosConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.CosmosDBConfig;
import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableCosmosRepositories;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
*/
//@Component
//@EnableCosmosRepositories(basePackageClasses = { OrderRepository.class })
//@ConfigurationProperties(prefix = "petstore.cosmosdb")
/*public class CosmosConfiguration extends AbstractCosmosConfiguration {

    private String uri;

    private String key;

    private String database;

    private CosmosKeyCredential cosmosKeyCredential;

  //  @Bean
    public CosmosDBConfig getConfig() {
        this.cosmosKeyCredential = new CosmosKeyCredential(key);
        CosmosDBConfig cosmosdbConfig = CosmosDBConfig.builder(uri, this.cosmosKeyCredential, database)
                .build();
        return cosmosdbConfig;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
*/