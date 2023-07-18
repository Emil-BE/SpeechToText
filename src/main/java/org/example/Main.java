package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VoiceRecorderGUI recorderGUI = new VoiceRecorderGUI();
            recorderGUI.setVisible(true);
        });
    }
}
