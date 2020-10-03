package build.webinterface.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DJController {

    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
    public String addSongs(final ModelMap model) {
        List<String> songs = new ArrayList<>();

        String selectedSong="No song";

        songs.add("SongA");
        songs.add("SongB");
        songs.add("SongC");
        model.addAttribute("songs", songs);
        model.addAttribute("selectedSong", selectedSong);

        return "DJInterface";
    }

    @RequestMapping(value="/home/submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute("selectedSong") String selectedSong){
        System.out.println(selectedSong);
        return "redirect:/home";
    }
}
