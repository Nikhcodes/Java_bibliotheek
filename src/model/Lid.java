package model;

import java.time.LocalDate;

public class Lid {
        private int bibnummer;
        private String naam;
        private String cbbId;
        private LocalDate geboortedatum;

        public Lid() {}

        public Lid(int bibnummer, String naam, String cbbId, LocalDate geboortedatum) {
                this.bibnummer = bibnummer;
                this.naam = naam;
                this.cbbId = cbbId;
                this.geboortedatum = geboortedatum;
        }

        public int getBibnummer() { return bibnummer; }
        public void setBibnummer(int bibnummer) { this.bibnummer = bibnummer; }

        public String getNaam() { return naam; }
        public void setNaam(String naam) { this.naam = naam; }

        public String getCbbId() { return cbbId; }
        public void setCbbId(String cbbId) { this.cbbId = cbbId; }

        public LocalDate getGeboortedatum() { return geboortedatum; }
        public void setGeboortedatum(LocalDate geboortedatum) { this.geboortedatum = geboortedatum; }
}
