package es.olidroide;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class NotesApiConfig {

    private static NotesApiConfig singleton;

    private final Retrofit retrofit;
    private final boolean isDebug;

    public NotesApiConfig(Retrofit retrofit, boolean debug) {
        this.retrofit = retrofit;
        this.isDebug = debug;
    }

    public static NotesApiConfig with() {
        if (singleton == null) {
            singleton = new Builder().build();
        }

        return singleton;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static class Builder {
        private static final String API_BASE_URL = "https://notes-api-olidroide.c9users.io/";

        private String baseUrl = API_BASE_URL;
        private boolean debug = true;
        private Retrofit retrofit;

        public Builder debug() {
            this.debug = true;
            return this;
        }

        public NotesApiConfig build() {
            if (retrofit == null) {
                retrofit = createDefaultRetrofit(baseUrl, debug);
            }

            return new NotesApiConfig(retrofit, debug);
        }

        private Retrofit createDefaultRetrofit(String baseUrl, boolean debug) {
            OkHttpClient client = new OkHttpClient();
            if (debug) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client.interceptors().add(interceptor);
            }

            Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

            Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

            return retrofit;
        }
    }
}
