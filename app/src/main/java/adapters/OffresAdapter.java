package adapters;

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

public class OffresAdapter extends RecyclerView.Adapter<OffresAdapter.ViewHolder> {

    ArrayList<Offre> offres;

    public OffresAdapter(ArrayList<Offre> offres) {
        this.offres = offres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_offres, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(String.valueOf(offres.get(position).getTitre()));
        holder.descriptionTxt.setText(String.valueOf(offres.get(position).getDescription()));
        holder.priceTxt.setText(String.valueOf(offres.get(position).getPrix()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(offres.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());

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
