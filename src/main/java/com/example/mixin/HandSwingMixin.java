package com.example.mixin;

import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HandSwingMixin {
    
    // В 1.21.1 этот метод может называться по-разному в маппингах, 
    // но самый безопасный способ "вклиниться" — это renderItem
    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    private void onRenderItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        // Если игра запустится — значит, мы попали в нужный метод!
    }
}
