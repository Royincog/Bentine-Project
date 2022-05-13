package com.balentine.core.handlebarservice;
import com.adobe.cq.social.handlebars.api.TemplateHelper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.osgi.service.component.annotations.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


@Slf4j
@Component(service = TemplateHelper.class,enabled = true,immediate = true)
public class HandleBarCustomHelper implements TemplateHelper<Map>{



    private String createHtmlStringTemplate(Map context, Options options,BiFunction<Map,Options,String> someFunction)
    {
        return someFunction.apply(context,options);
    }



    @Override
    public CharSequence apply(@NonNull Map context, Options options) throws IOException {



        Handlebars handlebars = new Handlebars();
        HashMap contextHashMap = new HashMap(context);

        log.info("Inside char sequence");
        log.info("The context From Handle is " + context.toString());

        //More Familiar with HashMap

//
//        Element element = new Element(Tag.valueOf("h1"),"");
//        element.appendText("Hello World");


      String htmlString = createHtmlStringTemplate(contextHashMap,options,(cmap,opt)->{
          String valuez = opt.hash("valuez").toString();
          return "<h2>The Hash Passed is " + valuez + "</h2>";
      });


     //     log.info("Jsoup String is " + element.toString());
//        PersonTemplate personTemplate = handlebars.compileInline("{{name}} is the thing").as(PersonTemplate.class);
//        StringBuffer templateString = new StringBuffer(personTemplate.apply(new Person("Roy")));
//        String valuez = options.hash("valuez").toString();
        //return new Handlebars.SafeString(element.toString());

        return new Handlebars.SafeString(htmlString);
    }


    @PostConstruct
    private void init(){
        log.info("In handle bars post construct ");
    }





    @Override
    public String getHelperName() {
        return "foo-text";
    }

    @Override
    public Class<Map> getContextType() {
        return Map.class;
    }
}

//interface PersonTemplate extends TypeSafeTemplate<Person> {
//
//    public PersonTemplate setName(String name);
//
//}
//
//class Person {
//
//    @Getter
//    @Setter
//    private String name;
//
//    public Person(String name) {
//        this.name = name;
//    }
//}

