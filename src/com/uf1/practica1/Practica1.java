package com.uf1.practica1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// BufferedReader: lee texto de una entrada de caracteres.
// FileReader: para leer archivos.

public class Practica1 {

    public static void main(String[] args) {

        String workingDirectory = System.getProperty("user.dir");
        System.out.println(workingDirectory);

        File f1 = new File("fExists.txt");
        File f2 = new File("fNotExists.txt");

        try {
            // fitxer.createNewFile() sí existeix el fitxer retorna false, també existeix fitxer.exists()
            if (f1.exists() && f2.exists()) {
                doThings(f1);
                doThings(f2);
            } else {
                // createNewFile() sí existeix el sobreescriu i si no existeix el crea
                System.out.println("Ara creem els fitxers que falten");

                f1.createNewFile();
                f2.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public static void doThings(File file) throws IOException {
        System.out.println("Tots els fitxers existeixen");

        FileReader reader = new FileReader(file);
        // Utilitzo la funció que he creat per llegir els fitxers amb el FileReader
        readFileFR(reader);

        reader = new FileReader(file);

        // Amb BufferReader necesitem el FileReader, per exemple en aquest cas r1:
        BufferedReader fBR = new BufferedReader(reader);
        showFile(fBR);
        // Amb Scanner:
        Scanner sc = new Scanner(file);
        readSc(sc);
        // Escriure BufferedWritter:
        FileWriter fw = new FileWriter(file);
        BufferedWriter fBW = new BufferedWriter(fw);
        fBW.write("Afegir text f1");
        fBW.close();
        // sense esborrar el contingut de l'arxiu:
        FileWriter fw3 = new FileWriter(file, true);
        BufferedWriter fBW3 = new BufferedWriter(fw3);
        fBW3.write("Text f1 extra");
        fBW3.close();
    }

    public static void readSc(Scanner sc) {
        while(sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        sc.close();
    }

    public static int readFileFR(FileReader r) throws IOException {
        int valor = r.read();
        while(valor != -1) {
            System.out.print((char)valor);
            valor = r.read();
        }
        r.close();
        return valor;
    }
    public static void showFile(BufferedReader br) throws FileNotFoundException, IOException {
        String str = "";
        while((str = br.readLine()) != null) {
            System.out.println(str);
        }
        br.close();
    }
}
