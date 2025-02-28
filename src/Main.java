public class Main {
    public static void main(String[] args)
    {
        Menu.menuLogowanie();
    }
}

/*
                    Lista zadań



                    Changelog
28.02.2025: version 2.0
Przeprowadzono modułowe testy programu:
    - moduł rejestracji:
        Naprawiono wykryte błędy i poprawiono działanie modułu
        Zmiany w sprawdzaniu czy chasło spełnia wymagania
    - moduł logowania
    - moduł zarządzania kontem

26.02.2025: version 2.0
Drobne poprawki kodu
Zmiany w metodzie odczytywania danych z konsoli
Zmienono dodawanie nowego loginu, od teraz login musi się składać z co najmniej 5 znaków

25.02.2025: version 2.0
Dodano funkcje która umożliwia przelewanie z 1 konta na 2
Usprawniono działanie metod wpłacania i wypłacania
Poprawki wizualne usprawniające szybszą analize kodu
Dodano metodę która sprawdza id konta po idKeyu
Poprawki usprawniające szybkość działania kodu

21.02.2025: version 2.0
Rozpoczęto prace nad uzupełnianiem dancyh po załorzeniu konta przez użytkownika
Zmieniono sposób generowania seeda podczas tworzenia konta z włączonym trybem developera, od teraz po wpisaniu 0 seed wygeneruje się losowo
Dodano metodę która sprawdza czy dany login nie jest już zajęty

20.02.2025: version 2.0
Dodano metode getSaldo która pobiera saldo użytkownika z bazy danych
Dodano metode setSaldo która ustala saldo
Dostosowano wplacPieniadze pod nowe metody
Dostosowano wyplacPieniadze pod nowe metody
Naprawiono błąd z dodawaniem kont do bazy danych po rejestracji konta
Naprawiono błąd z dodawaniem dancyh i aktualizowaniem ich do bazy dancyh

19.02.2025: version 2.0
Poprawa wydajności metody odpowiadającej za tworzenie sesji
Dodano identyfikator użytkownika który zapisuje się po zalogowaniu się na konto i czyśći po wylogowaniu:
    getUserId
    resetUserId
Dodano login użytkownika który zapisuje się po zalogowaniu się na konto i czyśći po wylogowaniu
    getUserLogin
    resetUserLogin
Zmiany w rejestracji, poprawiono kilka błędów
Zmiany w devModeMenu, poprawiono kilka błędów
Zmieniono sposób pozyskiwania danych do połączenia się z bazą dancyh, teraz dane te są zapisane w klasie Settings
Zmiany w javadoc, dodano opis nie opisanych do tej klas i metod

18.02.2025: version 2.0
Zmiany w trybie developera:
    Podczas błędu związanego z bazą danych użytkownikowi wyświetla się tylko kod błędu, aby zobaczyć cały error message należy włączyć devmode
Zmiany w javadoc, dodano opis nie opisanych do tej pory klas i metod
Zmiany w szyfrowaniu i deszyfrowaniu: dodano więcej znaków do alfabetu szyfrowania
Dodano nowy check do isPasswordStrongEnough

17.02.2025: version 2.0
Dodano kolumne saldo do tabeli konta. Domyślna wartość podczas zakładania konta wynosi 1000 zł
Naprawiono błąd zwracania złego wyniku podczas rejestrowania konta
Małe poprawki w rejestracji
Małe poprawki w logowaniu
Dodano możliwość bezpiecznego wylogowania się
Dodano sesje które po zalogowaniu zapisują się w bazie danych

17.02.2025: version 1.2
Zmiany w dokumentacji javadoc:
    Pełna dokumentacja klasy Logowanie
    Pełna dokumentacja klasy Rejestracja
Naprawiono błąd z dodawaniem loginu do nowych kont
Poprawiono generowanie idKey, dodano sprawdzanie czy istnieje już konto z takim id aby nie mogły powstać 2 konta z takim samym id
Dodano pole data_utworzenia do bazy danych. Zapisuje ono date utworzenia konta
Zmiana nazwy tabeli z uzytkownicy na konta i utworzenie tabeli uzytkownicy:
    Konto konta przechowuje dane o koncie
    Konto uzytkownicy przechowuje dane uzytkownikow
Dodano panel developera który umożliwia:
    Szybkie deszyfrowanie danych
    Szybkie szyfrowanie danych
Poprawki w menu logowania

16.02.2025: version 1.2
Dodano deszyfrowanie
Naprawiono błąd losowania idKey podczas rejestracji z trybem devMode
Dodano logowanie

13.02.2025: version 1.2
Dodano szyfrowanie:
    Hasła dodawane do bazy danych są od teraz szyfrowane

12.02.2025: version 1.2
zaczęto prace nad zapisywaniem danych do bazy danych:
    utworzono bazę danych
    rejestracja pomyślnie dodaje login i hasło do bazy danych
dodano opcje która sprawdza czy hasło posiada wystarczającą ilość znaków

10.02.2025: version 1.1.1
Naprawiono błąd z przypisywaniem tego samego loginu podczas logowania
Dodano tester mode który umożliwia odpalenie programu w trybie testów

8.02.2025: version 1.1.1
Zmiany w javadoc
Nowa klasa TextEdit:
    nowa funkcja wyczyscKonsole
Dodano nową funkcje developera która umożliwia sprawdzenie danych o programie

8.02.2025: version 1.1
Dodano poprawki do rejestracji
Dodano zabezpieczenia do rejestracji
Zmiany w javadoc
Rozdzielenie rejestracji od klasy logowanie i utworzenie klasy rejestracja

25.01.2025: version 1.1
Dodano możliwość rejestracji (konta nie są zapisywane)

24.01.2025: version 1.0 
Stworzono Panel główny, zarządzanie saldem, sprawdzanie salda



                    Error codes:
 RST100 - Secured tag error in login panel. Możliwa próba obejścia systemu rejestracji
 DB100 - Błąd bazy danych
 L100 - Przejście do panelu zarządzania kontem bez logowania się
 TS100 - Błąd wątku
 S100 - Błąd systemowy
*/