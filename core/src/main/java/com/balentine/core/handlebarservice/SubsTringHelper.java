package com.balentine.core.handlebarservice;

import com.adobe.cq.social.handlebars.api.TemplateHelper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.helper.StringHelpers;
import org.osgi.service.component.annotations.Component;
import java.io.IOException;

@Component(service = TemplateHelper.class,enabled = true,immediate = true)
public class SubsTringHelper implements TemplateHelper<Object> {
    @Override
    public CharSequence apply(Object value, Options options) throws IOException {
        String str = value.toString();
        Integer start = options.param(0);
        Integer end = options.param(1, str.length());
        return str.subSequence(start, end);
    }

    @Override
    public String getHelperName() {
        return "substring";
    }

    @Override
    public Class<Object> getContextType() {
        return Object.class;
    }
}
