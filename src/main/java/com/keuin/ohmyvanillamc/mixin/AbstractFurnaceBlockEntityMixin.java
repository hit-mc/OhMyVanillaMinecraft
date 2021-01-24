package com.keuin.ohmyvanillamc.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.LinkedHashMap;
import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    private static final Map<Item, Integer> fuelTimeMap = new LinkedHashMap<>(384); // use larger capacity to speed up query and iteration

    static {

        // Copied from 1.16.4 code
        // Need change in the future versions
        // Hint: replace with regex:
        //
        //  map\.put\(\(Tag\)(.+),.([0-9]+)\)\;
        //    --replace-->   $1.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, $2));

        fuelTimeMap.put(Items.LAVA_BUCKET.asItem(), 20000);
        fuelTimeMap.put(Blocks.COAL_BLOCK.asItem(), 16000);
        fuelTimeMap.put(Items.BLAZE_ROD.asItem(), 2400);
        fuelTimeMap.put(Items.COAL.asItem(), 1600);
        fuelTimeMap.put(Items.CHARCOAL.asItem(), 1600);
        ItemTags.LOGS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 300));
        ItemTags.PLANKS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 300));
        ItemTags.WOODEN_STAIRS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 300));
        ItemTags.WOODEN_SLABS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 150));
        ItemTags.WOODEN_TRAPDOORS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 300));
        ItemTags.WOODEN_PRESSURE_PLATES.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 300));
        fuelTimeMap.put(Blocks.OAK_FENCE.asItem(), 300);
        fuelTimeMap.put(Blocks.BIRCH_FENCE.asItem(), 300);
        fuelTimeMap.put(Blocks.SPRUCE_FENCE.asItem(), 300);
        fuelTimeMap.put(Blocks.JUNGLE_FENCE.asItem(), 300);
        fuelTimeMap.put(Blocks.DARK_OAK_FENCE.asItem(), 300);
        fuelTimeMap.put(Blocks.ACACIA_FENCE.asItem(), 300);
        fuelTimeMap.put(Blocks.OAK_FENCE_GATE.asItem(), 300);
        fuelTimeMap.put(Blocks.BIRCH_FENCE_GATE.asItem(), 300);
        fuelTimeMap.put(Blocks.SPRUCE_FENCE_GATE.asItem(), 300);
        fuelTimeMap.put(Blocks.JUNGLE_FENCE_GATE.asItem(), 300);
        fuelTimeMap.put(Blocks.DARK_OAK_FENCE_GATE.asItem(), 300);
        fuelTimeMap.put(Blocks.ACACIA_FENCE_GATE.asItem(), 300);
        fuelTimeMap.put(Blocks.NOTE_BLOCK.asItem(), 300);
        fuelTimeMap.put(Blocks.BOOKSHELF.asItem(), 300);
        fuelTimeMap.put(Blocks.LECTERN.asItem(), 300);
        fuelTimeMap.put(Blocks.JUKEBOX.asItem(), 300);
        fuelTimeMap.put(Blocks.CHEST.asItem(), 300);
        fuelTimeMap.put(Blocks.TRAPPED_CHEST.asItem(), 300);
        fuelTimeMap.put(Blocks.CRAFTING_TABLE.asItem(), 300);
        fuelTimeMap.put(Blocks.DAYLIGHT_DETECTOR.asItem(), 300);
        ItemTags.BANNERS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 300));
        fuelTimeMap.put(Items.BOW.asItem(), 300);
        fuelTimeMap.put(Items.FISHING_ROD.asItem(), 300);
        fuelTimeMap.put(Blocks.LADDER.asItem(), 300);
        ItemTags.SIGNS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 200));
        fuelTimeMap.put(Items.WOODEN_SHOVEL.asItem(), 200);
        fuelTimeMap.put(Items.WOODEN_SWORD.asItem(), 200);
        fuelTimeMap.put(Items.WOODEN_HOE.asItem(), 200);
        fuelTimeMap.put(Items.WOODEN_AXE.asItem(), 200);
        fuelTimeMap.put(Items.WOODEN_PICKAXE.asItem(), 200);
        ItemTags.WOODEN_DOORS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 200));
        ItemTags.BOATS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 1200));
        ItemTags.WOOL.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 100));
        ItemTags.WOODEN_BUTTONS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 100));
        fuelTimeMap.put(Items.STICK.asItem(), 100);
        ItemTags.SAPLINGS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 100));
        fuelTimeMap.put(Items.BOWL.asItem(), 100);
        ItemTags.CARPETS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> fuelTimeMap.put(item, 67));
        fuelTimeMap.put(Blocks.DRIED_KELP_BLOCK.asItem(), 4001);
        fuelTimeMap.put(Items.CROSSBOW.asItem(), 300);
        fuelTimeMap.put(Blocks.BAMBOO.asItem(), 50);
        fuelTimeMap.put(Blocks.DEAD_BUSH.asItem(), 100);
        fuelTimeMap.put(Blocks.SCAFFOLDING.asItem(), 400);
        fuelTimeMap.put(Blocks.LOOM.asItem(), 300);
        fuelTimeMap.put(Blocks.BARREL.asItem(), 300);
        fuelTimeMap.put(Blocks.CARTOGRAPHY_TABLE.asItem(), 300);
        fuelTimeMap.put(Blocks.FLETCHING_TABLE.asItem(), 300);
        fuelTimeMap.put(Blocks.SMITHING_TABLE.asItem(), 300);
        fuelTimeMap.put(Blocks.COMPOSTER.asItem(), 300);
    }

    @Invoker("isNonFlammableWood")
    private static boolean isNonFlammableWood(Item item) {
        throw new AssertionError();
    }

    @Overwrite
    public static Map<Item, Integer> createFuelTimeMap() {
        return fuelTimeMap;
    }
}
