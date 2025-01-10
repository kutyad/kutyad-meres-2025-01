import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Sportolo osztály
class Sportolo {
    private final int helyezes;
    public double eredmeny;
    private final String nev;
    private final String orszagkod;
    private final String helyszin;
    private final String datum;

    // Konstruktor
    public Sportolo(int helyezes, double eredmeny, String nev, String orszagkod, String helyszin, String datum) {
        this.helyezes = helyezes;
        this.eredmeny = eredmeny;
        this.nev = nev;
        this.orszagkod = orszagkod;
        this.helyszin = helyszin;
        this.datum = datum;
    }

    // Getterek (hozzáférési metódusok)
    public int getHelyezes() {
        return helyezes;
    }

    public double getEredmeny() {
        return eredmeny;
    }

    public String getNev() {
        return nev;
    }

    public String getOrszagkod() {
        return orszagkod;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public String getDatum() {
        return datum;
    }

    // toString metódus az objektum szöveges megjelenítésére
    @Override
    public String toString() {
        return "Helyezés: " + helyezes + ", Eredmény: " + eredmeny + " m, Név: " + nev +
               ", Országkód: " + orszagkod + ", Helyszín: " + helyszin + ", Dátum: " + datum;
    }
}

public class Kalapacsvetes {

    // Beolvassa a fájlt és visszaadja a sportolók listáját
    public static List<Sportolo> beolvasFajlbol(String fajlNev) {
        List<Sportolo> sportolok = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fajlNev))) {
            String sor;
            while ((sor = br.readLine()) != null) {
                if (sor.trim().isEmpty()) continue;  // Üres sorok kihagyása

                // Sor feldolgozása
                String[] adatok = sor.split(";");  // Feltételezve, hogy pontosvesszővel vannak elválasztva az adatok

                // Ellenőrizzük, hogy az adatokat megfelelően sikerült-e felosztani
                if (adatok.length == 6) {
                    try {
                        int helyezes = Integer.parseInt(adatok[0].trim());
                        double eredmeny = Double.parseDouble(adatok[1].trim());
                        String nev = adatok[2].trim();
                        String orszagkod = adatok[3].trim();
                        String helyszin = adatok[4].trim();
                        String datum = adatok[5].trim();

                        // Új Sportolo objektum hozzáadása a listához
                        sportolok.add(new Sportolo(helyezes, eredmeny, nev, orszagkod, helyszin, datum));
                    } catch (NumberFormatException e) {
                        System.out.println();
                    }
                    System.out.println();
                } else {
                    System.out.println("Hiba: Nem megfelelő számú adat található a sorban: " + sor);
                }
                List<String> nevek = new ArrayList<>();
                nevek.add(sor);
                System.out.println(nevek);
                
                
            
            System.out.println("A lista sorainak száma: " + nevek.size());
            }
            
            
        
        } catch (IOException e) {
            System.out.println("Hiba történt a fájl beolvasása során: " + e.getMessage());
        }
        return sportolok;
             
       
        
    
    }

    public static void main(String[] args) {
        System.out.println("Kalapácsvetés eredménykövető alkalmazás");

        String fajlNev = "C:\\Users\\Balázs\\OneDrive\\Dokumentumok\\NetBeansProjects\\Kalapacsvetes\\src\\kalapacsvetes.txt";  
        List<Sportolo> sportolok = beolvasFajlbol(fajlNev);

        // Kiíratjuk az eredményeket
        if (sportolok.isEmpty()) {
            System.out.println();
        } else {
            sportolok.forEach(sportolo -> System.out.println());
        }

        
    }
}
        

