package fi.dy.masa.malilib.test;

import com.google.common.collect.ImmutableList;

import fi.dy.masa.malilib.MaLiLibConfigs;
import fi.dy.masa.malilib.MaLiLibReference;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;

public class TestInputHandler implements IKeybindProvider
{
    private static final TestInputHandler INSTANCE = new TestInputHandler();

    private TestInputHandler()
    {
        super();
    }

    public static TestInputHandler getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void addKeysToMap(IKeybindManager manager)
    {
        for (IHotkey hotkey : MaLiLibConfigs.Test.HOTKEY_LIST)
        {
            manager.addKeybindToMap(hotkey.getKeybind());
        }

        for (TestEnumConfig toggle : TestEnumConfig.values())
        {
            manager.addKeybindToMap(toggle.getKeybind());
        }
    }

    @Override
    public void addHotkeys(IKeybindManager manager)
    {
        manager.addHotkeysForCategory(MaLiLibReference.MOD_NAME, MaLiLibReference.MOD_ID + ".hotkeys.category.test_hotkeys", MaLiLibConfigs.Test.HOTKEY_LIST);
        manager.addHotkeysForCategory(MaLiLibReference.MOD_NAME, MaLiLibReference.MOD_ID + ".hotkeys.category.test_enum_hotkeys", ImmutableList.copyOf(TestEnumConfig.values()));
    }
}
