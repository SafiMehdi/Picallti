package retrofit;

import java.util.Collection;
import java.util.Optional;

import data.LoginRequest;
import data.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

        @POST("/users/add")
        Call<Void> addUser(@Body User user);

        @POST("/users/login")
        Call<User> loginUser(@Body LoginRequest loginRequest);

        @GET("/users/getAll")
        Call<Collection<User>> getAllUsers();

        @POST("/users/remove/{id}")
        Call<Void> removeUser(@Path("id") int id);

}
