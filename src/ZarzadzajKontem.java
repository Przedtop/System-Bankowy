import java.text.DecimalFormat;
import java.util.Scanner;

public class ZarzadzajKontem {
    static Scanner input = new Scanner(System.in);
    /**
     * Jest to menu główne na które użytkownik zostaje przekierowany po zalogowaniu się na konto i może z niego wykonywać operacje:
     * Wypłaty gotówki, wpłaty, sprawdzenie salda
     */
    static DecimalFormat df = new DecimalFormat("0.0#");

    public static void menuGlowne(){
        if(Logowanie.getUserId()!=0 || !Logowanie.getUserLogin().equalsIgnoreCase("")){
            TextEdit.wyczyscKonsole();
            System.out.println("Witaj w systemie bankowym");
            System.out.println("Wersja systemu " + Settings.version + " 2025");
            System.out.println();
            System.out.println("Aby sprawdzić saldo konta wybierz 1");
            System.out.println("Aby wypłacić pieniądze wybierz 2");
            System.out.println("Aby wpłacić pieniądze wybierz 3");
            System.out.println("Aby przelać pieniądze wybierz 4");
            System.out.println("Aby się wylogować wybierz 5");
            int wybor = input.nextInt();

            if (wybor == 1){
                System.out.println("Twoje saldo wynosi: " + df.format(SprawdzSaldo.getSaldo(Logowanie.getUserId())) + " zł");
                try {
                    Thread.sleep(2000);
                    ZarzadzajKontem.menuGlowne();
                } catch (InterruptedException e) {
                    System.out.println("Eroor code: TS100");
                    if(Settings.getDevModeStatus()){
                        System.out.println("Szczegułowy błąd: ");
                        e.printStackTrace();}
                }
            }
            if (wybor == 2) ZarzadzajSaldem.wyplacPieniadze();
            if (wybor == 3) ZarzadzajSaldem.wplacPieniadze();
            if (wybor == 4) ZarzadzajSaldem.przelejPieniadze();
            if (wybor == 5) Logowanie.logout();
        }
        else{
            System.out.println("Error code: L100");
        }
    }
}
