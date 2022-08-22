package net.sydokiddo.auditory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.sydokiddo.auditory.config.ModConfig;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class Auditory implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String MOD_ID = "auditory";
	private static final Random RANDOM = new Random();
	private static final ModConfig CONFIG = AutoConfig.register(ModConfig.class, GsonConfigSerializer::new).getConfig();

	@Override
	public void onInitialize() {

		// Registry:

		ModSoundEvents.registerSounds();

		LOGGER.info("Thank you for downloading Auditory! :)");

		// Cake Eating Sounds:

		if (Auditory.getConfig().interaction_sounds) {

			UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
				BlockPos pos = hitResult.getBlockPos();
				BlockState blockState = world.getBlockState(pos);
				Block block = blockState.getBlock();

				if (!(block instanceof CakeBlock) || !player.canConsume(false) ||
						player.shouldCancelInteraction()) {
					return ActionResult.PASS;
				}
				ItemStack stack = block.getPickStack(world, pos, blockState);
				player.playSound(player.getEatSound(stack), 0.5F + 0.5F * (float) RANDOM.nextInt(2),
						(RANDOM.nextFloat() - RANDOM.nextFloat()) * 0.2F + 1F);
				return ActionResult.PASS;
			});
		}
	}
	public static ModConfig getConfig () {
		return CONFIG;
	}
}