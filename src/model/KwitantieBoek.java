package model;

public class KwitantieBoek {
    private int kwitantienummer;
    private String boekIsbn;

    public KwitantieBoek() {}

    public KwitantieBoek(int kwitantienummer, String boekIsbn) {
        this.kwitantienummer = kwitantienummer;
        this.boekIsbn = boekIsbn;
    }

    public int getKwitantienummer() { return kwitantienummer; }
    public void setKwitantienummer(int kwitantienummer) { this.kwitantienummer = kwitantienummer; }

    public String getBoekIsbn() { return boekIsbn; }
    public void setBoekIsbn(String boekIsbn) { this.boekIsbn = boekIsbn; }
}
