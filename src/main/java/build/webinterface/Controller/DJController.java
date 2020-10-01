package build.webinterface.Controller;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DJController {
    public void addSongs() {
        List<String> songs = new ArrayList<String>();
        songs.add("Song1");
        songs.add("Song2");
        songs.add("Song3");
    }
}
