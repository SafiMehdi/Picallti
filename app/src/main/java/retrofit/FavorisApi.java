package retrofit;

import data.Favoris;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavorisApi {

    @POST("favoris/add")
    Call<Void> addFavoris(@Body Favoris favoris);

    @POST("favoris/check")
    Call<Boolean> checkIfExists(@Body Favoris favoris);

    @POST("favoris/remove")
    Call<Void> remove(@Body Favoris favoris);
}
