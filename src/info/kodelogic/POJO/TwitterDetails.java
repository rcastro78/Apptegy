package info.kodelogic.POJO;

public class TwitterDetails {
  private String tweet;

  public TwitterDetails(String tweet) {
    this.tweet = tweet;
  }

  public String getTweet() { return tweet; }

  public void setTweet(String tweet) { this.tweet = tweet; }
}