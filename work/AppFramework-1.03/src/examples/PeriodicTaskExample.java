

/*
 * Copyright (C) 2006 Sun Microsystems, Inc. All rights reserved. Use is
 * subject to license terms.
 */ 

package examples;

import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.Task;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;


/**
 * Demonstrates creating a Task that runs periodically.  The 
 * Tasks's {@code process} method runs on the EDT,  
 * every period milliseconds.
 *
 * @author Hans Muller (Hans.Muller@Sun.COM)
 */
public class PeriodicTaskExample extends SingleFrameApplication {
    JLabel label = null;

    class MyPeriodicTask extends Task<Void, Void> {
        private final long period;
        MyPeriodicTask(long period) {
            super(PeriodicTaskExample.this);
            this.period = period;
        }
        public Void doInBackground() throws InterruptedException {
            while(!isCancelled()) {
                Thread.sleep(period);
                publish((Void)null);
            }
            return (Void)null;
        }
        public void process(List<Void> ignored) {
            long dt = getExecutionDuration(TimeUnit.MILLISECONDS);
            label.setText("Elapsed time: " + dt);
        }
    }

    @Override protected void startup() {
        label = new JLabel("Ready...");
        show(label);
    }

    @Override protected void ready() {
        getContext().getTaskService().execute(new MyPeriodicTask(500L));
    }

    public static void main(String[] args) {
        launch(PeriodicTaskExample.class, args);
    }
}
