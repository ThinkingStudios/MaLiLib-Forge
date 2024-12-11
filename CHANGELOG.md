## Change
- sync from `sakura-ryoko/malilib` 1.21.3-0.22.3-sakura.4

details:

**Lots of changes, please read carefully, especially for Downstream mods.**

Merged a handful of useful utility functions from Post-Rewrite MaLiLib 1.12.2-ornithe including:

* BlockUtils (Deprecated things, and split between util/game/BlockUtils and util/nbt/NbtBlockUtils)
* util/game/BlockUtils has been also merged with Post-ReWrite.
* EntityUtils (Deprecated things, and moved most functions to util/nbt/NbtEntityUtils)
* NbtKeys (Deprecated, and moved to util/nbt/NbtKeys)
* Added FileNameUtils, and Merged FileUtils from Post-Rewrite.
* Added as a WIP JsonUtils from Post-ReWrite under util/data/json, which will have JSON Deserializers for every Config Type (Coming soon)
* Deprecated util/NBTUtils for a merged Post-ReWrite copy under util/nbt/NbtUtils with added functionality
* Deprecated PayloadUtils
* Added a handful of other items under util/data, util/game, util/nbt, and util/position from Post-ReWrite.
* Added Config Menu Registry from Post-Re-Write. Your Mods should Register with MaLiLib for each of your `GuiConfigBase` screens as follows using the new `ModInfo` class During your Mod Init():
```
    Registry.CONFIG_SCREEN.registerConfigScreenFactory(
        new ModInfo(MaLiLibReference.MOD_ID, MaLiLibReference.MOD_NAME, MaLiLibConfigGui::new)
    );
```
MaFgLib will attempt to automatically Register any non-compliant Downstream mods; and this feature can be disabled using `enableConfigSwitcher` under MaFgLib's config menu.
