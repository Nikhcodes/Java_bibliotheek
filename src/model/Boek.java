package model;

public class Boek {
        private String isbn;
        private String titel;
        private String auteur;
        private String editie;
        private boolean beschikbaar;

        public Boek(String isbn, String titel, String auteur, String editie, boolean beschikbaar) {
                this.isbn = isbn;
                this.titel = titel;
                this.auteur = auteur;
                this.beschikbaar = beschikbaar;
        }

        public Boek(){}

        public String getIsbn() {return isbn;}
        public void setIsbn(String isbn) {this.isbn = isbn;}

        public String getTitel() {return isbn; }
        public void setTitel(String titel) {this.titel = titel;}

        public String getAuteur() {return auteur;}
        public void setAuteur(String auteur) {this.auteur = auteur;}

        public String getEditie() {return editie; }
        public void setEditie(String editie) {this.editie = editie;}

        public boolean isBeschikbaar() {return beschikbaar;}
        public void setBeschikbaar(boolean beschikbaar) {this.beschikbaar = beschikbaar;}

        @Override
        public String toString(){
                return isbn + " - "+ titel + " - "+ auteur + " (" + editie + ")";
        }
}
