package horsewithwings.trashbin.blocks;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("trashbin:block_trashbin")
    public static BlockTrashbin TRASHBIN;

    @ObjectHolder("trashbin:tile_trashbin")
    public static TileEntityType<BlockTrashbinTile> TRASHBIN_TILE;

    @ObjectHolder("trashbin:container_trashbin")
    public static ContainerType<BlockTrashbinContainer> TRASHBIN_CONTAINER;
}
