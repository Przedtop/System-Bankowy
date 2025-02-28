import java.util.Scanner;

/**
 * <pre>
 * Jest to klasa w której znajdują się:
 * Menu logowania
 * Menu testera
 * Menu developera
 * </pre>
 */
public class Menu {
    static Scanner input = new Scanner(System.in);

    static void testerModeMenu(){
        if(Settings.getTesterModeStatus() && Settings.testAllow){
            System.out.println("Panel testera: ");
        }
    }


    static void devModeMenu(){
        TextEdit.wyczyscKonsole();
        if(Settings.getDevModeStatus()){
            System.out.println("Panel developera: ");
            System.out.println("1. Szybkie deszyfrowanie danych");
            System.out.println("2. Szybkie deszyfrowanie danych");
            System.out.println("3. Wyjdz");
            String wybor = input.next();

            switch (wybor){
                case "1":
                    System.out.println("Podaj tekst do odszyfrowania: ");
                    String doOdszyfrowania = input.next();
                    System.out.println(Szyfrowanie.deszyfrowanieDanych(doOdszyfrowania));
                    break;
                case "2":
                    System.out.println("Podaj tekst do zaszyfrowania: ");
                    String doZaszyfrowania = input.next();
                    System.out.println(Szyfrowanie.szyfrowanieDanych(doZaszyfrowania));
                    break;
                case "3":
                    TextEdit.wyczyscKonsole();
                    break;
                default:
                    TextEdit.wyczyscKonsole();
                    System.out.println("Błędna opcja");
                    break;
            }
        }
    }


    /**
     * Jest to menu początkowe po odpaleniu programu
     */
    public static void menuLogowanie(){
        TextEdit.wyczyscKonsole();
        System.out.println("Witaj w systemie bankowym");
        System.out.println("Wersja systemu " + Settings.version + " 2025");
        System.out.println();
        System.out.println("Aby się zalogować wybierz 1");
        System.out.println("Aby się zarejestrować wybierz 2");
        String wybor = input.nextLine();


        if(wybor.equals("1"))
        {
            if(Logowanie.logowanie())ZarzadzajKontem.menuGlowne();
            else {
                System.out.println("Logowanie nie powiodło się");

                try {
                    Thread.sleep(2000);
                    Menu.menuLogowanie();
                } catch (InterruptedException e) {
                    System.out.println("Eroor code: TS100");
                    if(Settings.getDevModeStatus()){
                        System.out.println("Szczegułowy błąd: ");
                        e.printStackTrace();}
                }
            }
        }


        else if(wybor.equals("2"))
        {
            if(Rejestracja.rejestracja()){
                if(Logowanie.logowanie())
                {
                    ZarzadzajKontem.menuGlowne();
                }
                else{
                    System.out.println("Logowanie nie powiodło się");

                    try {
                        Thread.sleep(2000);
                        Menu.menuLogowanie();
                    } catch (InterruptedException e) {
                        System.out.println("Eroor code: TS100");
                        if(Settings.getDevModeStatus()){
                            System.out.println("Szczegułowy błąd: ");
                            e.printStackTrace();}
                    }
                }
            }
            else {
                System.out.println("Rejestracja nie powiodła się");

                try {
                    Thread.sleep(2000);
                    Menu.menuLogowanie();
                } catch (InterruptedException e) {
                    System.out.println("Eroor code: TS100");
                    if(Settings.getDevModeStatus()){
                        System.out.println("Szczegułowy błąd: ");
                        e.printStackTrace();}
                }
            }
        }


        else if(wybor.equalsIgnoreCase("dev"))
        {
            if(Settings.getDevModeStatus()){
                devModeMenu();
                menuLogowanie();
            }
            else {
                System.out.println("Błędna opcja");

                try {
                    Thread.sleep(2000);
                    Menu.menuLogowanie();
                } catch (InterruptedException e) {
                    System.out.println("Eroor code: TS100");
                    if(Settings.getDevModeStatus()){
                        System.out.println("Szczegułowy błąd: ");
                        e.printStackTrace();}
                }
            }
        }


        else if(wybor.equalsIgnoreCase("tester"))
        {
            if(Settings.getTesterModeStatus()){
                testerModeMenu();
                menuLogowanie();
            }
            else {
                System.out.println("Błędna opcja");

                try {
                    Thread.sleep(2000);
                    Menu.menuLogowanie();
                } catch (InterruptedException e) {
                    System.out.println("Eroor code: TS100");
                    if (Settings.getDevModeStatus()) {
                        System.out.println("Szczegułowy błąd: ");
                        e.printStackTrace();
                    }
                }
            }
        }


        else {
            System.out.println("Błędna opcja");

            try {
                Thread.sleep(2000);
                Menu.menuLogowanie();
            } catch (InterruptedException e) {
                System.out.println("Eroor code: TS100");
                if (Settings.getDevModeStatus()) {
                    System.out.println("Szczegułowy błąd: ");
                    e.printStackTrace();
                }
            }
        }
    }
}
