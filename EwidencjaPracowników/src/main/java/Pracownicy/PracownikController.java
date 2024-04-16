package Pracownicy;

import Dyrektor.Dyrektor;
import Handlowiec.Handlowiec;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.*;

public class PracownikController {
    private List<Pracownik> pracownicy = new ArrayList<Pracownik>();
    private PracownikView view;
    public PracownikController(PracownikView view) {
        this.view = view;
    }
    public boolean sprawdzPesel(int [] pesel) {
        return (pesel[0] + 3 * pesel[1] + 7 * pesel[2] + 9 * pesel[3] + pesel[4] + 3 * pesel[5] + 7 * pesel[6] + 9 * pesel[7] + pesel[8] + 3 * pesel[9] + pesel[10]) % 10 == 0;
    }
    public void addPracownik(){
        Scanner input = new Scanner(System.in);

        System.out.print("[D]yrektor/[H]handlowiec: ");
        String stanowisko = input.next();

        if(stanowisko.equalsIgnoreCase("d") || stanowisko.equalsIgnoreCase("h")) {

            System.out.println("---------------------------------");
//Pesel
            System.out.print("Pesel: ");
            String peselS = input.next();
            char[] pes = peselS.toCharArray();
            int[] pesel = new int[pes.length];
            if(peselS.length() < 11) {
                System.out.println("Za krótki pesel!");
                return;
            } else if (peselS.length()>11) {
                System.out.println("Za długi pesel!");
                return;
            }
            for (int i = 0; i < pes.length; i++) {
                    pesel[i] = Character.getNumericValue(pes[i]);
                }

            if (sprawdzPesel(pesel)) {

                //przeszukanie po peselu
                for(Pracownik pracownik : pracownicy){
                    if(pracownik.getPesel().equals(peselS)) {
                        System.out.println("Pracownik o podanym peselu istenieje");
                        return;
                    }
                }
                System.out.print("Imie: ");
                String imie = input.next();

                System.out.print("Nazwisko: ");
                String nazwisko = input.next();

                System.out.print("Wynagrodzenie (zł): ");
                int wynagrodzenie = input.nextInt();

                System.out.print("Telefon: ");
                int telefon = input.nextInt();

                BigDecimal dodatek = null;
                BigDecimal prowizja = null;
                String karta = null;
                if (stanowisko.equalsIgnoreCase("d")) {
                    System.out.print("Dodatek służbowy (zł): ");
                    dodatek = input.nextBigDecimal();

                    System.out.print("Karta służbowa numer: ");
                    karta = input.next();
                } else {
                    System.out.print("Prowizja ( % ): ");
                    prowizja = input.nextBigDecimal();
                }

                System.out.print("Limit: ");
                int limit = input.nextInt();

                System.out.println("---------------------------------");
                System.out.println("[Enter] – zapisz\n" + "[Q] – porzuć");

              //  input = new Scanner(System.in);
                input.nextLine();
                String choice = input.nextLine();
                if (choice.equalsIgnoreCase("q")) {
                    return;
                }

                if (stanowisko.equalsIgnoreCase("d")) {
                    Dyrektor dyrektor = new Dyrektor(imie, nazwisko, peselS, wynagrodzenie, telefon, limit, dodatek, karta);
                    pracownicy.add(dyrektor);
                } else {
                    Handlowiec handlowiec = new Handlowiec(imie, nazwisko, peselS, wynagrodzenie, telefon, limit, prowizja);
                    pracownicy.add(handlowiec);
                }

            } else {
                System.out.println("Zły numer pesel");
            }
        }else {
            System.out.println("Wybrane stanowisko pracownika nie istnieje");
        }
    }
    public void updateView(){
        //view.printPracownik(pracownik.getImie(), pracownik.getNazwisko(), pracownik.getPesel(), pracownik.getWynagrodzenie(), pracownik.getTelefon(), pracownik.getLimit());
        view.printPracownicy(pracownicy);
    }
    public  void usunPracownika(){
        Scanner input = new Scanner(System.in);
        System.out.print("Podaj Identyfikator PESEL: ");
        String pesel = input.nextLine();
        String choice;
        int index  = 0;

        for (int i =0; i<pracownicy.size(); i++) {
            if(pracownicy.get(i).getPesel().equals(pesel)){
                index = i;
                break;
            }
            else{
                System.out.println("Nie znaleziono pracownika!");

            }
        }
        view.printPracownik(pracownicy.get(index));
        System.out.println("------------------------------------------------------------------\n" + "[Enter] – potwierdź\n" + "[Q] – porzuć");

        input.nextLine();
        choice = input.nextLine();
        if (choice.equalsIgnoreCase("q")) {
            return;
        }
        pracownicy.remove(pracownicy.get(index));
        System.out.println("Usunieto pracownika o peselu: "+ pesel);
    }
    public boolean kopiaZapasowa(){
        Scanner input = new Scanner(System.in);
       // Scanner fileInput  = new Scanner(System.in);
        System.out.print("[Z]achowaj/[O]dtwórz: ");
        String choice = input.next();
        String zipChoice;
        String filename;

        if (choice.equalsIgnoreCase("z")){
            System.out.print("--------------------\nKompresja [G]zip/[Z]ip: ");
            zipChoice = input.next();

            System.out.print("Nazwa pliku: ");
            input.nextLine();
            filename = input.nextLine();

            System.out.println("---------------------------------");
            System.out.println("[Enter] – zapisz\n" + "[Q] – porzuć");
            input.nextLine();
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("q")) {
                return false;
            }
            if (zipChoice.equalsIgnoreCase("z")) {
                try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(filename+".zip"))) {
                    ZipEntry zipIn = new ZipEntry(filename);
                    zipOut.putNextEntry(zipIn);

                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(zipOut)) {
                        objectOutputStream.writeObject(pracownicy);
                    }

