package nl.teamdiopside.cutteryeet.neoforge;

import net.neoforged.fml.common.Mod;
import nl.teamdiopside.cutteryeet.CutterYeet;

@Mod(CutterYeet.MOD_ID)
public class CutterYeetForge {
    public CutterYeetForge() {
        CutterYeet.init();
    }
}