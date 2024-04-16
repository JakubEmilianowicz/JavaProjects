package Pracownicy;

import Dyrektor.*;
import Handlowiec.*;

import java.util.List;
import java.util.Scanner;

public class PracownikView {
    private DyrektorView dyrektorView;
    private HandlowiecView handlowiecView;

    public void printPracownicy(List<Pracownik> pracownicy){
        Scanner input = new Scanner(System.in);
        String  choice = null;

        for (int i =0; i < pracownicy.size(); i++){

            if(i< 0) {
                return;
            }
            if(pracownicy.get(i) instanceof  Dyrektor) {
                DyrektorView.printDyrektor(pracownicy.get(i).getImie(), pracownicy.get(i).getNazwisko(), pracownicy.get(i).getPesel(), pracownicy.get(i).getWynagrodzenie(), pracownicy.get(i).getTelefon(), pracownicy.get(i).getLimit(),((Dyrektor) pracownicy.get(i)).getDodatekSluzbowy(), ((Dyrektor) pracownicy.get(i)).getKartaSluzbowa(), pracownicy.get(i).getStanowisko());
            } else if (pracownicy.get(i) instanceof  Handlowiec) {
                HandlowiecView.printHandlowiec(pracownicy.get(i).getImie(), pracownicy.get(i).getNazwisko(), pracownicy.get(i).getPesel(), pracownicy.get(i).getWynagrodzenie(), pracownicy.get(i).getTelefon(), pracownicy.get(i).getLimit(), ((Handlowiec) pracownicy.get(i)).getProwizja(), pracownicy.get(i).getStanowisko());
            }
            System.out.println("\t\t\t [pozycja "+(i+1)+"/"+pracownicy.size()+"]");
            System.out.println("[ Enter] – następny\n" + "[ Q] – powrót");
            input = new Scanner(System.in);
            choice = input.nextLine();
            if (choice.equals("q")) {
               i = i-2;
            }
        }
    }
    public void printPracownik(Pracownik pracownik) {
        if(pracownik instanceof  Dyrektor) {
            DyrektorView.printRemoveDyrektor(pracownik.getImie(), pracownik.getNazwisko(), pracownik.getWynagrodzenie(), pracownik.getTelefon(), pracownik.getLimit(),((Dyrektor) pracownik).getDodatekSluzbowy(), ((Dyrektor) pracownik).getKartaSluzbowa(), pracownik.getStanowisko());
        } else if (pracownik instanceof  Handlowiec) {
            HandlowiecView.printRemoveHandlowiec(pracownik.getImie(), pracownik.getNazwisko(), pracownik.getWynagrodzenie(), pracownik.getTelefon(), pracownik.getLimit(), ((Handlowiec) pracownik).getProwizja(), pracownik.getStanowisko());
        }
    }

}
