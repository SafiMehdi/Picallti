package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.picallti.R;
import com.example.picallti.SingleOffreActivity;

import java.util.ArrayList;

import butterknife.OnClick;
import data.Offre;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.picallti.updateActivity;


public class MyOffersAdapter extends RecyclerView.Adapter<MyOffersAdapter.ViewHolder>{
    ArrayList<Offre> offres;
    private LayoutInflater mInflater;

    private Context context;

    public MyOffersAdapter(Context context) {
        this.context = context;
    }
    //Context context;


    public MyOffersAdapter(@NonNull Context context, ArrayList<Offre> offres) {
        this.mInflater = LayoutInflater.from(context);
        this.offres = offres;
        //this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = mInflater.from(parent.getContext()).inflate(R.layout.view_holder_my_offers, parent, false);
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
                String time = "";
                if(offre.getLocalDateTime() != null & offre.getTime()!= null){
                    time =   offre.getLocalDateTime() +  " "+offre.getTime().substring(0,5);
                }
                else {
                    if(offre.getTime()!= null){
                        time =   offre.getLocalDateTime() ;
                    }
                    if(offre.getLocalDateTime()!= null){
                        time =   offre.getLocalDateTime();
                    }
                }
                intent.putExtra("photo",photo);
                intent.putExtra("titre", offre.getTitre());
                intent.putExtra("prix", offre.getPrix());
                String date = "";
                if (offre.getLocalDateTime() != null){
                    date = offre.getLocalDateTime();
                }
                intent.putExtra("date", date);
                intent.putExtra("id", offre.getId());
                intent.putExtra("operation", offre.getOperation());
                intent.putExtra("localisation", offre.getLocalisation());
                intent.putExtra("description",offre.getDescription());
                intent.putExtra("time",time);
                String username = "";
                int phone = 0;
                if(offre.getUser() != null){
                    username = offre.getUser().getNom();
                    phone = offre.getUser().getPhone();
                }
                intent.putExtra("user",username);
                intent.putExtra("phone",phone);
                String marque = "";
                if(offre.getVehicule() != null){
                    marque = offre.getVehicule().getMarque();
                }
                intent.putExtra("vehicule", marque);
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
            ImageButton updateboutton = itemView.findViewById(R.id.imageButton10);

            /*@OnClick(R.id.imageButton10)
            public void goToUpdate(){

                Intent intent = new Intent(itemView.getContext(), updateActivity.class);
                itemView.getContext().startActivity(intent);
            }*/

            //Button btn = (Button)findViewById(R.id.open_activity_button);

            updateboutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), updateActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });/*
            updateboutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });*/
        }
    }


}
