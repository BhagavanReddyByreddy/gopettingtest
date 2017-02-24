package bhagavan.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import bhagavan.gopettingtest.R;
import bhagavan.model.EventDetails;

/**
 * Created by bhagavan on 2/24/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsHolder> {

    View itemView;
    ArrayList<EventDetails> eventsList;
    Activity activity;

    public EventsAdapter(Activity activity, ArrayList<EventDetails> eventsArrayList) {
        this.eventsList = eventsArrayList;
        this.activity = activity;
    }

    @Override
    public EventsAdapter.EventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new EventsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.EventsHolder holder, final int position) {
                 EventDetails event = eventsList.get(position);
        holder.eventName.setText(event.getEventName());
        holder.eventDate.setText(event.getEventName());
        //String personPhotoUrl = event.getEventPic().toString();
        String personPhotoUrl = event.getEventPic();
        Glide.with(activity).load(personPhotoUrl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.eventPic);

        holder.removeEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

   /* @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addButton){

        }else{

        }
    }*/

    public class EventsHolder extends RecyclerView.ViewHolder {

        TextView eventName,eventDate;
        ImageView eventPic;
        Button removeEvent;

        public EventsHolder(View itemView) {
            super(itemView);

            eventName = (TextView) itemView.findViewById(R.id.eventName);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
            eventPic = (ImageView) itemView.findViewById(R.id.eventPic);
            removeEvent = (Button) itemView.findViewById(R.id.removeButton);

        }
    }
}
