package org.dnu.filestorage.search;

import org.dnu.filestorage.model.Resource;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
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
                .field("resourceName", resource.getName())
                .field("author", resource.getAuthor())
                .field("description", resource.getDescription())
                .endObject();
        String json = builder.string();

        IndexResponse response = client.prepareIndex("resources_cluster", "resource")
                .setSource(json)
                .execute()
                .actionGet();
    }

    public SearchHit[] search(String query) throws IOException {
        QueryBuilder queryBuilder = null;

        if (query == null || query.isEmpty()) {
            queryBuilder = QueryBuilders.matchAllQuery();
        } else {
            queryBuilder = QueryBuilders.multiMatchQuery(query, "resourceName", "author", "description");
        }

        SearchResponse response = client.prepareSearch("resources_cluster")
                .setTypes("resource")
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(queryBuilder)
                .setFrom(0).setSize(100)
                .execute()
                .actionGet();

        SearchHits hits = response.getHits();
        return hits.getHits();
    }

    public void clearIndex(String indexName) {
        boolean isExists = client.admin().indices().prepareExists("testindex").execute().actionGet().isExists();
        if (isExists) {
            DeleteIndexResponse delete = client.admin().indices().delete(new DeleteIndexRequest("resources_cluster")).actionGet();
            if (!delete.isAcknowledged()) {
                // Something is wrong.
            }
        }
    }
}
