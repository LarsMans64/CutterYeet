package nl.teamdiopside.cutteryeet.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nl.teamdiopside.cutteryeet.CutterYeet;
import nl.teamdiopside.cutteryeet.config.Config;

@Mod(CutterYeet.MOD_ID)
public class CutterYeetForge {
    public CutterYeetForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CutterYeet.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> Config.makeScreen(screen)));
        CutterYeet.init();
    }
}