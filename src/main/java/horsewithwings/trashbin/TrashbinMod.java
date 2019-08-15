package horsewithwings.trashbin;

import horsewithwings.trashbin.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod("trashbin")
public class TrashbinMod
{
    private static final Logger LOGGER = LogManager.getLogger();

    public TrashbinMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModBlocks.TRASHBIN_CONTAINER, BlockTrashbinScreen::new);
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {

            event.getRegistry().registerAll(
                new BlockTrashbin().setRegistryName(Reference.MODID, "block_trashbin")
            );

        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().register(TileEntityType.Builder.create(BlockTrashbinTile::new, ModBlocks.TRASHBIN).build(null).setRegistryName("tile_trashbin"));
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                new BlockItem(
                    GameRegistry.findRegistry(Block.class).getValue(ResourceLocation.tryCreate("trashbin:block_trashbin")),
                    new Item.Properties()
                ).setRegistryName(Reference.MODID, "item_trashbin")
            );
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().registerAll(
                IForgeContainerType.create(((windowId, inv, data) -> {
                    BlockPos pos = data.readBlockPos();
                    return new BlockTrashbinContainer(windowId, Minecraft.getInstance().world, pos, inv, Minecraft.getInstance().player);
                })).setRegistryName("container_trashbin")
            );
        }
    }
}
