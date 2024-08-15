package nl.teamdiopside.cutteryeet;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;

public class ConfigHandler {
    @ExpectPlatform
    public static ConfigClassHandler<Config> getConfigHandler() {
        throw new AssertionError();
    }
}
