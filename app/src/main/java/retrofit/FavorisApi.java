package retrofit;

import data.Favoris;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FavorisApi {

    @POST("favoris/add")
    public Call<Void> addFavoris(@Body Favoris favoris);
}
