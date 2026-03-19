package com.example;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("Настройки рук (Cheat Style)"));

            ConfigCategory general = builder.getOrCreateCategory(Text.of("Визуал"));
            ConfigEntryBuilder eb = builder.entryBuilder();

            // Ползунок для X
            general.addEntry(eb.startFloatField(Text.of("Смещение X"), SimpleConfig.x)
                .setSaveConsumer(v -> SimpleConfig.x = v).build());
            
            // Ползунок для Y
            general.addEntry(eb.startFloatField(Text.of("Смещение Y"), SimpleConfig.y)
                .setSaveConsumer(v -> SimpleConfig.y = v).build());

            // Ползунок для плавности удара
            general.addEntry(eb.startFloatField(Text.of("Сила вращения удара"), SimpleConfig.rotationStrength)
                .setSaveConsumer(v -> SimpleConfig.rotationStrength = v).build());

            return builder.build();
        };
    }
}
