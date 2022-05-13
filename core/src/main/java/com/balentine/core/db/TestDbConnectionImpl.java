package com.balentine.core.db;

import com.balentine.core.entities.FruitObject;
import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component(service=TestDbConnection.class,immediate = true,enabled = true,name = "TestDB Connection")
public class TestDbConnectionImpl implements TestDbConnection{

    private static final String query =  "SELECT * FROM fruits";
    private static final String insertQuery = "INSERT INTO fruits (idfruits,fruitName,fruitColor) " +
            "VALUES (?,?,?)";
    private BeanListHandler<FruitObject> beanListHandler;
    private DataSourcePool dataSourcePool;
    private QueryRunner runner;
    private List<FruitObject> fruits;

    @Activate
    public TestDbConnectionImpl(@Reference DataSourcePool dataSourcePool){
        this.dataSourcePool = dataSourcePool;
        this.beanListHandler = new BeanListHandler<>(FruitObject.class);
        this.runner = new QueryRunner();
    }
    @Activate
    private void init(){
        log.info("The list of fruits " + getFruits());
    }

    public List<FruitObject> getFruits(){
        try {
            DataSource ds = (DataSource) dataSourcePool.getDataSource("test");
            Connection con = ds.getConnection();
            log.info("The connection info " + con);
            fruits = runner.query(con, query, beanListHandler);
            log.info("The count of records " + fruits);
        } catch (DataSourceNotFoundException | SQLException e) {
           log.info("The error is " + e);
        }
    return fruits;
    }

    @Override
    public void insertFruit(FruitObject fruitObject) {

        try {
            DataSource ds = (DataSource) dataSourcePool.getDataSource("test");
            Connection con = ds.getConnection();

            int someid = runner.update(con,insertQuery,fruitObject.getIdfruits(),fruitObject.getFruitName(),fruitObject.getFruitColor());
        } catch (DataSourceNotFoundException | SQLException e) {
            log.info("The error while inserting is " + e);
        }

    }

}
