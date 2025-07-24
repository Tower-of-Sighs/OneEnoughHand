package com.mafuyu404.oneenoughhand;

import com.mojang.brigadier.CommandDispatcher;
import com.mafuyu404.oneenoughhand.command.ICommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.objectweb.asm.Type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

@Mod(OneEnoughHand.MOD_ID)
public class OneEnoughHand {
    public static final String MOD_ID = "oneenoughhand";

    public OneEnoughHand(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);
        Register.ATTACHMENT_TYPES.register(modEventBus);
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        final String PACKAGE_NAME = "com.mafuyu404.oneenoughhand.command";
        final String REGISTER = "register";
        ModContainer modContainer = ModList.get().getModContainerById(MOD_ID).orElseThrow();
        Set<ModFileScanData.ClassData> classes = modContainer.getModInfo().getOwningFile().getFile().getScanResult().getClasses();
        classes.forEach(classData -> {
            Type clazz = classData.clazz();
            if (clazz.getClassName().startsWith(PACKAGE_NAME)) {
                try {
                    ClassLoader classLoader = OneEnoughHand.class.getClassLoader();
                    Class<?> commandClass = classLoader.loadClass(clazz.getClassName());
                    if (Arrays.stream(commandClass.getInterfaces()).toList().contains(ICommand.class)) {
                        Method register = commandClass.getMethod(REGISTER, CommandDispatcher.class, CommandBuildContext.class, Commands.CommandSelection.class);
                        Constructor<?> constructor = commandClass.getDeclaredConstructor();
                        Object iCommand = constructor.newInstance();
                        register.invoke(iCommand, event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static ResourceLocation ResourceLocationMod(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
