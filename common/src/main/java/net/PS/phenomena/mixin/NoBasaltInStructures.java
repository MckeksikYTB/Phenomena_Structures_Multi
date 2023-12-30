package net.PS.phenomena.mixin;

import net.PS.phenomena.Tags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BasaltColumnsFeature.class)
public class NoBasaltInStructures {

    @Inject(method = "canPlaceAt(Lnet/minecraft/world/level/LevelAccessor;ILnet/minecraft/core/BlockPos$MutableBlockPos;)Z", at = @At(value = "HEAD"), cancellable = true)
    private static void repurposedstructures_noBasaltColumnsInStructures(LevelAccessor levelAccessor, int seaLevel, BlockPos.MutableBlockPos mutableBlockPos, CallbackInfoReturnable<Boolean> cir) {
        if(!(levelAccessor instanceof WorldGenRegion)) {
            return;
        }

        SectionPos sectionPos = SectionPos.of(mutableBlockPos);
        if (!levelAccessor.getChunk(sectionPos.x(), sectionPos.z()).getStatus().isOrAfter(ChunkStatus.STRUCTURE_REFERENCES)) {
            return;
        }

        Registry<Structure> configuredStructureFeatureRegistry = levelAccessor.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY);
        StructureManager structureManager = ((WorldGenRegionAccessor)levelAccessor).getStructureManager();
        for (Holder<Structure> configuredStructureFeature : configuredStructureFeatureRegistry.getOrCreateTag(Tags.NO_BASALT)) {
            if (structureManager.getStructureAt(mutableBlockPos, configuredStructureFeature.value()).isValid()) {
                cir.setReturnValue(false);
                return;
            }
        }
    }
}
