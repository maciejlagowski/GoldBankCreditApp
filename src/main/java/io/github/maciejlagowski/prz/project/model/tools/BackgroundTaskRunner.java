package io.github.maciejlagowski.prz.project.model.tools;

import io.github.maciejlagowski.prz.project.controller.FrameController;
import io.github.maciejlagowski.prz.project.view.View;
import io.github.maciejlagowski.prz.project.view.Wait;
import javafx.application.Platform;

public class BackgroundTaskRunner {

    private Runnable runnable;

    public BackgroundTaskRunner(Runnable runnable) {
        Platform.runLater(() -> FrameController.getInstance().changeView(new Wait()));
        this.runnable = runnable;
    }

    public void start(View nextView) {
        new Thread(() -> {
            runnable.run();
            Platform.runLater(() -> FrameController.getInstance().changeView(nextView));
        }).start();
    }

    public void start() {
        new Thread(() -> runnable.run()).start();
    }
}
