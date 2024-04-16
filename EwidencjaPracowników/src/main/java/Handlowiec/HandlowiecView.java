package Handlowiec;

import java.math.BigDecimal;

public class HandlowiecView {
    public static void printHandlowiec(String imie, String nazwisko, String pesel, int wynagrodzenie, int telefon, int limit, BigDecimal Prowizja, String stanowisko) {
        String wyswietl;
        wyswietl = """
                 Identyfikator PESEL : %s
                 Imię : %s
                 Nazwisko : %s  
                 Stanowisko: %s
                 Wynagrodzenie (zł): %d
                 Telefon służbowy numer : %d
                 Prowizja: %f
                 Limit prowizji/miesiąc (zł): %d
                """.formatted(pesel, imie, nazwisko, stanowisko, wynagrodzenie, telefon,Prowizja , limit);
        System.out.println(wyswietl);
    }
    public static void printRemoveHandlowiec(String imie, String nazwisko, int wynagrodzenie, int telefon, int limit, BigDecimal Prowizja, String stanowisko) {
        String wyswietl;
        wyswietl = """
                 ----------------------------
                 Imię : %s
                 Nazwisko : %s  
                 Stanowisko: %s
                 Wynagrodzenie (zł): %d
                 Telefon służbowy numer : %d
                 Prowizja: %f
                 Limit prowizji/miesiąc (zł): %d
                """.formatted( imie, nazwisko, stanowisko, wynagrodzenie, telefon,Prowizja , limit);
        System.out.println(wyswietl);
    }
}
