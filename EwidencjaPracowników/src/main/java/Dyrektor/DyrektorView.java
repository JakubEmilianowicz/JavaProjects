package Dyrektor;

import java.math.BigDecimal;

public class DyrektorView {
    public static void printDyrektor(String imie, String nazwisko, String pesel, int wynagrodzenie, int telefon, int limit, BigDecimal dodatekSluzbowy, String kartaSluzbowa, String stanowisko) {
        String wyswwietl = """
                 Identyfikator PESEL : %s
                 Imię : %s
                 Nazwisko : %s  
                 Stanowisko: %s
                 Wynagrodzenie (zł): %d
                 Telefon służbowy numer : %d
                 Dodatek służbowy (zł): %f
                 Karta służbowa numer: %s
                 Limit prowizji/miesiąc (zł): %d
                """.formatted(pesel, imie, nazwisko, stanowisko, wynagrodzenie, telefon, dodatekSluzbowy, kartaSluzbowa, limit);
        System.out.println(wyswwietl);
    }

    public static void printRemoveDyrektor(String imie, String nazwisko, int wynagrodzenie, int telefon, int limit, BigDecimal dodatekSluzbowy, String kartaSluzbowa, String stanowisko) {
        String wyswwietl = """
                 ---------------------------
                 Imię : %s
                 Nazwisko : %s  
                 Stanowisko: %s
                 Wynagrodzenie (zł): %d
                 Telefon służbowy numer : %d
                 Dodatek służbowy (zł): %f
                 Karta służbowa numer: %s
                 Limit prowizji/miesiąc (zł): %d
                """.formatted( imie, nazwisko, stanowisko, wynagrodzenie, telefon, dodatekSluzbowy, kartaSluzbowa, limit);
        System.out.println(wyswwietl);
    }
}
