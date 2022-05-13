package com.balentine.core.models.impl;


import com.adobe.acs.commons.models.injectors.annotation.AemObject;
import org.apache.jackrabbit.commons.xml.ParsingContentHandler;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;

import javax.annotation.PostConstruct;
import javax.jcr.ImportUUIDBehavior;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.net.ContentHandler;

public class Demo {

    @AemObject
    private Session session;

    @AemObject
    private Resource resource;

    @PostConstruct
    public void init() throws RepositoryException, PersistenceException {
//
//        ContentHandler contentHandler = (ContentHandler) session.getImportContentHandler("", ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
//        ParsingContentHandler parsingContentHandler = new ParsingContentHandler((org.xml.sax.ContentHandler) contentHandler);
//
//        .parse();

        ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);

        resource.getResourceResolver().commit();
    }

}
