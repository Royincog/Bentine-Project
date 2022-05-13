package com.balentine.core.services.impl;

import com.balentine.core.entities.ColorPallette;
import com.balentine.core.services.ColorPalletteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component(service = ColorPalletteService.class,enabled = true,immediate = true,
name = "ColorPaletteService",scope = ServiceScope.SINGLETON)
public class ColorPalletteServiceImpl implements ColorPalletteService {

private ObjectMapper objectMapper;

@Activate
public ColorPalletteServiceImpl(){
    this.objectMapper = new ObjectMapper();
}

@Activate
private void activate() {
    log.info("The color Palette is Active");
    log.info("Info About Object Mapper " + objectMapper);

    
}

    private String getResponseFromApi() {

        Map<String, Object> fields = new HashMap<>();
        fields.put("step",0);
        fields.put("sort","random");
        fields.put("tags","");
        HttpResponse<String> jsonResponse
                = Unirest.post("https://colorhunt.co/php/feed.php")
                .fields(fields)
                .asString();

        return jsonResponse.getBody();
    }

    private List<ColorPallette> getColorPojoListFromResponse() throws IOException {
        return objectMapper.readValue(getResponseFromApi(),
                new TypeReference<List<ColorPallette>>(){});
    }

    private synchronized ColorPallette generateRandomColorPallete() throws IOException {
    List<ColorPallette> colorPojos = getColorPojoListFromResponse();
    int aRandomNum = (int) (Math.random() * colorPojos.size());
    return colorPojos.get(aRandomNum);
}

    @Override
    public ColorPallette getColorPallette() throws IOException {

    return generateRandomColorPallete();

    }
}
