package com.example.aufgaben_docestate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
public class Immobilie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Setter@Getter
    private String bezeichnung,plz,ort,strasse,hausnummer;

    public Immobilie(String bezeichnung, String plz, String ort, String strasse, String hausnummer) {
        this.bezeichnung = bezeichnung;
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    public String getAdresse(){
        return plz+" "+ort+" "+strasse+" "+hausnummer;
    }

}

