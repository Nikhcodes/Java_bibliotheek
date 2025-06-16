# 📚 BiblioApp – Java Console Bibliotheek

![Java](https://img.shields.io/badge/Java-17-red?logo=java)
![MySQL](https://img.shields.io/badge/MySQL-HeidiSQL-blue)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-Unasat-darkorange)


**BiblioApp** is een console-gebaseerde bibliotheekapplicatie geschreven in **Java**, met **MySQL** als databasesysteem. De applicatie ondersteunt boekuitleningen, retourverwerking, rapportages en het beheren van leden- en boekgegevens.

---

## 👥 Groepsleden

| 👤 Naam                | 🎓 Studentnummer | 🌐 GitHub                            |
|------------------------|------------------|--------------------------------------|
| Alain Pawirodikromo    | SE/1124/044      | [@Zxadge](https://github.com/Zxadge) |
| Alicia Mahes           | BI/1124/015      | [@aliciamaryjo2002](https://github.com/aliciamaryjo2002) |
| Joshua Wirjoinangoen   | SNE/1124/017     | [@xo2005](https://github.com/xo2005) |
| Nikhiel Lingard        | SE/1124/032      | [@Nikhcodes](https://github.com/Nikhcodes) |
| Shamar Lobles          | SE/1124/036      | [@Z-vax](https://github.com/Z-vax) |

---

## 🚀 Functionaliteiten

- 📖 Boeken **uitlenen** en **retourneren**
- 👥 Automatisch **leden toevoegen** bij eerste uitlening
- 📊 **Rapportages** genereren per lid of per jaar
- 📝 **Bewerken** van boek- en lidgegevens
- ✅ **Beschikbaarheid checken** bij uitlening
- 🛡️ **Validatie** van invoer en duidelijke foutmeldingen

---

## ⚙️ Installatie & Setup

### 📁 Project clonen
1. Open IntelliJ IDEA
2. Kies: File > New > Project from Version Control > Git
3. Repo-URL: https://github.com/Nikhcodes/BiblioApp-Java.git
4. Kies opslaglocatie en klik op "Clone"

## 🗄️ Database instellen
1. Open HeidiSQL of een andere MySQL-tool
2. Maak een database aan: java_bibliotheek
3. Importeer het bestand: database.sql
4. Pas aan in: DatabaseConnector.java

// Voorbeeld aanpassing in DatabaseConnector.java
private static final String URL = "jdbc:mysql://localhost:3306/java_bibliotheek";
private static final String USER = "jouw_gebruiker";
private static final String PASSWORD = "jouw_wachtwoord";

## ▶️ Applicatie uitvoeren
1. Open Main.java in IntelliJ
2. Klik op "Run"
3. Gebruik het consolemenu om te navigeren

## 🗃️ Projectstructuur
BiblioApp/
   
├── src/

│   ├── model/         # Java entity classes (Boek, Lid, etc.)

│   ├── dao/           # Data Access Objects

│   ├── service/       # Logica voor uitlenen, rapportage

│   ├── util/          # DB-connectie en hulpmiddelen

│   └── Main.java      # Startpunt van de applicatie

├── database.sql       # Database structuur + voorbeelddata

├── README.md          # Documentatie (dit bestand)

└── .gitignore         # Git ignore file

## 🔁 Git Workflow
🧰 Eerste keer opzetten
- Clone de repository
- Accepteer de uitnodiging als collaborator

## 📅 Dagelijks gebruik
- git pull origin main         # Haal de laatste updates op
<voeg je wijzigingen toe>

- git add .                    # Voeg alle bestanden toe

- git commit -m "Commit message"

- git push origin main         # Push je code naar GitHub
