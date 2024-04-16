
import  Pracownicy.*;
import  Dyrektor.*;
import  Handlowiec.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PracownikView pracownikView = new PracownikView();
        PracownikController controller = new PracownikController(pracownikView);
        Scanner input = new Scanner(System.in);
        int choice, choice2;
    do {
    String menu = """
              MENU
              1.Lista pracowników
              2.Dodaj pracownika
              3.Usuń pracownika
              4. Kopia zapasowa
              5. Wyjscie
              Wybór>
            """;
    System.out.print(menu);
    choice = input.nextInt();
    switch (choice) {
        case 1:
            controller.updateView();
            break;
        case 2:
            controller.addPracownik();
            break;
        case 3:
            controller.usunPracownika();
            break;
        case 4:
            controller.kopiaZapasowa();
            break;
        case 5:
            break;
        default:
            System.out.println("Zła opcja");
            break;
    }

}while (choice!= 5);
    }
}
