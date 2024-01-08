/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.client.renderer;

import biomesoplenty.forge.common.entity.BoatBOP;
import biomesoplenty.forge.common.entity.ChestBoatBOP;
import biomesoplenty.forge.core.BiomesOPlentyForge;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class BoatRendererBOP extends BoatRenderer
{
    private final Map<BoatBOP.ModelType, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public BoatRendererBOP(EntityRendererProvider.Context context, boolean hasChest)
    {
        super(context, false);
        this.boatResources = Stream.of(BoatBOP.ModelType.values()).collect(ImmutableMap.toImmutableMap((key) -> key, (model) -> {
            return Pair.of(new ResourceLocation(BiomesOPlentyForge.MOD_ID, getTextureLocation(model, hasChest)), createBoatModel(context, model, hasChest));
        }));
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat)
    {
        if (boat instanceof ChestBoatBOP)
            return this.boatResources.get(((ChestBoatBOP)boat).getModel());
        else
            return this.boatResources.get(((BoatBOP)boat).getModel());
    }

    private static String getTextureLocation(BoatBOP.ModelType model, boolean hasChest)
    {
        return hasChest ? "textures/entity/chest_boat/" + model.getName() + ".png" : "textures/entity/boat/" + model.getName() + ".png";
    }

    private static ModelLayerLocation createLocation(String name, String layer)
    {
        return new ModelLayerLocation(new ResourceLocation(BiomesOPlentyForge.MOD_ID, name), layer);
    }

    public static ModelLayerLocation createBoatModelName(BoatBOP.ModelType model)
    {
        return createLocation("boat/" + model.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(BoatBOP.ModelType model)
    {
        return createLocation("chest_boat/" + model.getName(), "main");
    }

    private BoatModel createBoatModel(EntityRendererProvider.Context context, BoatBOP.ModelType model, boolean hasChest)
    {
        ModelLayerLocation modellayerlocation = hasChest ? createChestBoatModelName(model) : createBoatModelName(model);
        ModelPart baked = context.bakeLayer(modellayerlocation);
        return hasChest ? new ChestBoatModel(baked) : new BoatModel(baked);
    }
}
