package cl.ucn.disc.dsm.fherrera.news.services;


import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.network.APIClient;
import com.kwabenaberko.newsapilib.network.APIService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.ucn.disc.dsm.fherrera.news.utils.Validations;
import retrofit2.Response;

public final class NewsApiService {

    private final String apiKey;

    private final APIService apiService;
    public NewsApiService(String apiKey) {
        Validations.notNull(apiKey, "apiKey");
        this   .apiKey = apiKey;
        this   .apiService = APIClient.getAPIService();
    }
    public List<Article> getTopHeadlines(final String category, final Integer pageSize) throws IOException {
        Validations.notNull(category, "category");
        Validations.notNull(pageSize, "pageSize");
        if (pageSize < 1) {
            throw new IllegalArgumentException("Error: pageSize need to be >0");
        }
        Map<String, String> query = new HashMap<>();
        query.put("apiKey", this.apiKey);
        query.put("category", category);
        query.put("pageSize", pageSize.toString());
        Response<ArticleResponse> response = apiService.getTopHeadlines(query).execute();
        if (response.isSuccessful()) {
            return response.body().getArticles();
        }
        throw new RuntimeException("Error: " + response.code() + " --> " + response.errorBody().string());
    }
}
