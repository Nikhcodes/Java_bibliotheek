package model;

import java.time.LocalDate;

public class LeenKwitantie {
    private int kwitantienummer;
    private int lidBibnummer;
    private LocalDate leendatum;
    private int duur;
    private LocalDate inleverdatum;
    private String opmerkingen;

    public LeenKwitantie() {}

    public LeenKwitantie(int kwitantienummer, int lidBibnummer, LocalDate leendatum, int duur, LocalDate inleverdatum, String opmerkingen) {
        this.kwitantienummer = kwitantienummer;
        this.lidBibnummer = lidBibnummer;
        this.leendatum = leendatum;
        this.duur = duur;
        this.inleverdatum = inleverdatum;
        this.opmerkingen = opmerkingen;
    }

    public int getKwitantienummer() { return kwitantienummer; }
    public void setKwitantienummer(int kwitantienummer) { this.kwitantienummer = kwitantienummer; }

    public int getLidBibnummer() { return lidBibnummer; }
    public void setLidBibnummer(int lidBibnummer) { this.lidBibnummer = lidBibnummer; }

    public LocalDate getLeendatum() { return leendatum; }
    public void setLeendatum(LocalDate leendatum) { this.leendatum = leendatum; }

    public int getDuur() { return duur; }
    public void setDuur(int duur) { this.duur = duur; }

    public LocalDate getInleverdatum() { return inleverdatum; }
    public void setInleverdatum(LocalDate inleverdatum) { this.inleverdatum = inleverdatum; }

    public String getOpmerkingen() { return opmerkingen; }
    public void setOpmerkingen(String opmerkingen) { this.opmerkingen = opmerkingen; }
}
