package com.balentine.core.models.impl;

import com.balentine.core.db.TestDbConnection;
import com.balentine.core.entities.FruitObject;
import com.balentine.core.models.GetFruitsModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Slf4j
@Model(adaptables = Resource.class,adapters = GetFruitsModel.class)
public class GetFruitsModelImpl implements GetFruitsModel {

    @OSGiService
    private TestDbConnection testDbConnection;

    @PostConstruct
    private void afterConstruct(){
        log.info("The info about the fruits in model {} ",testDbConnection);
    }

    @Override
    public List<FruitObject> getFruitObjects() {
        return testDbConnection.getFruits();
    }
}
