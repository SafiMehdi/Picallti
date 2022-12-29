package retrofit;

import java.util.List;

import data.Offre;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OffreApi {
    @GET("/offers/getAll")
    Call<List<Offre>> getOffers();

    @POST("/offers/add")
    Call<Void> addOffre(@Body Offre offre);
}
