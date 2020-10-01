package build.webinterface.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DJController {

    @RequestMapping(value={"/","/home"})
    public String addSongs() {
        /*List<String> songs = new ArrayList<String>();
        songs.add("Song1");
        songs.add("Song2");
        songs.add("Song3");*/

        return "DJInterface";
    }


}
