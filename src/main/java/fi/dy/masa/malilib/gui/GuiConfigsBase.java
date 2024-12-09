package fi.dy.masa.malilib.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.gui.widgets.WidgetDropDownList;
import net.minecraft.client.gui.screen.Screen;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.gui.ButtonPressDirtyListenerSimple;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.gui.GuiConfigsBase.ConfigOptionWrapper;
import fi.dy.masa.malilib.gui.button.ConfigButtonKeybind;
import fi.dy.masa.malilib.gui.interfaces.IConfigInfoProvider;
import fi.dy.masa.malilib.gui.interfaces.IDialogHandler;
import fi.dy.masa.malilib.gui.interfaces.IKeybindConfigGui;
import fi.dy.masa.malilib.gui.widgets.WidgetConfigOption;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptions;
import fi.dy.masa.malilib.util.GuiUtils;
import fi.dy.masa.malilib.util.KeyCodes;
import fi.dy.masa.malilib.util.StringUtils;
import org.thinkingstudio.mafglib.loader.FoxifiedLoader;
import org.thinkingstudio.mafglib.loader.entrypoints.EntrypointContainer;
import org.thinkingstudio.mafglib.loader.entrypoints.EntrypointHandler;
import org.thinkingstudio.mafglib.loader.gui.ModConfigScreenInitializer;
import org.thinkingstudio.mafglib.special.MaFgLibSpecial;

public abstract class GuiConfigsBase extends GuiListBase<ConfigOptionWrapper, WidgetConfigOption, WidgetListConfigOptions> implements IKeybindConfigGui
{
    protected WidgetDropDownList<EntrypointContainer<ModConfigScreenInitializer>> modSwitchWidget;
    protected final List<Runnable> hotkeyChangeListeners = new ArrayList<>();
    protected final ButtonPressDirtyListenerSimple dirtyListener = new ButtonPressDirtyListenerSimple();
    protected final String modId;
    protected ConfigButtonKeybind activeKeybindButton;
    protected int configWidth = 204;
    @Nullable protected IConfigInfoProvider hoverInfoProvider;
    @Nullable protected IDialogHandler dialogHandler;

    public GuiConfigsBase(int listX, int listY, String modId, @Nullable Screen parent, String titleKey, Object... args)
    {
        super(listX, listY);

        this.modId = modId;
        this.title = StringUtils.translate(titleKey, args);
    }

    @Override
    public void initGui() {
        super.initGui();

        EntrypointHandler.loadAll();

        var modContainer = FoxifiedLoader.getModContainers();

        if (MaFgLibSpecial.getConfig().fastSwitchConfigGui.isTrue()) {
            List<EntrypointContainer<ModConfigScreenInitializer>> entrypointContainers = FoxifiedLoader.getEntrypointContainers("mafglib_modmenu", ModConfigScreenInitializer.class)
                    // This will stack overflow if called in <init>()
                    .stream().filter(mod -> {
                            try {
                                return mod.entrypoint().getModConfigScreenFactory().createScreen(modContainer, null) instanceof GuiConfigsBase;
                            }
                            catch (Exception e)
                            {
                                return false;
                            }
                        }
                    )
                    .toList();
            EntrypointContainer<ModConfigScreenInitializer> thisContainer = entrypointContainers.stream().filter(mod -> {
                GuiConfigsBase gui = (GuiConfigsBase) mod.entrypoint().getModConfigScreenFactory().createScreen(modContainer, null);
                if (gui == null) return false;
                return gui.getClass() == this.getClass();
            }).findFirst().orElse(null);
            modSwitchWidget = new WidgetDropDownList<>(GuiUtils.getScaledWindowWidth() - 155, 13, 130, 18, 200, 10, entrypointContainers) {
                {
                    selectedEntry = thisContainer;
                }

                @Override
                protected void setSelectedEntry(int index) {
                    super.setSelectedEntry(index);
                    if (selectedEntry != null) {
                        client.setScreen(selectedEntry.entrypoint().getModConfigScreenFactory().createScreen(modContainer, null));
                    }
                }

                @Override
                protected String getDisplayString(EntrypointContainer<ModConfigScreenInitializer> entry) {
                    if (entry == null) return "";
                    return entry.mod().getModInfos().getFirst().getDisplayName();
                }
            };
            addWidget(modSwitchWidget);
        }
    }

    @Override
    protected int getBrowserWidth()
    {
        return this.width - 20;
    }

    @Override
    protected int getBrowserHeight()
    {
        return this.height - 80;
    }

    protected boolean useKeybindSearch()
    {
        return false;
    }

    protected int getConfigWidth()
    {
        return this.configWidth;
    }

    public GuiConfigsBase setConfigWidth(int configWidth)
    {
        this.configWidth = configWidth;
        return this;
    }

