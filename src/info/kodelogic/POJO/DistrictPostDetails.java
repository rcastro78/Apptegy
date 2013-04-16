package info.kodelogic.POJO;

public class DistrictPostDetails {
  private String title, published_text, content, postable_type;

  public DistrictPostDetails(String title, String published_text, String content,
                             String postable_type) {
    this.title = title;
    this.published_text = published_text;
    this.content = content;
    this.postable_type=postable_type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublished_text() {
    return published_text;
  }

  public void setPublished_text(String published_text) {
    this.published_text = published_text;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPostable_type() {
    return postable_type;
  }

  public void setPostable_type(String postable_type) {
    this.postable_type = postable_type;
  }
}