package com.example.picallti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OffrePageActivity extends AppCompatActivity {

    @BindView(R.id.offre_list)
    ListView offre_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre_page);
        ButterKnife.bind(this);

        ArrayList <Offre> offres = new ArrayList<Offre>();
        offres.add(new Offre(R.drawable.imagea, "VELO VILLE ELOPS 520 CADRE BAS MINT B'TWIN", "Conçu pour faire du vélo en ville pour femme, et y prendre du plaisir !", "Rabat, Morocco", "12DH/DAY", "17min"));
        offres.add(new Offre(R.drawable.imageb, "VTT 340 GRIS 26 ROCKRIDER", "Conçu pour vous évader lors de vos randonnées VTT, dépensez-vous et amusez vous face à de petits obstacles, en ville ou en pleine nature", "Rabat, Morocco", "12DH/DAY", "17min"));
        offres.add(new Offre(R.drawable.imagec, "VTT ROCKRIDER 300", "VTT Conçu pour les jeunes aventuriers...", "Beni-Mellal,Morocco", "25DH/DAY", "20min"));

        OffreAdapter adapter = new OffreAdapter(getApplicationContext(), R.layout.activity_cellule_offre_page, offres);
        offre_list.setAdapter(adapter);

        offre_list.setClickable(true);

        String[] titre ={"VELO VILLE ELOPS 520 CADRE BAS MINT B'TWIN", "VTT 340 GRIS 26 ROCKRIDER", "VTT ROCKRIDER 300"};
        Integer[] imageId ={R.drawable.imagea, R.drawable.imageb, R.drawable.imagec};
        String[] description = {"Conçu pour faire du vélo en ville pour femme, et y prendre du plaisir !", "Conçu pour vous évader lors de vos randonnées VTT, dépensez-vous et amusez vous face à de petits obstacles, en ville ou en pleine nature","VTT Conçu pour les jeunes aventuriers..." };
        String[] localisation = {"Rabat, Morocco", "Rabat, Morocco", "Beni-Mellal,Morocco"};
        String[] prix ={"12DH/DAY", "12DH/DAY", "25DH/DAY"};
        String[] time ={"17min", "17min", "20min"};

        ArrayList<Offre> offreArrayList = new ArrayList<>();

        for(int i = 0; i < titre.length; i++){
            Offre offre = new Offre(titre[i], imageId[i], description[i], localisation[i], prix[i], time[i]);
            offreArrayList.add(offre);
        }

        offre_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent OffrePageActivity = new Intent(getApplicationContext(), DetailsActivity.class);
                OffrePageActivity.putExtra("titre", titre[position]);
                //OffrePageActivity.putExtra("imageId", imageId[position]);
                OffrePageActivity.putExtra("description", description[position]);
                OffrePageActivity.putExtra("localisation", localisation[position]);
                OffrePageActivity.putExtra("prix", prix[position]);
                OffrePageActivity.putExtra("time", time[position]);
                startActivity(OffrePageActivity);

            }
        });
}

class OffreAdapter extends ArrayAdapter <Offre> {

    private ArrayList<Offre> offres;

    public OffreAdapter(Context context, int ressource, ArrayList<Offre> offres) {
        super(context, ressource, offres);
        this.offres = offres;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_cellule_offre_page, parent, false);

        ImageView image = convertView.findViewById(R.id.imageView3);
        image.setBackgroundResource(offres.get(position).getImageId());
        TextView viewTitle = (TextView) convertView.findViewById(R.id.textView4);
        viewTitle.setText(offres.get(position).getTitre());
        TextView viewDescription = (TextView) convertView.findViewById(R.id.textView5);
        viewDescription.setText(offres.get(position).getDescription());
        TextView viewLocalisation = (TextView) convertView.findViewById(R.id.textView6);
        viewLocalisation.setText(offres.get(position).getLocalisation());
        TextView viewPrice = (TextView) convertView.findViewById(R.id.textView7);
        viewPrice.setText(offres.get(position).getPrix());
        TextView viewTime = (TextView) convertView.findViewById(R.id.textView8);
        viewTime.setText(offres.get(position).getTime());

        return convertView;
    }
}

}