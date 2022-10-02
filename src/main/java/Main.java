import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

//    Добавление объекта запроса

        HttpGet request = new HttpGet(
                "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");

//   вызов удаленного сервиса
        CloseableHttpResponse response = httpClient.execute(request);

//   код для преобразования JSON в Java

        List<Post> posts = mapper.readValue(response.getEntity().getContent(),
                new TypeReference<List<Post>>() {
                });

 //   фильтр нужных значений

        posts.stream()
                .filter(getUpvotes -> getUpvotes.upvotes != 0).forEach(System.out::println);
    }
    public static ObjectMapper mapper = new ObjectMapper();
}