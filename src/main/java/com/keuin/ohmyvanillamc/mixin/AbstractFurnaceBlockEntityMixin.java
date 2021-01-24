package com.keuin.ohmyvanillamc.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    private static final Map<Item, Integer> fuelTimeMap; // An immutable view. If a mod tries to add fuel at runtime, it would crash.

    static {

        // Copied from 1.16.4 code
        // Need change in the future versions
        // Hint: replace with regex:
        //
        //  map\.put\(\(Tag\)(.+),.([0-9]+)\)\;
        //    --replace-->   $1.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, $2));

        Map<Item, Integer> map = new LinkedHashMap<>(384); // use larger capacity to speed up query and iteration

        map.put(Items.LAVA_BUCKET.asItem(), 20000);
        map.put(Blocks.COAL_BLOCK.asItem(), 16000);
        map.put(Items.BLAZE_ROD.asItem(), 2400);
        map.put(Items.COAL.asItem(), 1600);
        map.put(Items.CHARCOAL.asItem(), 1600);
        ItemTags.LOGS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 300));
        ItemTags.PLANKS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 300));
        ItemTags.WOODEN_STAIRS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 300));
        ItemTags.WOODEN_SLABS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 150));
        ItemTags.WOODEN_TRAPDOORS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 300));
        ItemTags.WOODEN_PRESSURE_PLATES.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 300));
        map.put(Blocks.OAK_FENCE.asItem(), 300);
        map.put(Blocks.BIRCH_FENCE.asItem(), 300);
        map.put(Blocks.SPRUCE_FENCE.asItem(), 300);
        map.put(Blocks.JUNGLE_FENCE.asItem(), 300);
        map.put(Blocks.DARK_OAK_FENCE.asItem(), 300);
        map.put(Blocks.ACACIA_FENCE.asItem(), 300);
        map.put(Blocks.OAK_FENCE_GATE.asItem(), 300);
        map.put(Blocks.BIRCH_FENCE_GATE.asItem(), 300);
        map.put(Blocks.SPRUCE_FENCE_GATE.asItem(), 300);
        map.put(Blocks.JUNGLE_FENCE_GATE.asItem(), 300);
        map.put(Blocks.DARK_OAK_FENCE_GATE.asItem(), 300);
        map.put(Blocks.ACACIA_FENCE_GATE.asItem(), 300);
        map.put(Blocks.NOTE_BLOCK.asItem(), 300);
        map.put(Blocks.BOOKSHELF.asItem(), 300);
        map.put(Blocks.LECTERN.asItem(), 300);
        map.put(Blocks.JUKEBOX.asItem(), 300);
        map.put(Blocks.CHEST.asItem(), 300);
        map.put(Blocks.TRAPPED_CHEST.asItem(), 300);
        map.put(Blocks.CRAFTING_TABLE.asItem(), 300);
        map.put(Blocks.DAYLIGHT_DETECTOR.asItem(), 300);
        ItemTags.BANNERS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 300));
        map.put(Items.BOW.asItem(), 300);
        map.put(Items.FISHING_ROD.asItem(), 300);
        map.put(Blocks.LADDER.asItem(), 300);
        ItemTags.SIGNS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 200));
        map.put(Items.WOODEN_SHOVEL.asItem(), 200);
        map.put(Items.WOODEN_SWORD.asItem(), 200);
        map.put(Items.WOODEN_HOE.asItem(), 200);
        map.put(Items.WOODEN_AXE.asItem(), 200);
        map.put(Items.WOODEN_PICKAXE.asItem(), 200);
        ItemTags.WOODEN_DOORS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 200));
        ItemTags.BOATS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 1200));
        ItemTags.WOOL.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 100));
        ItemTags.WOODEN_BUTTONS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 100));
        map.put(Items.STICK.asItem(), 100);
        ItemTags.SAPLINGS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 100));
        map.put(Items.BOWL.asItem(), 100);
        ItemTags.CARPETS.values().stream().filter(item -> !isNonFlammableWood(item)).forEach(item -> map.put(item, 67));
        map.put(Blocks.DRIED_KELP_BLOCK.asItem(), 4001);
        map.put(Items.CROSSBOW.asItem(), 300);
        map.put(Blocks.BAMBOO.asItem(), 50);
        map.put(Blocks.DEAD_BUSH.asItem(), 100);
        map.put(Blocks.SCAFFOLDING.asItem(), 400);
        map.put(Blocks.LOOM.asItem(), 300);
        map.put(Blocks.BARREL.asItem(), 300);
        map.put(Blocks.CARTOGRAPHY_TABLE.asItem(), 300);
        map.put(Blocks.FLETCHING_TABLE.asItem(), 300);
        map.put(Blocks.SMITHING_TABLE.asItem(), 300);
        map.put(Blocks.COMPOSTER.asItem(), 300);

        fuelTimeMap = Collections.unmodifiableMap(map);
    }

    @Invoker("isNonFlammableWood")
    private static boolean isNonFlammableWood(Item item) {
        throw new AssertionError();
    }

    /**
     * Replace the getter with ours.
     * The instance is created only once, and we just offer an immutable view.
     *
     * @return an immutable view of the fuel map
     * @reason replace the vanilla's bad implementation of a static factory method
     * @author trueKeuin
     */
    @Overwrite
    public static Map<Item, Integer> createFuelTimeMap() {
        return fuelTimeMap;
    }
}
