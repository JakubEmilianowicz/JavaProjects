package Dyrektor;
import Pracownicy.*;

import java.math.BigDecimal;

public class Dyrektor extends Pracownik {
    private BigDecimal dodatekSluzbowy;
    private String kartaSluzbowa;

    public Dyrektor(String imie, String nazwisko, String pesel , int wynagrodzenie, int telefon , int limit, BigDecimal dodatek, String karta) {
        super(imie, nazwisko, pesel, wynagrodzenie, telefon, limit, "Dyrektor" );
        this.dodatekSluzbowy = dodatek;
        this.kartaSluzbowa = karta;
    }
    public void setDodatekSluzbowy(BigDecimal dodatekSluzbowy) {
        this.dodatekSluzbowy = dodatekSluzbowy;
    }

    public void setKartaSluzbowa(String  kartaSluzbowa) {
        this.kartaSluzbowa = kartaSluzbowa;
    }

    public BigDecimal getDodatekSluzbowy() {
        return dodatekSluzbowy;
    }

    public String getKartaSluzbowa() {
        return kartaSluzbowa;
    }
}
