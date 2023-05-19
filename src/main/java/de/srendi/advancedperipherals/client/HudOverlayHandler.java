package de.srendi.advancedperipherals.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.srendi.advancedperipherals.common.argoggles.ARRenderAction;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class HudOverlayHandler {
    private static HudOverlayHandler instance;
    private final List<ARRenderAction> canvas = new ArrayList<>();

    public static void init() {
        instance = new HudOverlayHandler();
        MinecraftForge.EVENT_BUS.register(instance);
    }

    public static HudOverlayHandler getInstance() {
        return instance;
    }

    public static void updateCanvas(List<ARRenderAction> actions) {
        if (instance == null)
            return;
        instance.canvas.clear();
        instance.canvas.addAll(actions);
    }

    public static void clearCanvas() {
        if (instance == null)
            return;
        instance.canvas.clear();
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.getWindow() == null) return;
        Minecraft mc = Minecraft.getInstance();
        MatrixStack matrixStack = event.getMatrixStack();
        MainWindow window = event.getWindow();
        int width = window.getScreenWidth();
        int height = window.getScreenHeight();
        for (ARRenderAction action : canvas) {
            action.draw(mc, matrixStack, width, height);
        }
        mc.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
    }
}
