package Handlowiec;

import Pracownicy.Pracownik;

import java.math.BigDecimal;

public class Handlowiec extends Pracownik {
    private BigDecimal prowizja;
    public Handlowiec(String imie, String nazwisko, String pesel , int wynagrodzenie, int telefon , int limit, BigDecimal prowizja) {
        super(imie, nazwisko, pesel, wynagrodzenie, telefon, limit, "Handlowiec");
        this.prowizja = prowizja;
    }

    public BigDecimal getProwizja() {
        return prowizja;
    }
}
