package net.PS.phenomena;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class Tags {
    public static TagKey<Structure> NO_BASALT = TagKey.create(Registries.STRUCTURE, new ResourceLocation("phenomena", "no_basalt"));
}
