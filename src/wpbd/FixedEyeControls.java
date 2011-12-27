/*
 * FixedEyeControls.java
 *
 * Created on Jun 20, 2011, 10:17:44 PM
 */
package wpbd;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import org.jdesktop.application.Action;

/**
 *
 * Controls dialog for the fly through animation.
 * @author Eugene K. Ressler
 */
public final class FixedEyeControls extends JDialog implements AnimationControls {

    private final FixedEyeAnimation animation;
    /**
     * State variables describing appearance and dependent visibility.
     */
    private boolean dropped;
    private int panelHeight;
    private boolean visibleState = true;
    private boolean initialized = false;
    private Icon playIcon;
    private Icon pauseIcon;
    private static final String animationDialogStorage = "fixedEyeAnimationControlsState.xml";

    /** Creates new FlyThruControls non-modal floating dialog with dropdown. */
    public FixedEyeControls(Frame parent, FixedEyeAnimation animation) {
        super(parent);
        this.animation = animation;
        initComponents();
        playIcon = WPBDApp.getApplication().getIconResource("play.png");
        pauseIcon = WPBDApp.getApplication().getIconResource("pause.png");
    }

    /**
     * Return the dialog that implements the controls.
     * 
     * @return dialog implementing the controls
     */
    public Dialog getDialog() {
        return this;
    }

    public boolean getVisibleState() {
        return visibleState;
    }
    
    public void saveVisibilityAndHide() {
        saveState();
        visibleState = isVisible();
        setVisible(false);
    }
 
