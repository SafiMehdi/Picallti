package retrofit;

import java.util.Collection;

import data.LoginRequest;
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

public interface ImageDataApi {
    @Multipart
    @POST("/imageData/upload")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file);

    @GET("/imageData/{fileName}")
    Call<ResponseBody> downloadImage(@Path("fileName") String fileName);


}
