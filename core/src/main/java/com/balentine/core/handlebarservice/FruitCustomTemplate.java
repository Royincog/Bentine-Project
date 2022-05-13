package com.balentine.core.handlebarservice;

import com.adobe.cq.social.handlebars.api.TemplateHelper;

import com.balentine.core.models.impl.FruitModel;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.models.factory.ModelFactory;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Slf4j
@Component(service = TemplateHelper.class,enabled = true,immediate = true)
public class FruitCustomTemplate implements TemplateHelper<List> {




    @Override
    public CharSequence apply(List fruit, Options options) throws IOException {
        String fruitHTMLTemplate = "";
        ArrayList<LinkedHashMap<String,String>> fruitList = new ArrayList<>(fruit);
//
        for (LinkedHashMap<String,String> fruitobj : fruitList){

            Element element = new Element(Tag.valueOf("code"),"").appendText(fruitobj.get("fruitName"));
            fruitHTMLTemplate += element.toString();
        }


     //   log.info("About Fruit list template helper " + fruitModelList.toString());
        log.info("About the fruit template " + fruitHTMLTemplate);
        return new Handlebars.SafeString(fruitHTMLTemplate);
    }

    @Override
    public String getHelperName() {
        return "fruithelper";
    }

    @Override
    public Class<List> getContextType() {
        return List.class;
    }
}
