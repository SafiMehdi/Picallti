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
import data.VehiculeType;
import com.example.picallti.R;


public class VehiculeTypesAdapter extends RecyclerView.Adapter<VehiculeTypesAdapter.ViewHolder> {

    ArrayList<VehiculeType> vehiculeTypes;

    public VehiculeTypesAdapter(ArrayList<VehiculeType> vehiculeTypes) {
        this.vehiculeTypes = vehiculeTypes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_vehicule_type, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(String.valueOf(vehiculeTypes.get(position).getNom()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(vehiculeTypes.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.removeItem);
    }


    @Override
    public int getItemCount() {
        return vehiculeTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView removeItem;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            removeItem = itemView.findViewById(R.id.img_view);
        }
    }
}
