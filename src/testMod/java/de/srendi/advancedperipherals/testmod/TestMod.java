package de.srendi.advancedperipherals.testmod;

import de.srendi.advancedperipherals.lib.LibConfig;
import net.minecraftforge.fml.common.Mod;
import site.siredvin.ttoolkit.TToolkitMod;

import java.nio.file.Path;
import java.nio.file.Paths;

@Mod(TestMod.MOD_ID)
public class TestMod {
    public static final Path sourceDir = Paths.get("../../src/testMod/server-files").normalize().toAbsolutePath();
    public static final String MOD_ID = "aptest";

    static {
        TToolkitMod.performConfiguration(sourceDir, 20 * 30);
        LibConfig.setTestMode(true);
    }
}
