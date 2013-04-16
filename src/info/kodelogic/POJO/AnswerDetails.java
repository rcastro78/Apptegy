package info.kodelogic.POJO;

public class AnswerDetails {
  private String content;
  private String qContent;
  
  public AnswerDetails(String content, String qContent)
  {
	this.content=content;
	this.qContent=qContent;
  }

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public String getqContent() {
	return qContent;
}

public void setqContent(String qContent) {
	this.qContent = qContent;
}
  
  
  
}
