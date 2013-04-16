package info.kodelogic.POJO;

public class SurveyDetails {
  private int id;
  private String name;
  private String survey_type;
  private String publish;

  public SurveyDetails(int id,String name, String survey_type,String publish)
  {
    this.id=id;
    this.name=name;
    this.survey_type=survey_type;
    this.publish=publish;
  }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getSurvey_type() {
	return survey_type;
}

public void setSurvey_type(String survey_type) {
	this.survey_type = survey_type;
}

public String getPublish() {
	return publish;
}

public void setPublish(String publish) {
	this.publish = publish;
}
  
  

}
