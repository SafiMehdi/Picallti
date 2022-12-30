package retrofit;

import java.util.Collection;

import data.Commentaire;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentApi {

    @POST("/commentaires/add")
    Call<Void> addComment(@Body Commentaire commentaire);

    @GET("/commentaires/getAllByOffre/{id}")
    Call<Collection<Commentaire>> getAllCommentairesByOffre(@Path("id") int id);
}
