package pl.sidor.CarInsurancesSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageException {

    NIEOCZEKIWANY_BLAD("Wystąpił nieoczekiwany błąd systemu"),
    NIE_ZNALEZIONO_UBEZPIECZENIA("Nie znaleziono ubezpieczenia samochodu");

    private  final String message;
}
