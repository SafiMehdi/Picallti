package adapters;
import com.example.picallti.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

public class AnnoncesListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] maintitle;
    private final String[] description;
    private final String[] localisation;
    private final String[] prix;
    private final String[] temps;
    private final Integer[] imgid;

    public AnnoncesListAdapter(Activity context, String[] maintitle,String[] description,String[] localisation, String[] prix, String[] temps, Integer[] imgid) {
        super(context, R.layout.activity_annonces_list, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.description=description;
        this.localisation=localisation;
        this.prix=prix;
        this.temps=temps;
        this.imgid=imgid;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_annonces_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.titreAnnonces);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView desc = (TextView) rowView.findViewById(R.id.descriptionAnnonces);
        TextView price = (TextView) rowView.findViewById(R.id.prixAnnoncesTextView);
        TextView location = (TextView) rowView.findViewById(R.id.localisationTextView);
        TextView time = (TextView) rowView.findViewById(R.id.tempsAnnoncesTextView);

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        desc.setText(description[position]);
        price.setText(prix[position]);
        location.setText(localisation[position]);
        time.setText(temps[position]);


        return rowView;

    };
}