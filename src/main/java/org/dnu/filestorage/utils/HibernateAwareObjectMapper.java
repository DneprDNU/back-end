package org.dnu.filestorage.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author demyura
 * @since 14.11.14
 */
@Component
public class HibernateAwareObjectMapper extends ObjectMapper implements ApplicationContextAware {

    @Autowired
    ApplicationContext applicationContext;

    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        this.setHandlerInstantiator(applicationContext.getBean(HandlerInstantiator.class));

//        configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    }

    public void setPrettyPrint(boolean prettyPrint) {
        configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
    }

    @Override
    @Autowired
    public Object setHandlerInstantiator(HandlerInstantiator hi) {
        super.setHandlerInstantiator(hi);
        return this;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
