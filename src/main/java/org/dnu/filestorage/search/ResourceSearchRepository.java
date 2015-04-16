package org.dnu.filestorage.search;

import org.dnu.filestorage.data.model.LinkingEntity;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.model.Teacher;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for all interactions between application and Elastic Search server.
 */
@Service
public class ResourceSearchRepository {


    /**
     * A client provides a one stop interface for performing actions/operations against the Elastic Search cluster.
     */
    @Autowired
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Converts a resource to JSON document.
     *
     * @param resource Resource to be converted.
     * @return JSON representation of a document.
     * @throws IOException Exception.
     */
    private String buildDocument(Resource resource) throws IOException {
        List<Subject> subjects = resource.getSubjects();
        List<Teacher> teachers = new ArrayList<Teacher>();

        for (Subject subject : subjects){
            for (LinkingEntity linkingEntity : subject.getLinks()){
                 teachers.add(linkingEntity.getTeacher());
            }
        }

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("resourceName", resource.getName())
                .field("author", resource.getAuthor())
                .field("description", resource.getDescription())
                .field("year", resource.getYear())
                .field("speciality", resource.getSpeciality())
                .field("categories", resource.getCategories())
                .field("subjects", subjects)
                .field("teachers", teachers)
                .field("image", resource.getImage())
                .endObject();
        return builder.string();
    }

    /**
     * Index the resource.
     *
     * @param resource Resource to be indexed.
     * @throws IOException Exception.
     */
    public void index(Resource resource) throws IOException {
        String json = this.buildDocument(resource);
        client.prepareIndex("resources_cluster", "resource", String.valueOf(resource.getId()))
                .setSource(json)
                .execute()
                .actionGet();
    }

    /**
     * Update the resource in the index.
     *
     * @param resource Resource to be updated.
     * @throws IOException Exception.
     */
    public void update(Resource resource) throws IOException {
        String json = this.buildDocument(resource);
        client.prepareUpdate("resources_cluster", "resource", String.valueOf(resource.getId()))
                .setDoc(json)
                .get();
    }

    public void delete(Long id) throws IOException {
        client.prepareDelete("resources_cluster", "resource", String.valueOf(id))
                .execute()
                .actionGet();
    }

    /**
     * Search for resources by user query.
     *
     * @param query User query.
     * @return Search hits.
     * @throws IOException Exception.
     */
    public SearchHit[] search(String query) throws IOException {
        QueryBuilder queryBuilder = null;

        if (query == null || query.isEmpty()) {
            queryBuilder = QueryBuilders.matchAllQuery();
        } else {
            queryBuilder = QueryBuilders.multiMatchQuery(query, "resourceName", "author", "description", "year", "speciality", "categories", "image", "subjects", "teachers");
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
        boolean isExists = client.admin().indices().prepareExists(indexName).execute().actionGet().isExists();
        if (isExists) {
            DeleteIndexResponse delete = client.admin().indices().delete(new DeleteIndexRequest(indexName)).actionGet();
            if (!delete.isAcknowledged()) {
                // Something is wrong.
            }
        }

        try {
            client.admin().indices().prepareCreate(indexName)
                    .setSettings(ImmutableSettings.settingsBuilder().loadFromSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .startObject("analysis")
                            .startObject("analyzer")
                            .startObject("default")
                            .field("type", "snowball")
                            .field("language", "Russian")
                            .endObject()
                            .endObject()
                            .endObject()
                            .endObject().string()))
                    .execute().actionGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}