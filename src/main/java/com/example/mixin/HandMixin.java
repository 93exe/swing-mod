package com.example.mixin;

import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HandMixin {
    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void offsetHand(float tickDelta, MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, net.minecraft.client.network.ClientPlayerEntity player, Hand hand, net.minecraft.item.ItemStack item, float equipProgress, float swingProgress, CallbackInfo ci) {
        // Координаты: X (влево-вправо), Y (вверх-вниз), Z (вперед-назад)
        // Сейчас я поставлю тестовые значения, чтобы рука заметно сместилась:
        matrices.translate(0.1f, -0.3f, 0.0f); 
    }
}
