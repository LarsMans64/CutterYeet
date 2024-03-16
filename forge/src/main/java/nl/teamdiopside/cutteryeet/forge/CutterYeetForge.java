package nl.teamdiopside.cutteryeet.forge;

import dev.architectury.platform.forge.EventBuses;
import nl.teamdiopside.cutteryeet.CutterYeet;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CutterYeet.MOD_ID)
public class CutterYeetForge {
    public CutterYeetForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CutterYeet.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CutterYeet.init();
    }
}