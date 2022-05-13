package com.balentine.core.handlebarservice;


import com.adobe.cq.social.handlebars.api.TemplateHelper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.helper.StringHelpers;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

@Component(service = TemplateHelper.class,enabled = true,immediate = true)
public class CutHelper  implements TemplateHelper<Object> {
    @Override
    public CharSequence apply(Object value, Options options) throws IOException {
        return StringHelpers.cut.apply(value,options);
    }

    @Override
    public String getHelperName() {
        return "cut";
    }

    @Override
    public Class<Object> getContextType() {
        return Object.class;
    }

}


