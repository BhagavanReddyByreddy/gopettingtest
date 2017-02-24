package bhagavan.model;

/**
 * Created by bhagavan on 2/24/2017.
 */

public class EventDetails {



    String eventName;
    String eventEndDate;
    String eventPic;

    public EventDetails() {

    }

    public EventDetails(String eventName, String eventEndDate, String eventPic) {
        this.eventName = eventName;
        this.eventEndDate = eventEndDate;
        this.eventPic = eventPic;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPic() {
        return eventPic;
    }

    public void setEventPic(String eventPic) {
        this.eventPic = eventPic;
    }


}
