package io.github.maciejlagowski.prz.project.model.service;

import io.github.maciejlagowski.prz.project.controller.FrameController;
import io.github.maciejlagowski.prz.project.view.View;
import io.github.maciejlagowski.prz.project.view.WaitView;
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
