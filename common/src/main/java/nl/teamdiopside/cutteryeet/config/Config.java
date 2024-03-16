package nl.teamdiopside.cutteryeet.config;

import dev.architectury.platform.Platform;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import nl.teamdiopside.cutteryeet.CutterYeet;

public class Config {

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("cutteryeet.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("cutteryeet.config.title"))
                        .option(Option.<Integer>createBuilder()
                                .name(Text.translatable("cutteryeet.config.yeetStrength.title"))
                                .description(OptionDescription.of(Text.translatable("cutteryeet.config.yeetStrength.description")))
                                .binding(3, () -> Config.yeetStrength, newVal -> Config.yeetStrength = newVal)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                        .range(1, 10)
                                        .step(1)
                                        .formatValue(val -> Text.literal(String.valueOf(val))))
                                .build())
                        .build())
                .save(() -> HANDLER.save())
                .build()
                .generateScreen(parent);
    }

    public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
            .id(new Identifier(CutterYeet.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(Platform.getConfigFolder().resolve("cutteryeet.json"))
                    .build())
            .build();

    @SerialEntry
    public static int yeetStrength = 3;
}
