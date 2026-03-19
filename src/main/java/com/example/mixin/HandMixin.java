package com.example.mixin;

import com.example.SimpleConfig;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HandMixin {

    @Inject(
        method = "renderFirstPersonItem",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", shift = At.Shift.AFTER)
    )
    private void renderCustomHand(ClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        
        // Смещение руки
        matrices.translate(SimpleConfig.x, SimpleConfig.y, SimpleConfig.z);

        // Анимация удара
        if (swingProgress > 0.0f) {
            float f = (float) Math.sin(Math.sqrt(swingProgress) * Math.PI);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f * -SimpleConfig.rotationStrength));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(f * -20.0f));
        }
    }
}
