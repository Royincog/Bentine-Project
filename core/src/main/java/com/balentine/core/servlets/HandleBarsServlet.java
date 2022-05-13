package com.balentine.core.servlets;
import com.balentine.core.entities.Person;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.dam.commons.util.DamUtil;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.TypeSafeTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


@Slf4j
@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="balentine/components/handleTestV2",
        methods= HttpConstants.METHOD_GET,
        extensions="html")
@ServiceDescription("Handle Test V2")
public class HandleBarsServlet extends SlingSafeMethodsServlet {


    @Override
    protected void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Handlebars handlebars = new Handlebars();



        Rendition rendition =  getResource(request.getResource(),request.getResourceResolver());
        String theTemplateFromFile = getTheTemplateHtml(rendition);
        PersonTemplate personTemplate = handlebars.compileInline(theTemplateFromFile).as(PersonTemplate.class);
        String theHandleBarRenderedHtmlString = personTemplate.apply(new Person("Roy"));

        log.info("The final rendered template String " + theHandleBarRenderedHtmlString);

        response.getWriter().write(theHandleBarRenderedHtmlString);
       // response.getWriter().close();
    }


    private Rendition getResource(Resource resource, ResourceResolver resourceResolver){

        HashMap<String,String> handleBarRendererResource = new HashMap(resource.getValueMap());
        String theJcrLocation = handleBarRendererResource.get("templatePath");
        Resource HtmlResource = resourceResolver.getResource(theJcrLocation);
        Asset asset = DamUtil.resolveToAsset(HtmlResource);
        Rendition originalAsset = asset.getOriginal();



        log.info("The info about original asset size " + originalAsset.getSize());
        log.info("The jcr location is " + theJcrLocation);
        log.info("Resource name for Html HandleBar " + HtmlResource.getName());
        log.info("The resource Map for HandleBarr" + handleBarRendererResource.toString());



        return originalAsset;
    }

    private String getTheTemplateHtml(Rendition rendition) throws IOException {

        InputStream inputStream = rendition.adaptTo(InputStream.class);
        StringBuilder htmlContent = new StringBuilder();
        BufferedReader htmlReader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
        String line;
        while ((line = htmlReader.readLine()) != null) {
           htmlContent.append(line);
        }
        return  htmlContent.toString();
    }

}
interface PersonTemplate extends TypeSafeTemplate<Person> {

   //   public PersonTemplate setName(String name);
  //   public PersonTemplate setSomeArray(String[] array);

}

