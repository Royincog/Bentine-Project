package com.balentine.core.db;

import com.balentine.core.entities.FruitObject;

import java.util.List;

public interface TestDbConnection {
    public List<FruitObject> getFruits();
    public void insertFruit(FruitObject fruitObject);
}