                    zipOut.closeEntry();
                }
                catch (IOException e){
                    //e.printStackTrace();
                }
            }else {
                try (ObjectOutputStream outputStream = new ObjectOutputStream(
                        new GZIPOutputStream(new FileOutputStream(filename+".gz")))) {
                    // Zapis listy pracowników do pliku
                    outputStream.writeObject(pracownicy);
                    System.out.println("Dane zostały zapisane do pliku: " + filename + ".gz");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (choice.equalsIgnoreCase("o")) {
            System.out.print("--------------------\nNazwa Pliku: ");
            input=new Scanner(System.in);
            filename = input.nextLine();

            System.out.print("[G]zip/[Z]ip: ");
            String compress = input.next();

            System.out.println("---------------------------------");
            System.out.println("[Enter] – potwierdź \n" + "[Q] – porzuć");
            input.nextLine();
            String c = input.nextLine();

            if(c.equalsIgnoreCase("q")){
                return false;
            }

            if(compress.equalsIgnoreCase("z")) {
                try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(filename + ".zip"))) {
                    ZipEntry zipEntry = zipIn.getNextEntry();

                    if (zipEntry != null) {
                        if (zipEntry.getName().equals(filename)) {
                            try(ObjectInputStream objectInputStream = new ObjectInputStream(zipIn)){
                                pracownicy = (ArrayList<Pracownik>) objectInputStream.readObject();
                            }catch (ClassNotFoundException | IOException e) {
                                System.out.println("Błąd podczas deserializacji danych");
                            }
                        }
                    } else {
                        System.out.println("Brak pliku w archiwum!");
                        return false;
                    }
                } catch (IOException e) {
                    System.out.println("Błąd przy deserializacji danych");
                }
            } else if (compress.equalsIgnoreCase("g")) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream(filename+".gz")))) {
                    // Odczytaj dane z pliku i zastąp bieżące dane
                    pracownicy = (ArrayList<Pracownik>) objectInputStream.readObject();

                    System.out.println("Dane zostały wczytane z pliku: " + filename);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    System.out.println("Błąd podczas deserializacji danych");
                }
            }
            else {
                System.out.println("Wyvrana nie właściwa opcja");
                return false;
            }
        }
        else {
            System.out.println("Zła opcja!");
            return false;
        }
        return true;
    }
    public  List<Pracownik> getPracownicy(){
        return pracownicy;
    }
}
