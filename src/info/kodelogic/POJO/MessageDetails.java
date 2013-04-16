package info.kodelogic.POJO;

public class MessageDetails {
  private String id,topic,body,message_date,time_ago,recipient,sender;

  public MessageDetails(String id,String topic, String body, String message_date, 
		  String time_ago, String recipient, String sender)
  {
	 this.topic=topic;
	 this.body=body;
	 this.message_date=message_date;
	 this.time_ago=time_ago;
	 this.recipient=recipient;
	 this.sender=sender;
	 this.id=id;
  }

public MessageDetails(String from, String to, String date, String body)
{
  this.sender=from;
  this.recipient=to;
  this.message_date=date;
  this.body=body;
}
  
public String getTopic() {
	return topic;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public void setTopic(String topic) {
	this.topic = topic;
}

public String getBody() {
	return body;
}

public void setBody(String body) {
	this.body = body;
}

public String getMessage_date() {
	return message_date;
}

public void setMessage_date(String message_date) {
	this.message_date = message_date;
}

public String getTime_ago() {
	return time_ago;
}

public void setTime_ago(String time_ago) {
	this.time_ago = time_ago;
}

public String getRecipient() {
	return recipient;
}

public void setRecipient(String recipient) {
	this.recipient = recipient;
}

public String getSender() {
	return sender;
}

public void setSender(String sender) {
	this.sender = sender;
}

  
  
  
}
