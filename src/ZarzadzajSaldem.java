import java.sql.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ZarzadzajSaldem {
    private static double saldo = SprawdzSaldo.getSaldo(Logowanie.getUserId());
    static Scanner input = new Scanner(System.in);

    static DecimalFormat df = new DecimalFormat("0.0#");

    static public void wyplacPieniadze(){
        System.out.println("Na koncie posiadasz " + df.format(saldo) + " zł");
        System.out.println("Ile pieniędzy chcesz wypłacić: ");
        String kwotaDoWyplatyInput = input.next().replace(',','.').replace(" ","")
                .replace("zł","").replace("zl","");
        double kwotaDoWyplaty=0;

        try {
            kwotaDoWyplaty = Double.parseDouble(kwotaDoWyplatyInput);
        }
        catch (NumberFormatException e) {
            System.out.println("Błąd! Wpisz poprawną kwotę");
            if (Settings.getDevModeStatus()) {
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
                ZarzadzajKontem.menuGlowne();
            } catch (InterruptedException e2) {
                System.out.println("Eroor code: TS100");
                if(Settings.getDevModeStatus()){
                    System.out.println("Szczegułowy błąd: ");
                    e2.printStackTrace();}
            }
            return;
        }

        if(kwotaDoWyplaty<=saldo && kwotaDoWyplaty>0)
        {
            SprawdzSaldo.setSaldo(Logowanie.getUserId(), 0, kwotaDoWyplaty);
            System.out.println("Pomyślnie wypłaciłeś środki");
            System.out.println("Twoje saldo wynosi " + df.format(saldo-kwotaDoWyplaty) + " zł");

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
        else if (kwotaDoWyplaty<=0)
        {
            System.out.println("Kwota musi być większa od 0");

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

        else
        {
            System.out.println("Nie posiadasz tyle pieniędzy na koncie");

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
    }

    static public void wplacPieniadze(){
        System.out.println("Ile pieniędzy chcesz wpłacić: ");
        String kwotaDoWplatyInput = input.next().replace(',','.').replace(" ","")
                .replace("zł","").replace("zl","");
        double kwotaDoWplaty=0;

        try {
            kwotaDoWplaty = Double.parseDouble(kwotaDoWplatyInput);
        }
        catch (NumberFormatException e) {
            System.out.println("Błąd! Wpisz poprawną kwotę");
            if (Settings.getDevModeStatus()) {
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
                ZarzadzajKontem.menuGlowne();
            } catch (InterruptedException e2) {
                System.out.println("Eroor code: TS100");
                if(Settings.getDevModeStatus()){
                    System.out.println("Szczegułowy błąd: ");
                    e2.printStackTrace();}
            }
            return;
        }


        if (kwotaDoWplaty>0)
        {
            saldo+=kwotaDoWplaty;
            SprawdzSaldo.setSaldo(Logowanie.getUserId(), kwotaDoWplaty,0);
            System.out.println("Pomyślnie wpłaciłeś " + df.format(kwotaDoWplaty) + " zł");

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
         else
         {
            System.out.println("Kwota musi być większa od 0");
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
    }

    static public void przelejPieniadze(){
        long nrKonta;
        System.out.println("Podaj numer konta na który chcesz przelać pieniądze: ");
        nrKonta = input.nextLong();

        if(nrKonta==Logowanie.getUserId())
        {
            System.out.println("Nie możesz przelać pieniędzy na swoje konto!");

            try {
                Thread.sleep(2000);
                ZarzadzajKontem.menuGlowne();
            } catch (InterruptedException e) {
                System.out.println("Eroor code: TS100");
                if(Settings.getDevModeStatus()){
                    System.out.println("Szczegułowy błąd: ");
                    e.printStackTrace();}
            }
            return;
        }

        if(poprawnyNrKonta(nrKonta))
        {
            System.out.println("Ile chcesz przelać: ");
            String doPrzelaniaInput = input.next().replace(',','.').replace(" ","")
                    .replace("zł","").replace("zl","");
            double doPrzelania=0;

            try {
                doPrzelania = Double.parseDouble(doPrzelaniaInput);
            }
            catch (NumberFormatException e) {
                System.out.println("Błąd! Wpisz poprawną kwotę");
                if (Settings.getDevModeStatus()) {
                    System.out.println("Szczegułowy błąd: ");
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(2000);
                    ZarzadzajKontem.menuGlowne();
                } catch (InterruptedException e2) {
                    System.out.println("Eroor code: TS100");
                    if(Settings.getDevModeStatus()){
                        System.out.println("Szczegułowy błąd: ");
                        e2.printStackTrace();}
                }
                return;
            }
                if(saldo>=doPrzelania)
                {
                    SprawdzSaldo.setSaldo(Logowanie.getUserId(),0,doPrzelania);
                    SprawdzSaldo.setSaldo(SprawdzSaldo.checkId(nrKonta),doPrzelania,0);
                    System.out.println("Pomyślnie przelano " + df.format(doPrzelania) + " zł na numer konta " + nrKonta);

                    try {
                        Thread.sleep(2000);
                        ZarzadzajKontem.menuGlowne();
                        return;
                    } catch (InterruptedException e) {
                        System.out.println("Eroor code: TS100");
                        if(Settings.getDevModeStatus()){
                            System.out.println("Szczegułowy błąd: ");
                            e.printStackTrace();}
                    }

                    ZarzadzajKontem.menuGlowne();
                    return;
                }
                else
                {
                    System.out.println("Nie posiadasz tylu środków na koncie");

                    try {
                        Thread.sleep(2000);
                        ZarzadzajKontem.menuGlowne();
                        return;
                    } catch (InterruptedException e) {
                        System.out.println("Eroor code: TS100");
                        if(Settings.getDevModeStatus()){
                            System.out.println("Szczegułowy błąd: ");
                            e.printStackTrace();}
                    }
                }
        }
        else
        {
            System.out.println("Numer konta jest niepoprawny");

            try {
                Thread.sleep(2000);
                ZarzadzajKontem.menuGlowne();
                return;
            } catch (InterruptedException e) {
                System.out.println("Eroor code: TS100");
                if(Settings.getDevModeStatus()){
                    System.out.println("Szczegułowy błąd: ");
                    e.printStackTrace();}
            }
        }
        ZarzadzajKontem.menuGlowne();
    }

    private static boolean poprawnyNrKonta(long idKey){
        String zapytanie = "SELECT COUNT(id) FROM konta WHERE idKey=?;";

        try (Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(zapytanie))
        {
            preparedStatement.setLong(1,idKey);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) == 1;
            }
        }
        catch (SQLException e) {
            System.out.println("Eroor code: DB100");
            if (Settings.getDevModeStatus()) {
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();
            }
        }

        return false;
    }
}
