package com.balentine.core.models;

import com.balentine.core.models.impl.FruitModel;

import java.util.List;

public interface HandleBarV3ModelAdapter {
    public String getText();
    public List<FruitModel> getFruitlist();
}
