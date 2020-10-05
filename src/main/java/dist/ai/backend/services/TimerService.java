package dist.ai.backend.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimerService {
    private Timestamp startOfSong;

    public Timestamp getStartOfSong() {
        return startOfSong;
    }

    public TimerService() {
        startOfSong = Timestamp.valueOf(LocalDateTime.now());
    }

    public void newSongStarted() {
        startOfSong = Timestamp.valueOf(LocalDateTime.now());
    }
}

