package adapters;

import android.content.Context;
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
import data.Offre;
import com.example.picallti.R;
import com.example.picallti.SingleOffreActivity;

public class OffresAdapter extends RecyclerView.Adapter<OffresAdapter.ViewHolder> {

    ArrayList<Offre> offres;
    private LayoutInflater mInflater;
    //Context context;

    public OffresAdapter(@NonNull Context context, ArrayList<Offre> offres) {
        this.mInflater = LayoutInflater.from(context);
        this.offres = offres;
        //this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = mInflater.from(parent.getContext()).inflate(R.layout.view_holder_offres, parent, false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(String.valueOf(offres.get(position).getTitre()));
        holder.descriptionTxt.setText(String.valueOf(offres.get(position).getDescription()));
        holder.priceTxt.setText(String.valueOf(offres.get(position).getPrix()));
        int drawableResourceId ;
        if (    offres.get(position).getUrl() != null){
            drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(offres.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());

        }
        else {
             drawableResourceId = offres.get(position).getImageId();
        }

        final Offre offre =offres.get(position);

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
                String time =   offre.getLocalDateTime().toString() +  " "+offre.getTime().toString().substring(0,5);
                intent.putExtra("photo",photo);
                intent.putExtra("titre", offre.getTitre());
                intent.putExtra("prix", offre.getPrix());
                intent.putExtra("date", offre.getLocalDateTime().toString());
                intent.putExtra("operation", offre.getOperation());
                intent.putExtra("localisation", offre.getLocalisation());
                intent.putExtra("description",offre.getDescription());
                intent.putExtra("time",time);
                intent.putExtra("user",offre.getUser().getNom());
                intent.putExtra("phone",offre.getUser().getPhone());
                intent.putExtra("vehicule", offre.getVehicule().getMarque());
                v.getContext().startActivity(intent);

            }
        });
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.removeItem);
    }


    @Override
    public int getItemCount() {
        return offres.size();
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
