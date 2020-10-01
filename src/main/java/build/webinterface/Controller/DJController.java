package build.webinterface.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DJController {

    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
    public String addSongs(final ModelMap model) {
        List<String> songs = new ArrayList<String>();

        String a="No song";

        songs.add("SongA");
        songs.add("SongB");
        songs.add("SongC");
        model.addAttribute("songs", songs);
        model.addAttribute("selectedSong", a);

        return "DJInterface";
    }

    @RequestMapping(value="/home/submit")
    public String submit(final ModelMap model, String selectedSong){
        String test = (String) model.getAttribute(selectedSong);
        System.out.println(test);
        return "redirect:/home";
    }


}
