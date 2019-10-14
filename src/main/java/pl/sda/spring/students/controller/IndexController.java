package pl.sda.spring.students.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//zawiera tą adnotację, aby być komponentem
@Controller
//przed wszytskimi urlami musimy dopisac demo
@RequestMapping(path= "/demo/")
public class IndexController {

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String wyswietlMojaStroneHelloWorldTySpringu(){
            return "strona-hello";
    }

    @GetMapping("/hello")
    public String wiswietlHello(){
        return "strona-hello";
    }

//    metoda na przekazanie parametrów
    @GetMapping("/helloTo") //helloTo?imie=Kasia - żeby nie trzeba było podawać parametru "required=false"
    public String wyswietlHelloTo(Model model, @RequestParam(name = "imie", required = false) String parametrImie){

//        model to obiekt odpowiedzialny za przekazanie atrybutów do widoku (klasa Springowa)
        model.addAttribute("atrybutImie", parametrImie);

        return "strona-hello-to";
    }

//      inna metoda na przekazanie parametrów
//    /helloMyBaby/Kasia
    @GetMapping("/helloMyBaby/{babyName}")
    public String wyswietlHelloTo1(Model model, @PathVariable(name="babyName") String variable){
        model.addAttribute("atrybutImie", variable);

        return "strona-hello-to";
    }



}
