package org.example;

import javax.sound.sampled.*;
import java.io.File;

class VoiceRecorderModel {
    private static final int SAMPLE_RATE = 44100;
    private static final int SAMPLE_SIZE_IN_BITS = 16;
    private static final int CHANNELS = 1;
    private static final String OUTPUT_FILE = "recorded_audio.wav";
    private static final boolean SIGNED = true;
    private static final boolean BIG_ENDIAN = false;

    private TargetDataLine targetLine;
    private VoiceRecorderView view;

    public void setView(VoiceRecorderView view) {
        this.view = view;
    }

    public void startRecording() {
        try {
            var audioFormat =
                    new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, SIGNED, BIG_ENDIAN);
            var targetInfo = new DataLine.Info(TargetDataLine.class, audioFormat);

            if (!AudioSystem.isLineSupported(targetInfo)) {
                System.out.println("TargetDataLine not supported.");
                return;
            }

            targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetLine.open(audioFormat);
            targetLine.start();

            System.out.println("Recording started...");

            var recordingThread =
                    new Thread(
                            () -> {
                                var audioInputStream = new AudioInputStream(targetLine);
                                try {
                                    AudioSystem.write(
                                            audioInputStream, AudioFileFormat.Type.WAVE, new File(OUTPUT_FILE));
                                    var transcribedText =
                                            SpeechToTextService.transcribeAudio(new File(OUTPUT_FILE));
                                    view.showTranscribedText(transcribedText,1);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            });

            recordingThread.start();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void stopRecording() {
        if (targetLine != null) {
            targetLine.stop();
            targetLine.close();
            System.out.println("Recording stopped.");
        }
    }
}
