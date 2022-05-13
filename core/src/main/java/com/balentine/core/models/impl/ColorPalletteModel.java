package com.balentine.core.models.impl;

import com.balentine.core.entities.ColorPallette;
import com.balentine.core.models.ColorPalletteAdapters;
import com.balentine.core.services.ColorPalletteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
@Model(adaptables = Resource.class,
        adapters = ColorPalletteAdapters.class)
public class ColorPalletteModel implements ColorPalletteAdapters {


    @OSGiService
    private ColorPalletteService colorPalletteService;

    @PostConstruct
    private void init(){
        log.info("This is model is active");
    }

    @Override
    public ColorPallette getColorPallette() throws IOException {
        return colorPalletteService.getColorPallette();
    }
}
