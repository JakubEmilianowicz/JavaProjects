/**
 * Dzień dobry, przesyłam plik po termiinie ze względu na moją nieobcenośc spodowodaną wizytą kontrolną u lekarza,
 * Zwolnienie pokazałem na zajęciach 17.01
 *
 * pozdrawiam,
 * Jakub Emilianowicz
 */

import Pracownicy.PracownikController;
import Pracownicy.PracownikView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

import Pracownicy.Pracownik;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

//Testy od 1 do 12
public class PracownikControllerTest {
    private PracownikController controller;
    private  PracownikView view;

//Test 1 : dodaj handlarza do pustego kontenera
    @Before
    public void setupHandlowiec(){

        view = new PracownikView();
        controller = new PracownikController(view);

    }

    @Test
    public void addHandlowiecTest() {
        String input = "h 02272902451 Jan Kowalski 5000 123456789 15 8 \n\n";

        // symuluj wejscie
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        controller.addPracownik();
        String stanowisko = controller.getPracownicy().get(0).getStanowisko();
        assertEquals("Handlowiec", stanowisko);

    }

    @After
    public void restoreHandlowiec() {
        controller.getPracownicy().clear();
    }

//Test 2 : dodaj dyrektora do pustego kontenera
    @Before
    public void setupDyrektorTest(){

        view = new PracownikView();
        controller = new PracownikController(view);

    }

    @Test
    public void addDyrektor() {
        String input = "d 02272902451 Jan Brzezinski 50000 768543333 21313 1212 1321 \n\n";

        // symulacja wejscia
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        controller.addPracownik();
        String stanowisko = controller.getPracownicy().get(0).getStanowisko();
        assertEquals("Dyrektor", stanowisko);

    }

    @After
    public void restoreDyrektor() {
        controller.getPracownicy().clear();
    }
//Test 3:  dodanie handlowca do kontenera z jednym pracownikiem
    @Before
    public void setupHandlowiec1(){

        view = new PracownikView();
        controller = new PracownikController(view);

    }

    @Test
    public void addHandlowiec1Test() {
        String input = "h 02272902451 Krzysztof Kowalski 5000 123456789 15 8 \n\n";

        // symulacja wejscia
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // sprawdzenie czy w konterze jest jeden pracowniik
        controller.addPracownik();
        assertEquals(1, controller.getPracownicy().size());

        //stworzenie nowego mocka dla nowego handlowca
         input = "h 82120928589 Jan Nowak 5000 998877665 15 8 \n\n";
         inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

         //dodanie nowego pracownika
         controller.addPracownik();
         assertEquals("Handlowiec", controller.getPracownicy().get(1).getStanowisko());


    }

    @After
    public void restoreHandlowiec1() {
        controller.getPracownicy().clear();
    }

//Test 4:  dodanie Dyrektora do kontenera z jednym pracownikiem
    @Before
    public void setupDyrektor1(){

        view = new PracownikView();
        controller = new PracownikController(view);

    }

    @Test
    public void addDyrektor1Test() {
        String input = "h 02272902451 Krzysztof Kowalski 5000 123456789 15 8 \n\n";

        // symulacja wejscia
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // sprawdzenie czy w konterze jest jeden pracowniik
        controller.addPracownik();
        assertEquals(1, controller.getPracownicy().size());

        //stworzenie nowego mocka dla nowego dyrektora
        input = "d 71090978834 Jan Brzezinski 50000 768543333 21313 1212 1321 \n\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        //dodanie nowego pracownika
        controller.addPracownik();
        assertEquals("Dyrektor", controller.getPracownicy().get(1).getStanowisko());


    }

    @After
    public void restoreDyrektor1() {
        controller.getPracownicy().clear();
    }

//Test 5: Dodanie do pustego kontenera 10 losowych pracowików
    @Before
    public void setupPracownicy10(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }

    @Test
    public void addPracownik10Test() {
        //tablica przehcowujaca  10 peseli
        List<String> pesele = new ArrayList<>(Arrays.asList(
                "02272902451",
                "81121912735",
                "82120928589",
                "56020981731",
                "97021477335",
                "88091396642",
                "75122955462",
                "71051976752",
                "59103087597",
                "00291393685"));
        String input = null;
        Random random = new Random();
        int randomIndex, randomStanowisko, zakres = 10;

        for (int i =0; i < zakres; i ++){
            randomStanowisko = random.nextInt(2);
            //handlowiec
            if(randomStanowisko == 0) {
                randomIndex = random.nextInt(pesele.size());
                input = "h "+ pesele.get(randomIndex) + " Jan Nowak 5000 998877665 15 8 \n\n";
                pesele.remove(randomIndex);

            }
            //dyrektor
            else if (randomStanowisko == 1) {
                randomIndex = random.nextInt(pesele.size());
                input = "d " + pesele.get(randomIndex) +" Jan Brzezinski 50000 768543333 21313 1212 1321 \n\n";
                pesele.remove(randomIndex);
            }else{
                System.out.println("Cos poszlo nie tak");
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);
            controller.addPracownik();

        }
        //Warunek 10 obiektow
        assertEquals(zakres, controller.getPracownicy().size());
        // sprawdzenie czy kazdy z obiektów jest albo pracownikiem albo dyrektorem
        for (Pracownik employ:controller.getPracownicy()) {
            assertTrue("Handlowiec".equals(employ.getStanowisko()) || "Dyrektor".equals(employ.getStanowisko()));
        }
    }

