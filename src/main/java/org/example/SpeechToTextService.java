package org.example;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class SpeechToTextService {
    private final static String URL = "https://api.openai.com/v1/audio/transcriptions";
    private final static String TOKEN = "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";
    private final static String MODEL_VALUE = "whisper-1";

    public static String transcribeAudio(File file) {
        var responseBody = "";
        var httpClient = HttpClientBuilder.create().build();
        var request = new HttpPost(URL);

        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer ".concat(TOKEN));


        var entity = MultipartEntityBuilder.create()
                .addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName())
                .addTextBody("model", MODEL_VALUE)
                .build();

        request.setEntity(entity);

        try {
            var response = httpClient.execute(request);
            var responseEntity = response.getEntity();
            responseBody = EntityUtils.toString(responseEntity);
            EntityUtils.consume(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBody;
    }
}
