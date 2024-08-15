package nl.teamdiopside.cutteryeet.neoforge;

import dev.architectury.platform.Platform;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.minecraft.util.Identifier;
import nl.teamdiopside.cutteryeet.Config;
import nl.teamdiopside.cutteryeet.CutterYeet;

public class ConfigHandlerImpl {
    public static ConfigClassHandler<Config> getConfigHandler() {
        return ConfigClassHandler.createBuilder(Config.class)
                .id(Identifier.of(CutterYeet.MOD_ID, "config"))
                .serializer(config -> GsonConfigSerializerBuilder.create(config)
                        .setPath(Platform.getConfigFolder().resolve("cutteryeet.json"))
                        .build())
                .build();
    }
}
