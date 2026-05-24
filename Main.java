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