package retrofit;

import java.util.Collection;

import data.LoginRequest;
import data.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

        @POST("/users/add")
        Call<Void> addUser(@Body User user);

        @POST("/users/login")
        Call<User> loginUser(@Body LoginRequest loginRequest);

        @GET("/users/getAll")
        Call<Collection<User>> getAllUsers();


        @POST("/users/update")
        Call<Void> updateUser(@Body User user);

        @Multipart
        @POST("/users/updateWithImage/{id}")
        Call <User> updateUserWithImage(@Path("id") int id,
                                        @Part MultipartBody.Part file);

        @GET(value = "/users/downloadImage")
        Call <ResponseBody> downloadImage(@Query("id") int id);

        @DELETE("users/remove/{id}")
        Call <Void> deleteUserById(@Path("id") Integer id);
}