    @After
    public void restoreDyrektor10() {
        controller.getPracownicy().clear();
    }

    //Test 6: usuniecie pracownika typu Handlowiec z kontenera zawierajecego pracownikow
    @Before
    public void setupRemoveHandlowiec(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }

    @Test
    public void removeHandlowiecTest(){
        //tablica przehcowujaca  10 peseli
        List<String> pesele = new ArrayList<>(Arrays.asList(
                "02272902451",
                "81121912735",
                "82120928589",
                "56020981731",
                "97021477335",
                "88091396642",
                "75122955462",
                "71051976752",
                "59103087597",
                "00291393685"));
        String input = null;
        Random random = new Random();
        int randomIndex, randomStanowisko, zakres = 10;

        //dodanie kilku pracownikow (Wziete z poprzedniego testu)
        for (int i =0; i < zakres; i ++){
            randomStanowisko = random.nextInt(2);
            //handlowiec
            if(randomStanowisko == 0) {
                randomIndex = random.nextInt(pesele.size());
                input = "h "+ pesele.get(randomIndex) + " Jan Nowak 5000 998877665 15 8 \n\n";
                pesele.remove(randomIndex);

            }
            //dyrektor
            else if (randomStanowisko == 1) {
                randomIndex = random.nextInt(pesele.size());
                input = "d " + pesele.get(randomIndex) +" Jan Brzezinski 50000 768543333 21313 1212 1321 \n\n";
                pesele.remove(randomIndex);
            }else{
                System.out.println("Cos poszlo nie tak");
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);
            controller.addPracownik();
        }
        //szukanie pierwszego napotkanego handlarza
        String pesel = null;
        int index = 0, size = controller.getPracownicy().size();

        for (int i =0; i < controller.getPracownicy().size(); i ++) {
           if(controller.getPracownicy().get(i).getStanowisko().equals("Handlowiec")) {
               pesel = controller.getPracownicy().get(i).getPesel();
               index = i;
               break;
           }
        }
        if(pesel != null){
            input = pesel +" \n\n\n";
            //sprawdzenie czy pracownik o tym peselu (index wyciganiety), jest klasy handlarz
            assertEquals("Handlowiec", controller.getPracownicy().get(index).getStanowisko());

            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);
            controller.usunPracownika();

            //sprawdzenie czy licba elementow w tablciy sie zmniejszyla o 1
            assertEquals(size-1, controller.getPracownicy().size());

        }
    }
    @After
    public void restoreRemoveHandlowiec() {
        controller.getPracownicy().clear();
    }


    //Test 6: usuniecie pracownika typu Dyrektor z kontenera zawierajecego pracownikow
    @Before
    public void setupRemoveDyrektor(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }

    @Test
    public void removeDyrektorTest(){
        //tablica przehcowujaca  10 peseli
        List<String> pesele = new ArrayList<>(Arrays.asList(
                "02272902451",
                "81121912735",
                "82120928589",
                "56020981731",
                "97021477335",
                "88091396642",
                "75122955462",
                "71051976752",
                "59103087597",
                "00291393685"));
        String input = null;
        Random random = new Random();
        int randomIndex, randomStanowisko, zakres = 10;

        //dodanie kilku pracownikow (Wziete z poprzedniego testu)
        for (int i =0; i < zakres; i ++){
            randomStanowisko = random.nextInt(2);
            //handlowiec
            if(randomStanowisko == 0) {
                randomIndex = random.nextInt(pesele.size());
                input = "h "+ pesele.get(randomIndex) + " Jan Nowak 5000 998877665 15 8 \n\n";
                pesele.remove(randomIndex);

            }
            //dyrektor
            else if (randomStanowisko == 1) {
                randomIndex = random.nextInt(pesele.size());
                input = "d " + pesele.get(randomIndex) +" Jan Brzezinski 50000 768543333 21313 1212 1321 \n\n";
                pesele.remove(randomIndex);
            }else{
                System.out.println("Cos poszlo nie tak");
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);
            controller.addPracownik();
        }

        //szukanie pierwszego napotkanego dyrektora
        String pesel = null;
        int index = 0, size = controller.getPracownicy().size();

        for (int i =0; i < controller.getPracownicy().size(); i ++) {
            if(controller.getPracownicy().get(i).getStanowisko().equals("Dyrektor")) {
                pesel = controller.getPracownicy().get(i).getPesel();
                index = i;
                break;
            }
        }
        if(pesel != null){
            input = pesel +" \n\n\n";
            //sprawdzenie czy pracownik o tym peselu (index wyciganiety), jest klasy dyrektor
            assertEquals("Dyrektor", controller.getPracownicy().get(index).getStanowisko());

            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);
            controller.usunPracownika();

            //sprawdzenie czy licba elementow w tablciy sie zmniejszyla o 1
            assertEquals(size-1, controller.getPracownicy().size());

        }
    }
    @After
    public void restoreRemoveDyrektor() {
        controller.getPracownicy().clear();
    }

    //Test 8: test sparametryzowany na poprawnosc numerow pesel
    @BeforeEach
    public void setupPesel(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }
    //a)
    @ParameterizedTest
    @ValueSource(strings = {"02272902451", "81121912735", "82120928589", "56020981731", "97021477335",
            "88091396642", "75122955462", "71051976752", "59103087597", "00291393685"})
    void correctPeselChecksumTest(String pesel) {
        assertTrue(pesel.length() == 11);
        int[] peselInt = new int[pesel.length()];
        for (int i = 0; i < pesel.length(); i++) {
            peselInt[i] = Character.getNumericValue(pesel.charAt(i));
        }
        boolean correct = controller.sprawdzPesel(peselInt);
        assertTrue(correct);
    }
    @AfterEach
    public void restorePesel() {
        controller.getPracownicy().clear();
    }

    //b)
    @BeforeEach
    public void setupPeselIncorrect(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }
    @ParameterizedTest
    @ValueSource(strings = {"09876543211", "98765432100", "11111111111", "00000000100", "99999999999",
            "12345678902", "987654321000", "abcdefghijk", "1234567890a", "11112221113"})
    void incorrectPeselChecksumTest(String pesel) {
        int[] peselInt = new int[pesel.length()];
        for (int i = 0; i < pesel.length(); i++) {
            peselInt[i] = Character.getNumericValue(pesel.charAt(i));
        }
        boolean correct = controller.sprawdzPesel(peselInt);
        assertFalse(correct);
    }

    @AfterEach
    public void restorePeselIncorect() {
        controller.getPracownicy().clear();
    }


    //Test 9 poprawna serializacja
    @Before
    public void setupSerializacja(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }

    @Test
    public void serializacjaTest(){
        //dodaj pracownika
        String input = "h 02272902451 Jan Kowalski 5000 123456789 15 8 \n\n";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        controller.addPracownik();
        //sprawdz czy jest w konterze
        assertEquals(1,controller.getPracownicy().size());

        //serializuj
        input = "z z testSerializacja \n\n\n\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        boolean serializacja= controller.kopiaZapasowa();
        assertTrue(serializacja);


    }
    @After
    public void restoreSerializacja() {
        controller.getPracownicy().clear();
    }

 //Test 10 poprawna deserializacja
    @Before
    public void setupDeerializacjaPop(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }

    @Test
    public void deserializacjaPopTest(){
        //dodaj pracownika
        String input = "h 02272902451 Jan Kowalski 5000 123456789 15 8 \n\n";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        controller.addPracownik();
        //sprawdz czy jest w konterze
        assertEquals(1,controller.getPracownicy().size());

        //serializuj
        input = "z z testDeserializacja \n\n\n\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        boolean serializacja= controller.kopiaZapasowa();
        assertTrue(serializacja);

        controller.getPracownicy().clear();
        //deserializuj
        input= "o z testDeserializacja \n\n\n\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        boolean deserializacja = false;

        try {
            deserializacja = controller.kopiaZapasowa();
        } catch (NoSuchElementException e) {
            System.out.println("blad przy deserializacji");
        }

        // Sprawdanie, czy deserializacja zakończyła się pomyślnie
        assertTrue(deserializacja);

        // sprawdzanie czy pracownik jest w kontenerze
        assertEquals(1, controller.getPracownicy().size());
    }
    @After
    public void restoreDeserializacjaPop() {
        controller.getPracownicy().clear();
    }

//Test 11 niepoprawna deserializacja ze wzgledu na brak pliku
    @Before
    public void setupDeerializacja(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }

    @Test
    public void deserializacjaTest(){
        //deserializuj
        String input = "o z testSerializacja \n\n\n\n";
        boolean serializacja = false;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        try{
            serializacja= controller.kopiaZapasowa();
        }catch (NoSuchElementException e){
            System.out.println("nie znaleziono pliku");
        }
        assertFalse(serializacja);
    }
    @After
    public void restoreDeserializacja() {
        controller.getPracownicy().clear();
    }

    //Test 12: oczekiwany wyjatek

    @Before
    public void setupWyjatek(){
        view = new PracownikView();
        controller = new PracownikController(view);
    }
    @Test
    public void wyjatekTest() {

        String input = "h 02272902451 Jan Duda dodatek 123456789 15 8 \n\n\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

    // Sprawdzenie, czy zostanie InputMismatchException (dodatek zamiast int jest string)
        assertThrows(InputMismatchException.class, () -> {
            controller.addPracownik();
        });

    }
    @After
    public void restoreWyjatek() {
        controller.getPracownicy().clear();
    }
}
