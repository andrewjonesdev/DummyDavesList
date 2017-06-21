package byaj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by student on 6/20/17.
 */
@Controller
public class HomeController {
    @Autowired
    private RoomsRepository roomsRepository;


    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/")
    public String newRooms(Model model){
        model.addAttribute("rooms", new Rooms());
        return "form";
    }
    @GetMapping("/add")
    public String getRooms(Model model){
        model.addAttribute(new Rooms());
        model.addAttribute("items", roomsRepository.findAll());
        return "result";
    }
    @PostMapping("/add")
    public String processRooms(Model model, @Valid Rooms rooms, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        roomsRepository.save(rooms);
        return "redirect:/add";

        //finishProcessingRooms(rooms, bindingResult);

    }
    /*public String sameClassmate(Model model, Rooms rooms){

        model.addAttribute("classmate", rooms);
        return "form";
    }*/

}
