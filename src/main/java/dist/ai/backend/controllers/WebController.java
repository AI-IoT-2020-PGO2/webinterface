package dist.ai.backend.controllers;

import dist.ai.backend.database.VoteContext;
import dist.ai.backend.models.Song;
import dist.ai.backend.database.DataContext;
import dist.ai.backend.models.Vote;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import static java.util.Comparator.naturalOrder;

/**
 * @author Thomas Somers
 * @version 1.0 1/10/2020 15:18
 */

@Controller
public class WebController {

    @Autowired
    private DataContext dataService;

    //Initialiseren databank repositories
    @Autowired
    private DataContext data;

    private List<Song> songs;
    private Song currentPlaying;


    @Autowired
    public WebController() {
        currentPlaying = null;
        songs = new ArrayList<>();
    }


    @GetMapping("/get/songs")
    public ResponseEntity<List<Song>> getSongs() {
        this.songs = data.getSongs();
        return new ResponseEntity<>(this.songs, HttpStatus.OK);
    }



    @GetMapping("/get/current")
    public ResponseEntity<Song> getCurrent() {
        this.currentPlaying = data.getCurrentlyPlaying();
        return new ResponseEntity<>(this.currentPlaying, HttpStatus.OK);
    }

    @PutMapping("/set/current/{songID}")
    public ResponseEntity<Song> setCurrentPlaying(@RequestBody int songID) {
        //TODO: vragen hoe PutMapping en RequestBody werkt

        currentPlaying = data.getSong(songID);

        return new ResponseEntity<>(this.currentPlaying, HttpStatus.OK);
    }

    @RequestMapping(value={"/","/home"})
    public String showInfo(final ModelMap model) {

        List<Song> songs = dataService.getSongs();
        currentPlaying = dataService.getCurrentlyPlaying();
        if(currentPlaying != null && dataService.getNumberOfVotes(currentPlaying) != null) {
                model.addAttribute("votesPro", dataService.getScore(currentPlaying));
                model.addAttribute("votesContra", dataService.getNumberOfVotes(currentPlaying) - dataService.getScore(currentPlaying));
                model.addAttribute("totalVotes", dataService.getNumberOfVotes(currentPlaying));
        }
        if(dataService.getMostActiveUser() != null){
            model.addAttribute("activeUserID", dataService.getMostActiveUser().getId());
            model.addAttribute("activeUserVotes", dataService.getMostActiveUser().getTotal_votes());
        }
        model.addAttribute("songs", songs);
        model.addAttribute("currentSong",currentPlaying);

        return "DJInterface";
    }

    @RequestMapping(value="/home/submit")
    public String submit(@ModelAttribute("selectedSong") Song selectedSong, final ModelMap model){
        dataService.setCurrentSong(selectedSong);
        return "redirect:/home";
    }

    @RequestMapping(value="/home/refreshVotes")
    @Scheduled(cron = "*/10 * * * * * " )
    public String refreshVotes(){
        System.out.println("Refreshed");
        return "redirect:/home";
    }

    @RequestMapping(value={"/manager"})
    public String viewManager(final ModelMap model) {
        Song highScoreSong = new Song();
        List<Song> allSongs = dataService.getAllPlayedSongs();
        List<String> allSongNamesAndScores = new ArrayList<>();

        double highscore = 0;
        for(Song s : allSongs){
            if(highscore < dataService.getApprovalRate(s)){
                   highScoreSong = s;
                   highscore = dataService.getApprovalRate(s);
            }

            String total = s.getSong_name()+": " + dataService.getScore(s);
            allSongNamesAndScores.add(total);
            allSongNamesAndScores.add("\n");
        }

        double highApproval = dataService.getApprovalRate(highScoreSong);
        String highScore = highScoreSong.getSong_name();
        model.addAttribute("highScoreSong", highScore);
        model.addAttribute("highApproval",highApproval);
        model.addAttribute("allSongNamesAndScores",allSongNamesAndScores);
        return "ManagerInterface";
    }
}