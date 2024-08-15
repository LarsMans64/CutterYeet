package nl.teamdiopside.cutteryeet.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import nl.teamdiopside.cutteryeet.Config;
import nl.teamdiopside.cutteryeet.CutterYeet;

import java.util.function.Supplier;

@Mod(value = CutterYeet.MOD_ID, dist = Dist.CLIENT)
public class CutterYeetForgeClient {
    public CutterYeetForgeClient(ModContainer container) {
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (Supplier<IConfigScreenFactory>) () -> (IConfigScreenFactory) (minecraftClient, screen) -> Config.makeScreen(screen)
        );
    }
}