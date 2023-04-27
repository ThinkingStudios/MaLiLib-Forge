<center><div align="center">

<img height="100" src="icon/400x400.png" width="100"/>

# MaFgLib (MaLiLib-Forge)

MaLiLib unofficial forge port.

</div></center>

**This Mod require [ForgedFabricLoaderAPI](https://github.com/PortingLab/ForgedFabricLoaderAPI) !**

mafglib (or malilib-forge) is a library mod used by masa's mods forge port. It contains some common code previously
duplicated in most of the mods, such as multi-key capable keybinds, configuration GUIs and **config screen register** etc.

## Development

This mod uses jitpack maven, and will use modrinth maven in the future after modrinth is released (snapshots still use jitpack).

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    modImplementation "com.github.ThinkingStudios:MaLiLib-Forge:${mafglib_version}"
}
```
> Note: "${mafglib_version}" can be found in Releases
## Compiling
- Clone the repository
- Open a command prompt/terminal to the repository directory
- run 'gradlew build'
- The built jar file will be in build/libs/
