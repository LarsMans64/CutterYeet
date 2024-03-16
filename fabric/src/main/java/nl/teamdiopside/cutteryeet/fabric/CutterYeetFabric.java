package nl.teamdiopside.cutteryeet.fabric;

import nl.teamdiopside.cutteryeet.CutterYeet;
import net.fabricmc.api.ModInitializer;

public class CutterYeetFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CutterYeet.init();
    }
}