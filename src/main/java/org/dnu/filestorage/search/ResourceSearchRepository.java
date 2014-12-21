package org.dnu.filestorage.search;

import org.dnu.filestorage.model.Resource;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResourceSearchRepository {

    @Autowired
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public void index(Resource resource) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("name", resource.getName())
                .field("author", resource.getAuthor())
                .field("description", resource.getDescription())
                .endObject();
        String json = builder.string();

        IndexResponse response = client.prepareIndex("resources_cluster", "resource")
                .setSource(json)
                .execute()
                .actionGet();
    }

    public SearchHits search(String query) throws IOException {
        QueryBuilder queryBuilder = null;

        if (query == null || query.isEmpty()) {
            queryBuilder = QueryBuilders.matchAllQuery();
        } else {
            queryBuilder = QueryBuilders.multiMatchQuery(query, "name", "author", "description");
        }

        SearchResponse response = client.prepareSearch("resources_cluster")
                .setTypes("resource")
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(queryBuilder)
                .setFrom(0)
                .execute()
                .actionGet();

        SearchHits hits = response.getHits();
        return hits;
    }
}
