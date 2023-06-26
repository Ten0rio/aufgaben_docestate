package com.example.aufgaben_docestate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/immobilien")
public class ImmobilieController {
    private final ImmobilieRepository immobilieRepository;

    @Autowired
    public ImmobilieController(ImmobilieRepository immobilieRepository) {
        this.immobilieRepository = immobilieRepository;
    }

    @GetMapping
    public String getAllImmobilien(Model model) {
        List<Immobilie> immobilienList = immobilieRepository.findAll();
        model.addAttribute("immobilienList", immobilienList);
        return "immobilien";
    }


    @PostMapping
    public String addImmobilie(@RequestParam("bezeichnung") String bezeichnung,
                               @RequestParam("strasse") String strasse,
                               @RequestParam("hausnummer") String hausnummer,
                               @RequestParam("plz") String plz,
                               @RequestParam("ort") String ort) {
        Immobilie immobilie = new Immobilie(bezeichnung, strasse, hausnummer, plz, ort);
        immobilieRepository.save(immobilie);
        return "redirect:/immobilien";
    }


    @PostMapping("/delete")
    public String deleteImmobilieById(@RequestParam("Id") Long id) {
        immobilieRepository.deleteById(id);
        return "redirect:/immobilien";
    }

    @PostMapping("/bearbeiten")
    public String updateImmobilie(@RequestParam("id") Long id,
                                  @RequestParam("bezeichnung") String bezeichnung,
                                  @RequestParam("strasse") String strasse,
                                  @RequestParam("hausnummer") String hausnummer,
                                  @RequestParam("plz") String plz,
                                  @RequestParam("ort") String ort) {
        Immobilie immobilie = immobilieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ungültige Immobilie-ID: " + id));

        immobilie.setBezeichnung(bezeichnung);
        immobilie.setPlz(plz);
        immobilie.setOrt(ort);
        immobilie.setStrasse(strasse);
        immobilie.setHausnummer(hausnummer);

        immobilieRepository.save(immobilie);

        return "redirect:/immobilien";
    }

}

