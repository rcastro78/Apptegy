package info.kodelogic.POJO;

public class DistrictEventsDetails {
  private String id;
  private String title, description, start, end, allDay, venue,eventable_type;

  public DistrictEventsDetails(String title, String description, String venue,
                               String allDay, String eventable_type, String start, String end) {
    
    this.title = title;
    this.description = description;
    this.start = start;
    this.end = end;
    this.allDay = allDay;
    this.venue = venue;
    this.eventable_type = eventable_type;
  }

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getStart() {
	return start;
}

public void setStart(String start) {
	this.start = start;
}

public String getEnd() {
	return end;
}

public void setEnd(String end) {
	this.end = end;
}

public String getAllDay() {
	return allDay;
}

public void setAllDay(String allDay) {
	this.allDay = allDay;
}

public String getVenue() {
	return venue;
}

public void setVenue(String venue) {
	this.venue = venue;
}

public String getEventable_type() {
	return eventable_type;
}

public void setEventable_type(String eventable_type) {
	this.eventable_type = eventable_type;
}

  


}