    public void startAnimation() {
        playButton.setIcon(pauseIcon);
        animation.getConfig().paused = false;
        if (visibleState) {
            if (!initialized) {
                setLocation(animation.getCanvas().getLocationOnScreen());
                panelHeight = animationControlsPanel.getHeight();
                setSize(getWidth(), getHeight() - panelHeight);
                dropped = false;
                visibleState = true;
                restoreState();
                initialized = true;
            }
            // Make visible and start animation later so that JOGL initializes first.
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    restoreState();
                    setVisible(true);
                    animation.start();
                    animation.getCanvas().requestFocusInWindow();
                }
            });
        } else {
            // Just start the animation.
            animation.start();
            animation.getCanvas().requestFocusInWindow();
        }
    }

    /**
     * Return a list of all the animation controls check boxes.
     *
     * @return checkboxes of the animation controls
     */
    private JCheckBox [] getAnimationControlsCheckBoxes() {
        return new JCheckBox[] {
            backgroundCheckBox,
            abutmentsCheckBox,
            colorsCheckBox,
            exaggerationCheckBox,
            truckCheckBox
        };
    }

    public void restoreState() {
        ComponentStateLocalStorable s = ComponentStateLocalStorable.load(animationDialogStorage);
        if (s != null) {
            s.apply(getAnimationControlsCheckBoxes());
        }
    }

    // Capture animation controls state for next time the animation panel is shown.
    public void saveState() {
        ComponentStateLocalStorable s = new ComponentStateLocalStorable();
        s.add(getAnimationControlsCheckBoxes());
        s.save(animationDialogStorage);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        animationControlsToolbar = new javax.swing.JToolBar();
        resetButton = new javax.swing.JButton();
        playButton = new javax.swing.JToggleButton();
        sep100 = new javax.swing.JToolBar.Separator();
        speedSlider = new javax.swing.JSlider();
        animationControlsSep02 = new javax.swing.JToolBar.Separator();
        animationControlsDialogDropButton = new javax.swing.JButton();
        animationControlsPanel = new javax.swing.JPanel();
        backgroundCheckBox = new javax.swing.JCheckBox();
        speedLabel = new javax.swing.JLabel();
        colorsCheckBox = new javax.swing.JCheckBox();
        exaggerationCheckBox = new javax.swing.JCheckBox();
        smoothTerrainCheckBox = new javax.swing.JCheckBox();
        abutmentsCheckBox = new javax.swing.JCheckBox();
        truckCheckBox = new javax.swing.JCheckBox();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(wpbd.WPBDApp.class).getContext().getResourceMap(FixedEyeControls.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFocusable(false);
        setFocusableWindowState(false);
        setIconImage(WPBDApp.getApplication().getImageResource("animate.png"));
        setName("Form"); // NOI18N
        setResizable(false);

        animationControlsToolbar.setFloatable(false);
        animationControlsToolbar.setRollover(true);
        animationControlsToolbar.setName("animationControlsToolbar"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(wpbd.WPBDApp.class).getContext().getActionMap(FixedEyeControls.class, this);
        resetButton.setAction(actionMap.get("reset")); // NOI18N
        resetButton.setIcon(resourceMap.getIcon("resetButton.icon")); // NOI18N
        resetButton.setFocusable(false);
        resetButton.setHideActionText(true);
        resetButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resetButton.setName("resetButton"); // NOI18N
        resetButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        animationControlsToolbar.add(resetButton);

        playButton.setAction(actionMap.get("playAnimation")); // NOI18N
        playButton.setToolTipText(resourceMap.getString("playButton.toolTipText")); // NOI18N
        playButton.setFocusable(false);
        playButton.setHideActionText(true);
        playButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playButton.setName("playButton"); // NOI18N
        playButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        animationControlsToolbar.add(playButton);

        sep100.setMaximumSize(new java.awt.Dimension(8, 32767));
        sep100.setMinimumSize(new java.awt.Dimension(8, 0));
        sep100.setName("sep100"); // NOI18N
        sep100.setPreferredSize(new java.awt.Dimension(8, 0));
        animationControlsToolbar.add(sep100);

        speedSlider.setMajorTickSpacing(5);
        speedSlider.setMaximum(30);
        speedSlider.setMinimum(5);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setPaintTicks(true);
        speedSlider.setSnapToTicks(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("wpbd/resources/WPBDView"); // NOI18N
        speedSlider.setToolTipText(bundle.getString("animationSpeedTip")); // NOI18N
        speedSlider.setMaximumSize(new java.awt.Dimension(120, 34));
        speedSlider.setMinimumSize(new java.awt.Dimension(120, 34));
        speedSlider.setName("speedSlider"); // NOI18N
        speedSlider.setPreferredSize(new java.awt.Dimension(120, 34));
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });
        animationControlsToolbar.add(speedSlider);

        animationControlsSep02.setMaximumSize(new java.awt.Dimension(8, 32767));
        animationControlsSep02.setMinimumSize(new java.awt.Dimension(8, 0));
        animationControlsSep02.setName("animationControlsSep02"); // NOI18N
        animationControlsSep02.setPreferredSize(new java.awt.Dimension(8, 0));
        animationControlsToolbar.add(animationControlsSep02);

        animationControlsDialogDropButton.setAction(actionMap.get("toggleAnimationDrop")); // NOI18N
        animationControlsDialogDropButton.setHideActionText(true);
        animationControlsDialogDropButton.setMaximumSize(new java.awt.Dimension(37, 37));
        animationControlsDialogDropButton.setMinimumSize(new java.awt.Dimension(37, 37));
        animationControlsDialogDropButton.setName("animationControlsDialogDropButton"); // NOI18N
        animationControlsDialogDropButton.setPreferredSize(new java.awt.Dimension(37, 37));
        animationControlsToolbar.add(animationControlsDialogDropButton);

        animationControlsPanel.setName("animationControlsPanel"); // NOI18N

        backgroundCheckBox.setSelected(true);
        backgroundCheckBox.setText(resourceMap.getString("backgroundCheckBox.text")); // NOI18N
        backgroundCheckBox.setActionCommand(resourceMap.getString("backgroundCheckBox.actionCommand")); // NOI18N
        backgroundCheckBox.setName("backgroundCheckBox"); // NOI18N
        backgroundCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                backgroundCheckBoxItemStateChanged(evt);
            }
        });

        speedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        speedLabel.setText(resourceMap.getString("speedLabel.text")); // NOI18N
        speedLabel.setMaximumSize(new java.awt.Dimension(64, 16));
        speedLabel.setMinimumSize(new java.awt.Dimension(64, 16));
        speedLabel.setName("speedLabel"); // NOI18N
        speedLabel.setPreferredSize(new java.awt.Dimension(64, 16));

        colorsCheckBox.setSelected(true);
        colorsCheckBox.setText(resourceMap.getString("colorsCheckBox.text")); // NOI18N
        colorsCheckBox.setName("colorsCheckBox"); // NOI18N
        colorsCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                colorsCheckBoxItemStateChanged(evt);
            }
        });

        exaggerationCheckBox.setSelected(true);
        exaggerationCheckBox.setText(resourceMap.getString("exaggerationCheckBox.text")); // NOI18N
        exaggerationCheckBox.setName("exaggerationCheckBox"); // NOI18N
        exaggerationCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                exaggerationCheckBoxItemStateChanged(evt);
            }
        });

        smoothTerrainCheckBox.setSelected(true);
        smoothTerrainCheckBox.setText(resourceMap.getString("smoothTerrainCheckBox.text")); // NOI18N
        smoothTerrainCheckBox.setName("smoothTerrainCheckBox"); // NOI18N
        smoothTerrainCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                smoothTerrainCheckBoxItemStateChanged(evt);
            }
        });

        abutmentsCheckBox.setSelected(true);
        abutmentsCheckBox.setText(resourceMap.getString("abutmentsCheckBox.text")); // NOI18N
        abutmentsCheckBox.setName("abutmentsCheckBox"); // NOI18N
        abutmentsCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                abutmentsCheckBoxItemStateChanged(evt);
            }
        });

        truckCheckBox.setSelected(true);
        truckCheckBox.setText(resourceMap.getString("truckCheckBox.text")); // NOI18N
        truckCheckBox.setName("truckCheckBox"); // NOI18N
        truckCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                truckCheckBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout animationControlsPanelLayout = new javax.swing.GroupLayout(animationControlsPanel);
        animationControlsPanel.setLayout(animationControlsPanelLayout);
        animationControlsPanelLayout.setHorizontalGroup(
            animationControlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(animationControlsPanelLayout.createSequentialGroup()
                .addGroup(animationControlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(animationControlsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(animationControlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backgroundCheckBox)
                            .addComponent(abutmentsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(animationControlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(colorsCheckBox)
                            .addComponent(exaggerationCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                    .addGroup(animationControlsPanelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(speedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(animationControlsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(truckCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addComponent(smoothTerrainCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                .addContainerGap())
        );
        animationControlsPanelLayout.setVerticalGroup(
            animationControlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, animationControlsPanelLayout.createSequentialGroup()
                .addComponent(speedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(animationControlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backgroundCheckBox)
                    .addGroup(animationControlsPanelLayout.createSequentialGroup()
                        .addComponent(colorsCheckBox)
                        .addGap(0, 0, 0)
                        .addGroup(animationControlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exaggerationCheckBox)
                            .addComponent(abutmentsCheckBox))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(animationControlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(truckCheckBox)
                    .addComponent(smoothTerrainCheckBox))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(animationControlsToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(animationControlsPanel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(animationControlsToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animationControlsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        int speed = speedSlider.getValue();
        speedLabel.setText(Integer.toString(speed) + " km/hr");
        animation.getConfig().truckSpeed = speed;
}//GEN-LAST:event_speedSliderStateChanged

    private void colorsCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_colorsCheckBoxItemStateChanged
        animation.getConfig().showForcesAsColors = (evt.getStateChange() == ItemEvent.SELECTED);
}//GEN-LAST:event_colorsCheckBoxItemStateChanged

    private void exaggerationCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_exaggerationCheckBoxItemStateChanged
        animation.getConfig().displacementExaggeration = (evt.getStateChange() == ItemEvent.SELECTED) ? FlyThruAnimation.standardExaggeration : 1;
}//GEN-LAST:event_exaggerationCheckBoxItemStateChanged

    private void abutmentsCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_abutmentsCheckBoxItemStateChanged
        animation.getConfig().showAbutments = (evt.getStateChange() == ItemEvent.SELECTED);
        animation.invalidateBackground();
}//GEN-LAST:event_abutmentsCheckBoxItemStateChanged

    private void truckCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_truckCheckBoxItemStateChanged
        animation.getConfig().showTruck = (evt.getStateChange() == ItemEvent.SELECTED);
}//GEN-LAST:event_truckCheckBoxItemStateChanged

    private void backgroundCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_backgroundCheckBoxItemStateChanged
        animation.getConfig().showBackground = (evt.getStateChange() == ItemEvent.SELECTED);
        animation.invalidateBackground();
}//GEN-LAST:event_backgroundCheckBoxItemStateChanged

    private void smoothTerrainCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_smoothTerrainCheckBoxItemStateChanged
        animation.getConfig().showSmoothTerrain = (evt.getStateChange() == ItemEvent.SELECTED);
        animation.invalidateBackground();
    }//GEN-LAST:event_smoothTerrainCheckBoxItemStateChanged

    @Action
    public void toggleAnimationDrop() {
        Dimension size = getSize();
        if (dropped) {
            size.height -= panelHeight;
            animationControlsPanel.setVisible(false);
            setSize(size);
            animationControlsDialogDropButton.setIcon(WPBDApp.getApplication().getIconResource("drop.png"));
            dropped = false;
        }
        else {
            size.height += panelHeight;
            setSize(size);
            animationControlsPanel.setVisible(true);
            animationControlsDialogDropButton.setIcon(WPBDApp.getApplication().getIconResource("undrop.png"));
            dropped = true;
        }
    }

    @Action
    public void playAnimation() {
        if (playButton.getIcon() == playIcon) {
            playButton.setIcon(pauseIcon);
            animation.getConfig().paused = false;
        }
        else {
            playButton.setIcon(playIcon);
            animation.getConfig().paused = true;
        }
    }

    @Action
    public void reset() {
        animation.reset();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox abutmentsCheckBox;
    private javax.swing.JButton animationControlsDialogDropButton;
    private javax.swing.JPanel animationControlsPanel;
    private javax.swing.JToolBar.Separator animationControlsSep02;
    private javax.swing.JToolBar animationControlsToolbar;
    private javax.swing.JCheckBox backgroundCheckBox;
    private javax.swing.JCheckBox colorsCheckBox;
    private javax.swing.JCheckBox exaggerationCheckBox;
    private javax.swing.JToggleButton playButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JToolBar.Separator sep100;
    private javax.swing.JCheckBox smoothTerrainCheckBox;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JCheckBox truckCheckBox;
    // End of variables declaration//GEN-END:variables
}
