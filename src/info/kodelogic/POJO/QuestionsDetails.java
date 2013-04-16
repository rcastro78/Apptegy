package info.kodelogic.POJO;

public class QuestionsDetails {

	private String content;
	private int idSurvey;
	
	public QuestionsDetails( int idSurvey,String content)
	{
		this.idSurvey=idSurvey;
		this.content=content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdSurvey() {
		return idSurvey;
	}

	public void setIdSurvey(int idSurvey) {
		this.idSurvey = idSurvey;
	}
	
	
}
