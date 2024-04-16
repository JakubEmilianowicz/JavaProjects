package Pracownicy;

import java.io.Serializable;

public abstract class Pracownik implements Serializable {
    private  String pesel;
    private String imie;
    private String nazwisko;
    private int telefon;
    private int wynagrodzenie;
    private int limit;
    private  String stanowisko;
    public Pracownik(String imie, String nazwisko, String pesel, int wynagrodzenie, int telefon, int limit, String stanowisko) {
        this.pesel =pesel;
        this.imie = imie;
        this.nazwisko =nazwisko;
        this.wynagrodzenie = wynagrodzenie;
        this.telefon = telefon;
        this.limit = limit;
        this.stanowisko = stanowisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setWynagrodzenie(int wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public int getWynagrodzenie() {
        return wynagrodzenie;
    }

    public int getLimit() {
        return limit;
    }

    public int getTelefon() {
        return telefon;
    }
}
