package com.uf1.practica1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

// Descarregueu el codi anomenat banc_p2uf3.java de moodle. Genereu les funcions adients per
//        tal de desar en un document de text la quantitat de diners disponibles al banc i, un cop fetes les
//        modificacions pertinents, tornar a desar-lo. La puntuació serà la següent:
    //        a. Comprovar si existeix el fitxer d’emmagatzematge dels diners. En cas que no existeixi,
    //        que es generi – 1 punt
    //        b. Obrir i tancar el fitxer correctament – 1 punt
    //        c. Desar el codi en un fitxer (utilitzant la funció write que més us agradi) – 1 punt
    //        d. Llegir el codi del fitxer (utilitzant la funció write que més us agradi) – 1 punt
    //        e. Adaptar el codi en base a les lectures i escriptures del programa, fent una iteració per
    //        escollir quan s’ha de sortir del programa – 1 punt

public class Principal {
    public static void main(String[] args) throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        System.out.println(workingDirectory);

        String strFile = "saveCash.txt";
        createFile(strFile);

        //int i_capital = 10000;
        int capital = lastCash(createFile(strFile)); // capital és l'ultim valor afegit a l'arxiu
        //System.out.println(lastCash(createFile(strFile)));

        String s_opcions = "1-Afegir diners\n2-Treure diners\n3-Sortir del programa";
        System.out.println(s_opcions);
        Scanner sc = new Scanner(System.in);
        int i_opcio_escollida = sc.nextInt();
        int i_quantitat = 0;
        switch (i_opcio_escollida) {
            case 1:
                System.out.println("Introdueix la quantitat a ingressar");
                i_quantitat = sc.nextInt();
                capital += i_quantitat;
                //System.out.println("Saldo disponible: " + i_capital + "€");
                writeCash("" + capital + ";", createFile(strFile));
                System.out.println(capital);
                break;
            case 2:
                System.out.println("Introdueix la quantitat a extreure");
                i_quantitat = sc.nextInt();
                if (i_quantitat > capital) System.out.println("No té suficient capital");
                else {
                    capital -= i_quantitat;
                    //System.out.println("Saldo disponible: " + i_capital + "€");
                    writeCash("" + capital + ";", createFile(strFile));
                    System.out.println(capital);
                }
                break;
            case 3:
                System.out.println("Sortint del programa. Fins aviat!");
                break;
            default:
                System.out.println("Opció incorrecta. Sortint del programa.");
                break;
        }
    }

    public static File createFile(String fileName) throws IOException {
        File f = new File(fileName);

        if (f.exists()) {
            System.out.println("Fitxer " + fileName + " existeix");
        } else {
            System.out.println("Creant fitxer...");
            f.createNewFile();
        }

        return f;
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return sb.toString();
    }

    public static void writeCash(String cash, File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter fBW = new BufferedWriter(fw);
        fBW.write(cash);
        fBW.close();

    }

    public static int lastCash(File file) throws IOException {
        String lin;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        lin = reader.readLine();
        String[] cashs = lin.split(";");

        return Integer.valueOf(cashs[cashs.length -1]); // pasem a int el String per poder operar amb ell
    }
}