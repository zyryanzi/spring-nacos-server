package cloud.spring.my.mongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

//@Configuration
public class MongoDBConfig {

    private String host = "mongodb+srv://cluster94976.o7qtpp5.mongodb.net";


    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(host);
    }

    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        ConnectionString uri = new ConnectionString(host);
        mongo.setConnectionString(uri);
        return mongo;
    }

}
