package de.srendi.advancedperipherals.common.configuration;

import de.srendi.advancedperipherals.lib.LibConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class GeneralConfig implements IAPConfig {

    public final ForgeConfigSpec.BooleanValue ENABLE_DEBUG_MODE;
    private final ForgeConfigSpec configSpec;

    GeneralConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Config to adjust general mod settings").push("General");

        ENABLE_DEBUG_MODE = builder.comment("Enable the debug mode. You should only enable it, if a developer says it or something does not work.").define("enableDebugMode", false);

        builder.pop();
        builder.push("Core");

        LibConfig.build(builder);

        builder.pop();

        configSpec = builder.build();
    }


    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public String getFileName() {
        return "general";
    }

    @Override
    public ModConfig.Type getType() {
        return ModConfig.Type.COMMON;
    }
}