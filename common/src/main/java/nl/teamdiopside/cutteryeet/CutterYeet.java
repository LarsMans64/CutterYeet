package nl.teamdiopside.cutteryeet;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class CutterYeet
{
	public static final String MOD_ID = "cutteryeet";
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(MOD_ID, RegistryKeys.SOUND_EVENT);
	public static RegistrySupplier<SoundEvent> CUT_SOUND = SOUND_EVENTS.register("cut", () -> SoundEvent.of(Identifier.of(MOD_ID, "cut")));

	public static void init() {
		SOUND_EVENTS.register();
		Config.HANDLER.load();
	}
}
