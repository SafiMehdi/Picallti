package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import data.Commentaire;
import data.Notification;
import com.example.picallti.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    ArrayList<Notification> notifications;
    private LayoutInflater mInflater;

    public NotificationAdapter(@NonNull Context context,ArrayList<Notification> notifications) {
        this.mInflater = LayoutInflater.from(context);
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_notification, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(String.valueOf(notifications.get(position).getTitle()));
        holder.descriptionTxt.setText(String.valueOf(notifications.get(position).getText()));
        DateTimeFormatter formatter
                =DateTimeFormatter.ofPattern("HH:mm");

        holder.timeTxt.setText(String.valueOf(notifications.get(position).getTime()));

        int drawableResourceId;

    }


    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt,descriptionTxt,timeTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            descriptionTxt = itemView.findViewById(R.id.descriptionTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
        }
    }
}