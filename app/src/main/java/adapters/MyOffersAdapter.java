package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.picallti.HomePageActivity;
import com.example.picallti.R;
import com.example.picallti.SingleOffreActivity;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import butterknife.OnClick;
import data.Offre;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.OffreApi;
import retrofit.RetrofitService;
import retrofit.VehiculeApi;
import retrofit.VehiculeTypeApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final Offre offre =offres.get(position);

        RetrofitService retrofitService = new RetrofitService();
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);

        ImageButton deleteboutton = holder.itemView.findViewById(R.id.delete);

        /*RetrofitService retrofitService = new RetrofitService();
        VehiculeTypeApi vehiculeTypeApi = retrofitService.getRetrofit().create(VehiculeTypeApi.class);
        VehiculeApi vehiculeApi = retrofitService.getRetrofit().create(VehiculeApi.class);
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);*/
        deleteboutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offreApi.removeOffreById(offre.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        System.out.println("remove");
                        offres.remove(offres.get(position));
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println("not remove");
                    }
                });
            }
        });
        /*deleteboutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offreApi.removeOffreById(offre.getId())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                System.out.println("responnnnseeet");
                                //startActivity(new Intent (MyOffersAdapter.this, HomePageActivity.class));
                                //Toast.makeText(MyOffersAdapter.this, "Offre Updated !", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Logger.getLogger(updateActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);

                            }
                        });
                /*
                String titre1 = titreEdit.getText().toString();
                String description1 = descEdit.getText().toString();
                float prix1 = Float.parseFloat(prixEdit.getText().toString());
                String ville = spinner.getSelectedItem().toString();
                String operation = spinner3.getSelectedItem().toString();
                String categorie = spinner2.getSelectedItem().toString();


                Offre offre = new Offre();
                o




                System.out.println("clickkkkkkk");
                Intent intentUpdate = new Intent(holder.itemView.getContext(), updateActivity.class);
                int photo;
                if (offre.getUrl() != null){
                    photo = holder.itemView.getContext().getResources().getIdentifier(offre.getUrl(), "drawable", holder.itemView.getContext().getPackageName());
                }
                else {
                    photo = offre.getImageId();
                }
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
                intentUpdate.putExtra("photo",photo);
                intentUpdate.putExtra("titre", offre.getTitre());
                intentUpdate.putExtra("prix", offre.getPrix());
                String date = "";
                if (offre.getLocalDateTime() != null){
                    date = offre.getLocalDateTime();
                }
                intentUpdate.putExtra("date", date);
                intentUpdate.putExtra("id", offre.getId());
                intentUpdate.putExtra("operation", offre.getOperation());
                intentUpdate.putExtra("localisation", offre.getLocalisation());
                intentUpdate.putExtra("description",offre.getDescription());
                intentUpdate.putExtra("time",time);
                intentUpdate.putExtra("operation",offre.getOperation());
                intentUpdate.putExtra("ville",offre.getVille());
                intentUpdate.putExtra("type",offre.getVehicule().getVehiculeType().getNom());
                String username = "";
                int phone = 0;
                if(offre.getUser() != null){
                    username = offre.getUser().getNom();
                    phone = offre.getUser().getPhone();
                }
                intentUpdate.putExtra("user",username);
                intentUpdate.putExtra("phone",phone);
                String marque = "";
                if(offre.getVehicule() != null){
                    marque = offre.getVehicule().getMarque();
                }
                intentUpdate.putExtra("vehicule", marque);
                holder.itemView.getContext().startActivity(intentUpdate);
            }
        });*/
        int drawableResourceId ;
        if (    offres.get(position).getUrl() != null){
            drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(offres.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());

        }
        else {
            drawableResourceId = offres.get(position).getImageId();
        }


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

            /*@OnClick(R.id.imageButton10)
            public void goToUpdate(){

                Intent intent = new Intent(itemView.getContext(), updateActivity.class);
                itemView.getContext().startActivity(intent);
            }*/

            //Button btn = (Button)findViewById(R.id.open_activity_button);

            /*
            updateboutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });*/
        }
    }


}
