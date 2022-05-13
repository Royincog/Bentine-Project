package com.balentine.core.models.impl;
import com.balentine.core.models.HandleBarV3ModelAdapter;
import lombok.Setter;
import lombok.ToString;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import javax.inject.Inject;
import java.util.List;


@ToString
@Model(adaptables = Resource.class,adapters = HandleBarV3ModelAdapter.class,resourceType = "balentine/components/handleTestV3",defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HandleBarV3Model implements HandleBarV3ModelAdapter{

    @Inject
    @Default(values = "Dummy Text")
    @Setter
    private String text;


    @Inject
    @Default(values = {"EmptyBanana","EmptyApple"})
    private List<FruitModel> fruitlist;

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<FruitModel> getFruitlist() {
        return fruitlist;
    }


}


