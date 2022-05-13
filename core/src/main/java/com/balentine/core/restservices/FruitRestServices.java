package com.balentine.core.restservices;

import com.balentine.core.db.TestDbConnection;
import com.balentine.core.entities.FruitObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component(service = FruitRestServices.class)
@Path("/fruits")
public class FruitRestServices {

    @Reference
    private TestDbConnection testDbConnection;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FruitObject> getFruitObjects(){
        return testDbConnection.getFruits();
    }

    @POST
    @Path("/addfruit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FruitObject addFruit(FruitObject fruitObject){
        testDbConnection.insertFruit(fruitObject);
        return fruitObject;
    }


}
