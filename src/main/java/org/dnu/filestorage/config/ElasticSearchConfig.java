package org.dnu.filestorage.config;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
    @Bean
    public Client client() {
        Settings s = ImmutableSettings.settingsBuilder().put("cluster.name","resources_cluster").build();
        TransportClient client = new TransportClient(s);
        client.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
        return client;
    }
}