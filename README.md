# Java_kigyok
```
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public class Kigyo {
        public String fajta;
        public int hossz;
        public String elofordulas;
        public String merges;

        public Kigyo(String sor) {
            String[] s = sor.split(";");
            fajta = s[0];
            hossz = Integer.parseInt(s[1]);
            elofordulas = s[2];
            merges = s[3];
        }
    }

    private ArrayList<Kigyo> kigyok = new ArrayList<>();

    public Main() {
        // --- 0. feladat ---
        betolt("kigyok.csv");
        System.out.printf("0) Összesen %d kígyó adata beolvasva\n", kigyok.size());

        int mergesDb = 0;
        for (Kigyo k : kigyok) {
            if (k.merges.equals("Igen")) {
                mergesDb++;
            }
        }
        System.out.printf("   Közülük %d mérges és %d nem mérges\n", mergesDb, kigyok.size()-mergesDb);

        // --- 1. feladat ---
        float teljeshossz = 0;
        for (Kigyo k : kigyok) {
            if (k.hossz > 0) {
                teljeshossz += k.hossz;
            }
        }
        System.out.printf("1) A kígyók teljes hossza méterben: %.2fm\n", teljeshossz/100);

        // --- 2. feladat ---
        Kigyo leghosszabb = kigyok.get(0);
        for (Kigyo k : kigyok) {
            if (k.hossz > leghosszabb.hossz && k.merges.equals("Igen")) {
                leghosszabb = k;
            }
        }
        System.out.printf("2) A leghosszabb mérges kígyó: %s (%dcm)\n", leghosszabb.fajta, leghosszabb.hossz);

        // --- 3. feladat ---
        TreeSet<String> elofordulasok = new TreeSet<>();
        for (Kigyo k : kigyok) {
            if (!elofordulasok.contains(k.elofordulas)) {
                elofordulasok.add(k.elofordulas);
            }
        }
        System.out.printf("3) A kígyók származási helye (abc): %s\n", String.join(", ", elofordulasok));

        // --- 4. feladat ---
        ArrayList<Kigyo> mergesek = new ArrayList<>();
        for (Kigyo k : kigyok) {
            if (k.merges.equals("Igen")) {
                mergesek.add(k);
            }
        }

        // --- 5. feladat ---

        // --- 6. feladat ---
        String utolsoMamba = "";
        for (Kigyo k : kigyok) {
            if (k.fajta.contains("Mamba")) {
                utolsoMamba = k.fajta;
            }
        }
        System.out.printf("6) Az utolsó Mamba fajtája: %s\n", utolsoMamba);

        // --- 7. feladat ---
        PrintWriter ki = null;
        try {
            ki = new PrintWriter(new File("kobra.txt"), "utf-8");
            for (Kigyo k : kigyok) {
                if (k.fajta.contains("Kobra")) {
                    ki.printf("%s (%dcm)\r\n", k.fajta, k.hossz);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ki != null) ki.close();
        }
        System.out.print("7) Minden Kobra mentve a kobra.txt fájlba");
    }

    private void betolt(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) {
                kigyok.add(new Kigyo(be.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (be != null) be.close();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
```
### kigyok.csv
```
Fajta;Hossz(cm);Előfordulás;Mérges
Kobra;300;Ázsia;Igen
Mamba;500;Afrika;Igen
Piton;400;Ázsia;Nem
Boa;400;Amerika;Nem
Vipera;150;Ázsia;Igen
...
```
## Feladat
```
 A kigyok.csv fájl különböző kígyók adatait tartalmazza,
 pontosvesszővel elválasztva, utf-8 kódolással. VIGYÁZAT, az első sor fejléc!
 Hozzunk létre egy Kigyok nevű projektet és oldjuk meg a következő feladatokat!

 0) Olvassuk be a fájl adatait egy megfelelő adatszerkezetbe,
    és jelenítsük meg a beolvasott adatok számát!.....................(2p)
    Írjuk ki hány mérges és nem mérges kígyó adatát olvastuk be!......(1p)
 1) Határozzuk meg a kígyók teljes hosszát méterben (2 tizedes)!......(1p)
 2) Írjuk ki a leghosszabb mérges(!) kígyó hosszát!...................(2p)
 3) Jelenítsük meg mely földrészekről (abc) származnak a kígyók!......(1p)
    A földrészeket vesszővel elválasztva írjuk ki!....................(1p)
 4) Válasszunk ki véletlenszerűen egy mérges(!) kígyót,...............(1p)
    és írjuk ki a származási helyét és hosszát is!....................(1p)
 5) A fajta utolsó szava (pl. Zöld Mamba -> Mamba) a faj neve!
    Írjuk ki melyikből fajból hány féle van a fájlban!................(2p)
 6) Írjuk ki melyik az utolsó Mamba fajta!............................(1p)
 7) Írjuk ki a kobra.txt fájlba a mintának megfelelően
    a Kobrák adatait!.................................................(2p)

 Minta:
 0) Összesen 23 kígyó adata beolvasva
    Közülük 12 mérges és 11 nem mérges
 1) A kígyók teljes hossza méterben: 68,10m
 2) A leghosszabb mérges kígyó: King Kobra (550cm)
 3) A kígyók származási helye (abc): Afrika, Amerika, Ázsia
 4) Egy véletlen kiválasztott mérges kígyó: Indiai Kobra
    Származási helye Ázsia, hossza 280cm
 5) Adott fajhoz (abc) tartozó kígyók darabszáma:
    Boa : 5 féle
    Kobra : 6 féle
    Kígyó : 4 féle
    Mamba : 3 féle
    Piton : 3 féle
    Vipera : 2 féle
 6) Az utolsó Mamba fajtája: Fekete Mamba
 7) Minden Kobra mentve a kobra.txt fájlba

 kobra.txt:
 Kobra (300cm)
 King Kobra (550cm)
 Harlequin Kobra (120cm)
 Indiai Kobra (280cm)
 Vörös Ázsiai Kobra (330cm)
 Arany Kobra (350cm)
```
