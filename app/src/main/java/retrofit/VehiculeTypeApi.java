package retrofit;

import data.VehiculeType;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VehiculeTypeApi {
    @GET("/vehiculetype/getbyname")
    Call<VehiculeType> getTypeByName(@Query("nom") String nom);
}
