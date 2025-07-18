package Challend.Forum.hub.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topico")
public class TopicosController {

    @GetMapping
    public String primeiraRequest(){
        String ola = "olá mundo";
        return  ola;
    }

}
