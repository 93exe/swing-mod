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
                .setTitle(Text.literal("Настройки Swing Mod"));

            ConfigCategory general = builder.getOrCreateCategory(Text.literal("Визуал"));
            ConfigEntryBuilder eb = builder.entryBuilder();

            general.addEntry(eb.startFloatField(Text.literal("Смещение X"), SimpleConfig.x)
                .setDefaultValue(0.2f)
                .setSaveConsumer(v -> SimpleConfig.x = v)
                .build());
            
            general.addEntry(eb.startFloatField(Text.literal("Смещение Y"), SimpleConfig.y)
                .setDefaultValue(-0.3f)
                .setSaveConsumer(v -> SimpleConfig.y = v)
                .build());

            general.addEntry(eb.startFloatField(Text.literal("Сила вращения"), SimpleConfig.rotationStrength)
                .setDefaultValue(20.0f)
                .setSaveConsumer(v -> SimpleConfig.rotationStrength = v)
                .build());

            return builder.build();
        };
    }
}
