package data;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Message {
    public String text;
    public enum Sender {USER,BOT};
    public FeedResponse feedResponse;
    public Sender sender;


}
