package retrofit;

import data.Vehicule;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VehiculeApi {
    @GET("/vehicule/getLast")
    Call<Vehicule> getLastVehicule();

    @POST("/vehicule/add")
    Call<Void> addVehicule(@Body Vehicule vehicule);

}
