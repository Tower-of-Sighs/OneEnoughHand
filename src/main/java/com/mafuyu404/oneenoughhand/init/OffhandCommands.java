package com.mafuyu404.oneenoughhand.init;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.function.Consumer;

public class OffhandCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> offhandCommand =
                Commands.literal("offhand")
                        .requires(source -> source.hasPermission(2));

        offhandCommand.then(Commands.literal("disable")
                .then(Commands.argument("targets", EntityArgument.players())
                        .executes(ctx -> setOffhandState(ctx, OEHUtil::disableOffhand))));

        offhandCommand.then(Commands.literal("enable")
                .then(Commands.argument("targets", EntityArgument.players())
                        .executes(ctx -> setOffhandState(ctx, OEHUtil::enableOffhand))));

        offhandCommand.then(Commands.literal("lock")
                .then(Commands.argument("targets", EntityArgument.players())
                        .executes(ctx -> setOffhandState(ctx, OEHUtil::lockOffhand))));

        offhandCommand.then(Commands.literal("unlock")
                .then(Commands.argument("targets", EntityArgument.players())
                        .executes(ctx -> setOffhandState(ctx, OEHUtil::unlockOffhand))));

        dispatcher.register(offhandCommand);
    }

    private static int setOffhandState(CommandContext<CommandSourceStack> ctx, Consumer<ServerPlayer> action) throws CommandSyntaxException {
        Collection<ServerPlayer> targets = EntityArgument.getPlayers(ctx, "targets");
        for (ServerPlayer player : targets) {
            action.accept(player);
        }
        return targets.size();
    }
}
