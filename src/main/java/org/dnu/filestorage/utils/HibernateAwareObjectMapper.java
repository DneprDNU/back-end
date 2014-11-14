package org.dnu.filestorage.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * @author demyura
 * @since 14.11.14
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

//        configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    }

    public void setPrettyPrint(boolean prettyPrint) {
        configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
    }
}
