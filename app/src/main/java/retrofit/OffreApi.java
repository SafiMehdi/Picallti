package retrofit;

import java.util.Collection;
import java.util.List;

import data.Offre;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OffreApi {
    @GET("/offers/getAll")
    Call<List<Offre>> getOffers();

    @GET("/offers/getAllByUser/{id}")
    Call <List<Offre>> getAllOffersByUser(@Path("id") int id);

    @POST("/offers/add")
    Call<Void> addOffre(@Body Offre offre);

    @POST("/offers/update")
    Call<Void> update(@Body Offre offre);

    @GET("/offers/offrebyvehiculetype")
    Call<List<Offre>> getOffersByVehiculeType(@Query("vehiculeTypeName") String vehiculeTypeName);

    @GET("/offers/offrebyprice")
    Call<List<Offre>> filterOffresByPrix(@Query("min") float min,@Query("max") float max);

    @GET("/offers/getByVille")
    Call<List<Offre>> getOffreByVille(@Query("ville") String ville);

    @GET("/offers/getByDateDesc")
    Call<List<Offre>> getOffreByDateDesc();

    @GET("/offers/searchtitle")
    Call<List<Offre>> searchTitleContaining(@Query("title") String title);




    @GET("/offers/remove")
    Call<Void> removeOffreById(@Query("id") int id);


}
