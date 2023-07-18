package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;

class VoiceRecorderView {
    private VoiceRecorderController controller;

    public void setController(VoiceRecorderController controller) {
        this.controller = controller;
    }

    public void showTranscribedText(String text, int... a) {
        if(a.length>0){
            System.out.println(text);
            return;
        }
        try {
            var robot = new Robot();
            var delay = 5000;
            robot.delay(delay);

            for (var c : text.toCharArray()) {
                var keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
