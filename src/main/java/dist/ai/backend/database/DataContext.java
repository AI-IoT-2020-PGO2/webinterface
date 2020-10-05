package dist.ai.backend.database;

import dist.ai.backend.models.SongInfo;
import dist.ai.backend.models.*;
import dist.ai.backend.repositories.*;
import dist.ai.backend.services.MQTTService;
import dist.ai.backend.services.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataContext {

    @Autowired
    private AlbumRepository albumRepo;
    @Autowired
    private ArtistRepository artistRepo;
    @Autowired
    private GenreRepository genreRepo;
    @Autowired
    private PlayedSongRepository playedSongRepo;
    @Autowired
    private ProcessedVotesRepository processedVotesRepo;
    @Autowired
    private SongRepository songRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    MQTTService mqttService;
    @Autowired
    TimerService timerService;

    private Song currentlyPlaying = null;

    public List<Song> getSongs() {
        return songRepo.findAll();
    }

    public Map<Song, Integer> getSongsWithScore() {
        List<Song> playedSongs = getAllPlayedSongs();
        HashMap<Song, Integer> songsWithScore = new HashMap<>();
        for (Song playedSong : playedSongs) {
            songsWithScore.put(playedSong, getScore(playedSong));
        }
        return songsWithScore;
    }

    public Song getSong(Integer id) {
        return songRepo.findByIdEquals(id);
    }

    public Integer getScore(Integer songId) {
        return processedVotesRepo.findScoreById(songId);
    }

    public Integer getScore(Song song) {
        return processedVotesRepo.findScoreById(song.getId());
    }

    public Integer getNumberOfVotes(Integer songId) {
        return processedVotesRepo.findVotesById(songId);
    }

    public Integer getNumberOfVotes(Song song) {
        return processedVotesRepo.findVotesById(song.getId());
    }

    public double getApprovalRate(Integer songId) {
        return ((double) getScore(songId)) / ((double) getNumberOfVotes(songId));
    }

    public double getApprovalRate(Song song) {
        return ((double) getScore(song)) / ((double) getNumberOfVotes(song));
    }

    public List<Song> getAllPlayedSongs() {
        List<PlayedSong> playedSongs = playedSongRepo.findAll();
        List<Song> returnList = new ArrayList<>();
        for (PlayedSong ps : playedSongs) {
            returnList.add(getSong(ps.getSong_id()));
        }
        return returnList;
    }

    public void setCurrentSong(Song song) {
        currentlyPlaying = song;
        SongInfo info = new SongInfo(song.getSong_name(), getArtist(song.getArtist_id()).getName(), song.getId());
        mqttService.publishNewSong(info);
        timerService.newSongStarted();
    }

    public Song getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public User getMostActiveUser() {
        return userRepo.findHighestVoter();
    }

    public Artist getArtist(Integer id) {
        return artistRepo.findByIdEquals(id);
    }

    public Album getAlbum(Integer id) {
        return albumRepo.findByIdEquals(id);
    }

    public Genre getGenre(Integer id) {
        return genreRepo.findByIdEquals(id);
    }


}
