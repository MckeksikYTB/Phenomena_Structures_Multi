package net.PS.phenomena.world.village;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "phenomena")
public class VillageAddition {
    private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(
            Registries.PROCESSOR_LIST, new ResourceLocation("minecraft", "empty"));
    private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry,
                                          Registry<StructureProcessorList> processorListRegistry,
                                          ResourceLocation poolRL,
                                          String nbtPieceRL,
                                          int weight) {

        Holder<StructureProcessorList> emptyProcessorList = processorListRegistry.getHolder(EMPTY_PROCESSOR_LIST_KEY).orElseThrow();

        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null) return;

        SinglePoolElement piece = SinglePoolElement.legacy(nbtPieceRL,
                emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);

        for (int i = 0; i < weight; i++) {
            pool.templates.add(piece);
        }

        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, weight));
        pool.rawTemplates = listOfPieceEntries;
    }

    @SubscribeEvent
    public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {
        Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).orElseThrow();
        Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).orElseThrow();

        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft:village/plains/houses"),
                "phenomena:village/plains/campfire", 4);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft:village/savanna/houses"),
                "phenomena:village/savana/campfire", 4);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft:village/taiga/houses"),
                "phenomena:village/taiga/campfire", 4);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft:village/snowy/houses"),
                "phenomena:village/snowy/campfire", 4);

        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft:village/taiga/houses"),
                "phenomena:village/taiga/guildhouse_taiga", 5);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft:village/desert/houses"),
                "phenomena:village/desert/guildhouse_desert", 5);
        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft:village/plains/houses"),
                "phenomena:village/plains/guildhouse", 5);

        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("minecraft:village/plains/houses"),
                "phenomena:village/plains/oak_pillar", 3);
    }
}
