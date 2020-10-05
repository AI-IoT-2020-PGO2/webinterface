package dist.ai.backend.database;

import com.mongodb.client.MongoClients;
import dist.ai.backend.models.Vote;
import dist.ai.backend.services.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.sql.Timestamp;

public class VoteContext {
    @Autowired
    TimerService timerService;

    MongoOperations mongoOps;

    public VoteContext() {
        mongoOps = new MongoTemplate(MongoClients.create(), "votes");
    }

    public boolean saveVote(Vote vote) {
        Timestamp timestamp = Timestamp.valueOf(vote.getTimestamp());
        long timeDifference_ms = timerService.getStartOfSong().getTime() - timestamp.getTime();
        int seconds = (int) timeDifference_ms / 1000;
        int minutes =  seconds / 60;
        seconds = seconds % 60;
        vote.setTimestamp(String.format("%d:%d", minutes, seconds));
        mongoOps.insert(vote);
        return true;
    }
}
