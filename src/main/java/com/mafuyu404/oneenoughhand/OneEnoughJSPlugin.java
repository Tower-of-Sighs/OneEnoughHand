package com.mafuyu404.oneenoughhand;

import com.mafuyu404.oneenoughhand.init.OEHUtil;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;

public class OneEnoughJSPlugin implements KubeJSPlugin {

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("OEHUtil", OEHUtil.class);
    }
}
