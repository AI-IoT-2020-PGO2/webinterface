package build.webinterface.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DJController {

    @RequestMapping(value={"/","/home"})
    public String addSongs(final ModelMap model) {
        List<String> songs = new ArrayList<>();

        songs.add("SongA");
        songs.add("SongB");
        songs.add("SongC");
        model.addAttribute("songs", songs);
        //model.addAttribute("selectedSong");
        //System.out.println(model.getAttribute("selectedSong"));

        return "DJInterface";
    }

    @RequestMapping(value="/home/submit")
    public String submit(@ModelAttribute("selectedSong") String selectedSong, final ModelMap model){
        System.out.println("submitted");
        System.out.println(model.getAttribute("selectedSong"));
        System.out.println("____________");
        return "redirect:/home";
    }
}
