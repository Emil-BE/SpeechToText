package org.example;

import javax.swing.*;
import java.awt.*;

public class VoiceRecorderGUI extends JFrame {
    private final JButton startButton;
    private final JButton stopButton;

    private final VoiceRecorderController controller;

    public VoiceRecorderGUI() {
        setTitle("Voice Recorder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");

        addActionListener(startButton);
        addActionListener(stopButton);

        stopButton.setEnabled(false);

        add(startButton);
        add(stopButton);

        controller = new VoiceRecorderController();
    }

    private void addActionListener(JButton jButton) {
        var enabled = jButton.getText().equals("Start");
        jButton.addActionListener(
                e -> {
                    if (enabled) {
                        controller.startRecording();
                    } else {
                        controller.stopRecording();
                    }
                    startButton.setEnabled(!enabled);
                    stopButton.setEnabled(enabled);
                });
    }
}
