package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.picallti.LikedPosts;
import com.example.picallti.R;

import java.util.ArrayList;

public class AdapterFavoris extends ArrayAdapter<LikedPosts> {
    private ArrayList<LikedPosts> likedPostsArrayList;

    public AdapterFavoris(@NonNull Context context, int resource, ArrayList<LikedPosts> likedPostsArrayList) {
        super(context, resource, likedPostsArrayList);
        this.likedPostsArrayList = likedPostsArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LikedPosts likedPosts= getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageId);
        TextView priceView = (TextView) convertView.findViewById(R.id.PostPrice);
        TextView titleView = (TextView) convertView.findViewById(R.id.PostTitle);

        imageView.setImageResource(likedPosts.getImageLiked());
        priceView.setText(likedPosts.getPrice());
        titleView.setText(likedPosts.getTitle());
        return convertView;
    }
}
