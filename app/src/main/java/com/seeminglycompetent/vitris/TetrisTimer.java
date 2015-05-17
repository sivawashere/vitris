package com.seeminglycompetent.vitris;

/**
 * Hacked up ActionListener
 * @author Siva Somayyajula
 */

import java.util.Timer;
import java.util.TimerTask;

public class TetrisTimer extends Timer {
    private TimerTask task;
    public TetrisTimer(TimerTask task) {
        super();
        this.task = task;
    }
    public void start() {
        scheduleAtFixedRate(task, 0, 400);
    }
    public void stop() {
        cancel();
    }
}
