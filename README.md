<center><div align="center">

<img height="100" src="icon/400x400.png" width="100"/>

# MaFgLib

MaLiLib unofficial forge port.



</div></center>

MaFgLib (or MaLiLib-Forge) is a library mod used by Masa's mods Forge port. It contains some common code previously
duplicated in most of the mods, such as multi-key capable keybinds, configuration GUIs and **config screen register** etc.
For compiled builds (= downloads), see [Releases](https://github.com/ThinkingStudios/MaLiLib-Forge/releases) or [Modrinth](https://modrinth.com/mod/mafglib)

## Development

This mod use modrinth maven.

```gradle
repositories {
    maven { url 'https://api.modrinth.com/maven' }
}

dependencies {
    modImplementation "maven.modrinth:mafglib:${mafglib_version}"
}
```

> Note: "${mafglib_version}" can be found in [Modrinth](https://modrinth.com/mod/mafglib)

## Compiling
- Clone the repository
- Open a command prompt/terminal to the repository directory
- run 'gradlew build'
- The built jar file will be in build/libs/