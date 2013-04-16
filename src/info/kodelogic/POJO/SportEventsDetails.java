package info.kodelogic.POJO;

public class SportEventsDetails {
  private String date, address, _time, away_score, away_team, city_state,
                 home_score, home_team, place, zip, date_text;


  public SportEventsDetails(int id, String away_score,String away_team,String home_score,
		  String home_team, String place, String address, String city_state, String zip,
		  String date_text) {
    this.date = date;
    this.address = address;
    this._time = _time;
    this.away_score = away_score;
    this.away_team = away_team;
    this.city_state = city_state;
    this.home_score = home_score;
    this.home_team = home_team;
    this.place = place;
    this.zip = zip;
    this.date_text = date_text;
  }

  public String getDateText() {
    return date_text;
  }

  public void setDateText(String date_text) {
    this.date_text = date_text;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String get_time() {
    return _time;
  }

  public void set_time(String _time) {
    this._time = _time;
  }

  public String getAway_score() {
    if (away_score == "null")
    	{
    	away_score="0";
    	}
    return away_score;
  }

  public void setAway_score(String away_score) {
    this.away_score = away_score;
  }

  public String getAway_team() {
    return away_team;
  }

  public void setAway_team(String away_team) {
    this.away_team = away_team;
  }

  public String getCity_state() {
    return city_state;
  }

  public void setCity_state(String city_state) {
    this.city_state = city_state;
  }

  public String getHome_score() {
    if (home_score == "null") {
    	home_score="0";
    } 
    return home_score;
  }

  public void setHome_score(String home_score) {
    this.home_score = home_score;
  }

  public String getHome_team() {
    return home_team;
  }

  public void setHome_team(String home_team) {
    this.home_team = home_team;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }
}