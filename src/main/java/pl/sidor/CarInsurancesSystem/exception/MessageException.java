package pl.sidor.CarInsurancesSystem.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageException {

    NIEOCZEKIWANY_BLAD("Wystąpił nieoczekiwany błąd systemu."),
    NIE_ZNALEZIONO_UBEZPIECZENIA("Nie znaleziono ubezpieczenia samochodu."),
    BRAK_WLASCICIELA_POJAZDU("Wprowadź dane osobowe osoby ubezpieczającej pojazd."),
    BLEDNE_DATY_UBEZPIECZENIA("Daty ubezpieczenia nie mogą być puste."),
    NIEPOPRAWNY_PESEL("Wprowadzony numer PESEL jest niepoprawny."),
    NIEPOPRAWNE_DANE_POJAZDU("Wprowadzone dane pojazdu są niepoprawne."),
    NIEPOPRAWNY_NUMER_POLISY_UBEZPIECZENIOWE("Wprowadzono niepoprawny numer polisy ubezpieczeniowej."),
    NIEPRAWIDLOWE_DANE_OSOBOWE("Wprowadzono nieprawidłowe dane osobowe."),
    BRAK_PLATNOSCI("Brak płatności za ubezpieczenie samochodu.");

    private final String message;
}
