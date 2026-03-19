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

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", shift = At.Shift.AFTER))
    private void renderCustomHand(ClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        
        // Берем координаты из нашего GUI
        matrices.translate(SimpleConfig.x, SimpleConfig.y, SimpleConfig.z);

        // Плавная анимация меча как в читах
        if (swingProgress > 0.0f) {
            float f = (float) Math.sin(Math.sqrt(swingProgress) * Math.PI);
            // Плавный разворот руки
            matrices.multiply(RotationAxis.POSITIVE_Y.getDegreesQuaternion(f * -SimpleConfig.rotationStrength));
            matrices.multiply(RotationAxis.POSITIVE_Z.getDegreesQuaternion(f * -20.0f));
        }
    }
}
