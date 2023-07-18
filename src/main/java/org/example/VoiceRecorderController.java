package org.example;

public class VoiceRecorderController {
    private final VoiceRecorderModel model;

    public VoiceRecorderController() {
        model = new VoiceRecorderModel();
        var view = new VoiceRecorderView();

        view.setController(this);
        model.setView(view);
    }

    public void startRecording() {
        model.startRecording();
    }

    public void stopRecording() {
        model.stopRecording();

    }
}
