package tech.thatgravyboat.creeperoverhaul.client.renderer.normal;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import tech.thatgravyboat.creeperoverhaul.client.init.ClientInit;
import tech.thatgravyboat.creeperoverhaul.common.entity.base.BaseCreeper;
import tech.thatgravyboat.creeperoverhaul.common.entity.base.CreeperType;

public class CreeperPowerLayer<E extends BaseCreeper> extends GeoLayerRenderer<E> {

    public CreeperPowerLayer(IGeoRenderer<E> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack stack, VertexConsumerProvider buffer, int packedLightIn, E creeper, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!creeper.isPowered()) return;

        float f = (float)creeper.age + partialTicks;

        CreeperType type = creeper.getCreeperType();
        GeoModel chargedModel = this.getEntityModel().getModel(type.model());
        RenderLayer renderType = ClientInit.RenderTypes.getSwirl(type.chargedTexture(), f * 0.005F % 1F,  f * 0.005F % 1F);
        VertexConsumer armorConsumer = buffer.getBuffer(renderType);

        getRenderer().render(chargedModel, creeper, partialTicks,
                null, stack, null, armorConsumer,
                packedLightIn, OverlayTexture.DEFAULT_UV,
                1f, 1f, 1f, 1f);
    }
}
