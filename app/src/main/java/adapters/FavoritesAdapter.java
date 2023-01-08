package adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import data.Favoris;
import data.Offre;

import com.example.picallti.R;
import com.example.picallti.SingleOffreActivity;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    ArrayList<Favoris> favoris;

    public FavoritesAdapter(ArrayList<Favoris> favoris) {
        this.favoris = favoris;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_favorites, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(String.valueOf(favoris.get(position).getOffre().getTitre()));
        holder.descriptionTxt.setText(String.valueOf(favoris.get(position).getOffre().getDescription()));
        holder.priceTxt.setText(String.valueOf(favoris.get(position).getOffre().getPrix()));

        int drawableResourceId;
        if (favoris.get(position).getOffre().getUrl() != null){
             drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(favoris.get(position).getOffre().getUrl(), "drawable", holder.itemView.getContext().getPackageName());

        }else {
             drawableResourceId = favoris.get(position).getOffre().getImageId();
        }
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.removeItem);


        Offre offre = favoris.get(position).getOffre();
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int photo;
                if (offre.getUrl() != null){
                    photo = holder.itemView.getContext().getResources().getIdentifier(offre.getUrl(), "drawable", holder.itemView.getContext().getPackageName());
                }
                else {
                    photo = offre.getImageId();
                }

                Intent intent = new Intent(v.getContext(), SingleOffreActivity.class);
                String time = offre.getLocalDateTime() + " " + offre.getTime().substring(0, 5);
                intent.putExtra("photo", photo);
                intent.putExtra("titre", offre.getTitre());
                intent.putExtra("prix", offre.getPrix());
                intent.putExtra("date", offre.getLocalDateTime().toString());
                intent.putExtra("operation", offre.getOperation());
                intent.putExtra("localisation", offre.getLocalisation());
                intent.putExtra("description", offre.getDescription());
                intent.putExtra("time", time);
                intent.putExtra("user", offre.getUser().getNom());
                intent.putExtra("phone", offre.getUser().getPhone());
                intent.putExtra("vehicule", offre.getVehicule().getMarque());
                intent.putExtra("id",offre.getId());
                v.getContext().startActivity(intent);
            }

        });
    }


    @Override
    public int getItemCount() {
        return favoris.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt,descriptionTxt,priceTxt;
        ImageView removeItem;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            descriptionTxt = itemView.findViewById(R.id.descriptionTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            removeItem = itemView.findViewById(R.id.img_view);
        }
    }
}
