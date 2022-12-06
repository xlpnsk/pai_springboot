/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pollub.pai_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pollub.pai_springboot.entities.Zadanie;
import pollub.pai_springboot.repositories.ZadanieRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author adaml
 */
@Controller
public class PageController {

    @Autowired
    public ZadanieRepository rep;

    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie();
        //korzystając z obiektu repozytorium zapisujemy zadanie do bazy
        rep.save(zadanie);
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/addMore")
    @ResponseBody
    public String addMore() {
        StringBuilder odp = new StringBuilder();
        Zadanie z;
        double k = 1000;
        boolean wyk = false;
        for (int i = 1; i <= 10; i++) {
            z = new Zadanie();
            z.setNazwa("zadanie " + i);
            z.setOpis("Opis czynnosci do wykonania w zadaniu " + i);
            z.setKoszt(k);
            z.setWykonane(wyk);
            wyk = !wyk;
            k += 200.50;
            rep.save(z);

        }
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") long id) {
        StringBuilder odp = new StringBuilder();
        //korzystając z obiektu repozytorium zapisujemy zadanie do bazy
        rep.deleteById(id);
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping(value = "/zadania", params = "wykonane", method = RequestMethod.GET)
    @ResponseBody
    public String listaZadanWykonane(@RequestParam("wykonane") boolean wykonane) {
        StringBuilder odp = new StringBuilder();

        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findByWykonane(wykonane)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/zadania/{min}/{max}")
    @ResponseBody
    public String listaZadanKosztPomiedzy(@PathVariable("min") double min, @PathVariable("max") double max) {
        StringBuilder odp = new StringBuilder();
        odp.append("<h1>Pomiędzy ").append(min).append(" i ").append(max).append("</h1><br>");
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findByKosztBetween(min, max)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/zadania/{max}")
    @ResponseBody
    public String listaZadanKosztPMniejszyNiz(@PathVariable("max") double max) {
        StringBuilder odp = new StringBuilder();
        odp.append("<h1>Mniejsze niz ").append(max).append("</h1><br>");
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findByKosztLessThan(max)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }


}
