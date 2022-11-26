package adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.picallti.R;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import data.Notification;

public class NotificationAdapter extends ArrayAdapter<Notification> {

    ArrayList<Notification> notifications;

    public NotificationAdapter(@NonNull Context context, int resource, ArrayList<Notification> notifications) {
        super(context, resource, notifications);
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.notification_list_element,parent,false);

        ImageView imageView = convertView.findViewById(R.id.notification_icon);
        imageView.setBackgroundResource(notifications.get(position).getIcon());
        TextView title = convertView.findViewById(R.id.notification_title);
        title.setText(notifications.get(position).getTitle());
        if(notifications.get(position).getText()!= null){
            TextView text = convertView.findViewById(R.id.notification_text);
            text.setText(notifications.get(position).getText());
        }
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
        //mTimeText.setText("Time: " + dateFormat.format(date));
        TextView time= convertView.findViewById(R.id.notification_time);
        time.setText(dateFormat.format(notifications.get(position).getTime()).toString());
        return convertView;
    }
}
