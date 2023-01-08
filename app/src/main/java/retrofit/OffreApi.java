package retrofit;

import java.util.List;

import data.Offre;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OffreApi {
    @GET("/offers/getAll")
    Call<List<Offre>> getOffers();

    @GET("/offers/getAllByUser")
    Call<List<Offre>> getOffersByUser(@Query("id") int id);

    @POST("/offers/add")
    Call<Void> addOffre(@Body Offre offre);

    @POST("/offers/update")
    Call<Void> update(@Body Offre offre);


}