    public GuiConfigsBase setHoverInfoProvider(IConfigInfoProvider provider)
    {
        this.hoverInfoProvider = provider;
        return this;
    }

    @Nullable
    @Override
    public IDialogHandler getDialogHandler()
    {
        return this.dialogHandler;
    }

    public void setDialogHandler(@Nullable IDialogHandler handler)
    {
        this.dialogHandler = handler;
    }

    @Override
    public String getModId()
    {
        return this.modId;
    }

    @Override
    @Nullable
    public IConfigInfoProvider getHoverInfoProvider()
    {
        return this.hoverInfoProvider;
    }

    @Override
    protected WidgetListConfigOptions createListWidget(int listX, int listY)
    {
        return new WidgetListConfigOptions(listX, listY,
                this.getBrowserWidth(), this.getBrowserHeight(), this.getConfigWidth(), 0.f, this.useKeybindSearch(), this);
    }

    @Override
    public void removed()
    {
        if (this.getListWidget().wereConfigsModified())
        {
            this.getListWidget().applyPendingModifications();
            this.onSettingsChanged();
            this.getListWidget().clearConfigsModifiedFlag();
        }
    }

    protected void onSettingsChanged()
    {
        ConfigManager.getInstance().onConfigsChanged(this.modId);

        if (this.hotkeyChangeListeners.size() > 0)
        {
            InputEventHandler.getKeybindManager().updateUsedKeys();
        }
    }

    @Override
    public boolean onKeyTyped(int keyCode, int scanCode, int modifiers)
    {
        if (this.activeKeybindButton != null)
        {
            this.activeKeybindButton.onKeyPressed(keyCode);
            return true;
        }
        else
        {
            if (this.getListWidget().onKeyTyped(keyCode, scanCode, modifiers))
            {
                return true;
            }

            if (keyCode == KeyCodes.KEY_ESCAPE && this.getParent() != GuiUtils.getCurrentScreen())
            {
                this.closeGui(true);
                return true;
            }

            return false;
        }
    }

    @Override
    public boolean onCharTyped(char charIn, int modifiers)
    {
        if (this.activeKeybindButton != null)
        {
            // Prevents the chars leaking into the search box, if we didn't pretend to handle them here
            return true;
        }

        if (this.getListWidget().onCharTyped(charIn, modifiers))
        {
            return true;
        }

        return super.onCharTyped(charIn, modifiers);
    }

    @Override
    public boolean onMouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (super.onMouseClicked(mouseX, mouseY, mouseButton))
        {
            return true;
        }

        // When clicking on not-a-button, clear the selection
        if (this.activeKeybindButton != null)
        {
            this.activeKeybindButton.onClearSelection();
            this.setActiveKeybindButton(null);
            return true;
        }

        return false;
    }

    @Override
    public void clearOptions()
    {
        this.setActiveKeybindButton(null);
        this.hotkeyChangeListeners.clear();
    }

    @Override
    public void addKeybindChangeListener(Runnable listener)
    {
        this.hotkeyChangeListeners.add(listener);
    }

    @Override
    public ButtonPressDirtyListenerSimple getButtonPressListener()
    {
        return this.dirtyListener;
    }

    @Override
    public void setActiveKeybindButton(@Nullable ConfigButtonKeybind button)
    {
        if (this.activeKeybindButton != null)
        {
            this.activeKeybindButton.onClearSelection();
            this.updateKeybindButtons();
        }

        this.activeKeybindButton = button;

        if (this.activeKeybindButton != null)
        {
            this.activeKeybindButton.onSelected();
        }
    }

    protected void updateKeybindButtons()
    {
        for (Runnable listener : this.hotkeyChangeListeners)
        {
            listener.run();
        }
    }

    public static class ConfigOptionWrapper
    {
        private final Type type;
        @Nullable private final IConfigBase config;
        @Nullable private final String label;

        public ConfigOptionWrapper(IConfigBase config)
        {
            this.type = Type.CONFIG;
            this.config = config;
            this.label = null;
        }

        public ConfigOptionWrapper(String label)
        {
            this.type = Type.LABEL;
            this.config = null;
            this.label = label;
        }

        public Type getType()
        {
            return this.type;
        }

        @Nullable
        public IConfigBase getConfig()
        {
            return this.config;
        }

        @Nullable
        public String getLabel()
        {
            return this.label;
        }

        public static List<ConfigOptionWrapper> createFor(Collection<? extends IConfigBase> configs)
        {
            ImmutableList.Builder<ConfigOptionWrapper> builder = ImmutableList.builder();

            for (IConfigBase config : configs)
            {
                builder.add(new ConfigOptionWrapper(config));
            }

            return builder.build();
        }

        public enum Type
        {
            CONFIG,
            LABEL;
        }
    }
}
