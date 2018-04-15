package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.FeedResponse;
import data.Message;
import data.NewsAgentResponse;
import services.FeedService;
import services.NewsAgentService;

import java.util.UUID;

import static data.Message.Sender.BOT;
import static data.Message.Sender.USER;

public class MessageActor extends UntypedActor {
    //Self-Reference the actor
    //PROPS
    //object of FeedService
    //object of NewsAgentService
    private final ActorRef out;
    private FeedService feedService = new FeedService();
    private NewsAgentService newsAgentService = new NewsAgentService();
    private NewsAgentResponse newsAgentResponse = new NewsAgentResponse();
    private FeedResponse feedResponse = new FeedResponse();

    public static Props props(ActorRef out) {
        return Props.create(MessageActor.class, out);
    }

    public MessageActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        Message messageObject = new Message();
        if (message instanceof String) ;
        {
            messageObject.text = (String) message;
            messageObject.sender = USER;
            out.tell(objectMapper.writeValueAsString(messageObject), self());
            newsAgentResponse = newsAgentService.getNewsAgentResponse(messageObject.text, UUID.randomUUID());
            feedResponse = feedService.getFeedByQuery(newsAgentResponse.query);//gets the news
            messageObject.text = (feedResponse.title == null) ? "No results found" : "Showing results for: " + newsAgentResponse.query;
            messageObject.feedResponse = feedResponse;
            messageObject.sender = BOT;
            out.tell(objectMapper.writeValueAsString(messageObject), self());


        }

    }

}