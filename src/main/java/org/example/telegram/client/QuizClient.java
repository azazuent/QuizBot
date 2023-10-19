package org.example.telegram.client;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import org.example.telegram.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QuizClient {

    @Autowired
    private OkHttpClient okHttpClient;

    @Value("${server.hostname}")
    private String hostname;

    public String getRequest(String url) throws ServiceException {
        var request = new Request.Builder()
                .url(url)
                .build();
        try {
            var response = okHttpClient.newCall(request).execute();
            var body = response.body();
            return body == null ? null : body.string();
        } catch(IOException e){
            throw new ServiceException("Ошибка", e);
        }
    }

    public String randomQuestion() throws Exception {
        return getRequest(hostname.concat("/getRandomQuestion"));
    }
}
