package es.olidroide;

import com.google.gson.Gson;
import java.io.IOException;
import retrofit.Call;
import retrofit.Response;

public class NotesApiClient {

    private final NotesApiConfig notesApiConfig;

    public NotesApiClient(NotesApiConfig notesApiConfig) {
        this.notesApiConfig = notesApiConfig;
    }

    public <T> T getApi(Class<T> apiRest) {
        return notesApiConfig.getRetrofit().create(apiRest);
    }

    public <T> T execute(Call<T> call) throws NotesApiException {
        Response<T> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new NotesApiException("Network error", e);
        }
        if (response.isSuccess()) {
            return response.body();
        } else {
            parseError(response);
            return null;
        }
    }

    private void parseError(Response execute) throws NotesApiException {
        //String marvelCode = "";
        //String marvelDescription = "";
        if (execute.errorBody() != null) {
            Gson gson = new Gson();
            try {
                String errorBody = execute.errorBody().string();
                //MarvelError marvelError = gson.fromJson(errorBody, MarvelError.class);
                //marvelCode = marvelError.getCode();
                //marvelDescription = marvelError.getMessage();
                //if (marvelDescription == null || "".equals(marvelDescription)) {
                //    marvelDescription = marvelError.getStatus();
                //}
            } catch (IOException e) {
            }
        }

        //if(execute.code() == INVALID_AUTH_CODE) {
        //    throw new MarvelAuthApiException(execute.code(), marvelCode, marvelDescription, null);
        //} else {
        //    throw new MarvelApiException(execute.code(), marvelCode, marvelDescription, null);
        //}
    }
}
