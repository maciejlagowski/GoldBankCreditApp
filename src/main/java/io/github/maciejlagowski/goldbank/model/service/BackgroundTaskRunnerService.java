package io.github.maciejlagowski.goldbank.model.service;

import io.github.maciejlagowski.goldbank.controller.FrameController;
import io.github.maciejlagowski.goldbank.view.View;
import io.github.maciejlagowski.goldbank.view.WaitView;
import javafx.application.Platform;

public class BackgroundTaskRunnerService {

    private final Runnable runnable;

    public BackgroundTaskRunnerService(Runnable runnable) {
        Platform.runLater(() -> FrameController.getInstance().changeView(new WaitView()));
        this.runnable = runnable;
    }

    public void start(View nextView) {
        new Thread(() -> {
            runnable.run();
            Platform.runLater(() -> FrameController.getInstance().changeView(nextView));
        }).start();
    }

    public void start() {
        new Thread(runnable).start();
    }
}
