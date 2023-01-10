package retrofit;

import java.util.Collection;

import data.Notification;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotificationApi {

    @POST("/notification/add")
    Call<Void> addNotification(@Body Notification notification);

    @GET("/notification/findallbyuser/{id}")
    Call<Collection<Notification>> getNotificationByUser(@Path("id") int id);
}
