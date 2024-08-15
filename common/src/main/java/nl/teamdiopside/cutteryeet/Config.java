package nl.teamdiopside.cutteryeet;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class Config {

    public static int yeetStrengthDefault = 2;

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("cutteryeet.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("cutteryeet.config.title"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("cutteryeet.config.enabled.title"))
                                .binding(true, () -> Config.enabled, newVal -> Config.enabled = newVal)
                                .controller(BooleanControllerBuilder::create)
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Text.translatable("cutteryeet.config.yeetStrength.title"))
                                .description(OptionDescription.of(Text.translatable("cutteryeet.config.yeetStrength.description")))
                                .binding(yeetStrengthDefault, () -> Config.yeetStrength, newVal -> Config.yeetStrength = newVal)
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

    public static ConfigClassHandler<Config> HANDLER = ConfigHandler.getConfigHandler();

    @SerialEntry
    public static boolean enabled = true;
    @SerialEntry
    public static int yeetStrength = yeetStrengthDefault;
}
