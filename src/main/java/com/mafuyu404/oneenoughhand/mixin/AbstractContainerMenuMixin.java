package com.mafuyu404.oneenoughhand.mixin;

import com.mafuyu404.oneenoughhand.api.IAbstractContainerMenu;
import com.mafuyu404.oneenoughhand.init.OEHUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
@Implements(@Interface(iface = IAbstractContainerMenu.class, prefix = "lazy$"))
public abstract class AbstractContainerMenuMixin implements IAbstractContainerMenu {
    @Shadow @Final public int containerId;

    @Shadow public abstract Slot getSlot(int p_38854_);

    @Unique
    private ServerPlayer player;

    public void setPlayer(ServerPlayer player) {
        this.player = player;
    }

    public ServerPlayer getPlayer() {
        return player;
    }

    @Inject(method = "broadcastChanges", at = @At("HEAD"))
    private void qqq(CallbackInfo ci) {
        if (this.containerId == 0) {
            if (!OEHUtil.isOffhandLocked(getPlayer())) return;
            Slot slot = this.getSlot(45);
            if (slot.hasItem()) {
                ItemStack itemStack = slot.getItem().copy();
                slot.set(ItemStack.EMPTY);
                give(getPlayer(), itemStack);
            }
        }
    }

    @Unique
    private void give(Player player, ItemStack itemStack) {
        boolean flag = player.getInventory().add(itemStack);
        if (flag && itemStack.isEmpty()) {
            itemStack.setCount(1);
            ItemEntity itementity1 = player.drop(itemStack, false);
            if (itementity1 != null) {
                itementity1.makeFakeItem();
            }
            player.containerMenu.broadcastChanges();
        } else {
            ItemEntity itementity = player.drop(itemStack, false);
            if (itementity != null) {
                itementity.setNoPickUpDelay();
                itementity.setTarget(player.getUUID());
            }
        }
    }
}
