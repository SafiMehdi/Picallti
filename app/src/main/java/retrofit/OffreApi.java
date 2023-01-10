package retrofit;

import java.util.List;

import data.Offre;
import data.User;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OffreApi {
    @GET("/offers/getAll")
    Call<List<Offre>> getOffers();

    @POST("/offers/add")
    Call<Void> addOffre(@Body Offre offre);

    @POST("/offers/update")
    Call<Void> update(@Body Offre offre);



}
