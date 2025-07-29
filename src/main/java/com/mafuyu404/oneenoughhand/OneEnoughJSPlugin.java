package com.mafuyu404.oneenoughhand;

import com.mafuyu404.oneenoughhand.init.OEHUtil;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;

public class OneEnoughJSPlugin extends KubeJSPlugin {

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("OEHUtil", OEHUtil.class);
    }
}
