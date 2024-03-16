package nl.teamdiopside.cutteryeet.fabric;

import net.fabricmc.api.ModInitializer;
import nl.teamdiopside.cutteryeet.CutterYeet;

public class CutterYeetFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CutterYeet.init();
    }
}