package retrofit;

import java.util.Collection;

import data.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

        @POST("/users/add")
        Call<Void> addUser(@Body User user);

        @GET("/users/getAll")
        Call<Collection<User>> getAllUsers();

}
