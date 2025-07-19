# 只手遮天 / OneEnoughHand

本模组提供了灵活便捷的禁用副手功能。

### 主要功能

**禁用副手**

禁用状态下，在物品栏界面中将不再有副手槽位，切换副手的快捷键也将失效。

但仍可通过指令、KubeJS等方式更改副手槽位中的物品，效果与绑定诅咒相似。

相关指令：

- /offhand disable @a
- /offhand enable @a

**锁定副手**

锁定状态下，副手槽位不允许存在任何物品，否则它将被放置到玩家物品栏中的空余槽位或掉落。

相关指令：

- /offhand lock @a
- /offhand unlock @a

### 使用KubeJS

仅作参考示例：
```javascript
let OEHUtil = Java.loadClass("com.mafuyu404.oneenoughhand.init.OEHUtil");

PlayerEvents.loggedIn(event => {
    OEHUtil.disableOffhand(event.player);
    OEHUtil.enableOffhand(event.player);
    OEHUtil.lockOffhand(event.player);
    OEHUtil.unlockOffhand(event.player);
});
```