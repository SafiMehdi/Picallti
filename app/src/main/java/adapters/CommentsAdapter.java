package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;

import data.Commentaire;
import data.Offre;
import okhttp3.ResponseBody;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.picallti.R;
import com.example.picallti.SingleOffreActivity;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    ArrayList<Commentaire> commentaires;
    private LayoutInflater mInflater;

    public CommentsAdapter(@NonNull Context context, ArrayList<Commentaire> commentaires) {
        this.mInflater = LayoutInflater.from(context);
        this.commentaires = commentaires;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = mInflater.from(parent.getContext()).inflate(R.layout.view_holder_comment, parent, false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.commentTxt.setText(String.valueOf(commentaires.get(position).getCommentaire()));
        holder.usernameTxt.setText(String.valueOf(commentaires.get(position).getUser().getNom()));
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        userApi.downloadImage(commentaires.get(position).getUser().getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    System.out.println("success");
                    byte[] r;
                    try {
                        r = response.body().bytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(r, 0, r.length);
                        holder.userImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("failed ! ");
            }
        });
        //int drawableResourceId = commentaires.get(position).getUser().getPhoto();

        final Commentaire commentaire = commentaires.get(position);

        //Glide.with(holder.itemView.getContext())
               // .load(drawableResourceId)
                //.into(holder.userImage);
    }


    @Override
    public int getItemCount() {
        return commentaires.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTxt,commentTxt;
        ImageView userImage;

        public ViewHolder(View itemView) {
            super(itemView);
            usernameTxt = itemView.findViewById(R.id.usernameTxt);
            commentTxt = itemView.findViewById(R.id.commentTxt);
            userImage = itemView.findViewById(R.id.img_view);
        }
    }


}
