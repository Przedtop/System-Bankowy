import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

/**
 * Klasa ta zawiera panel rejestracji i wszystko co z nim związane
 */
public class Rejestracja {

    private static boolean securedTag =false;

    static Scanner input = new Scanner(System.in);

    private static long idKey;
    private static String rejestracjaHaslo;

    private static void idKeyGenerator(int nrPetli){
        Random random = new Random();
        long seed = random.nextLong();
        if(Settings.getDevModeStatus()){
            if(nrPetli>0) System.out.println("Istnieje już id wygenerowane na tym seedzie. Podaj inny seed");
            System.out.println("Podaj seed: ");
            Scanner scanner = new Scanner(System.in);
            seed = scanner.nextInt();
            if(seed==0) seed = random.nextLong();
        }
        Random random2 = new Random(seed);
        idKey = random2.nextInt(1000000000)+100000000;

        String zapytanie = "SELECT COUNT(id) FROM konta WHERE idKey=?;";
        try(Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(zapytanie))
        {
            preparedStatement.setLong(1,idKey);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                nrPetli++;
                if(resultSet.next() && resultSet.getInt(1) == 1) idKeyGenerator(nrPetli);
            }
        }
        catch(SQLException e){
            System.out.println("Eroor code: DB100");
            if(Settings.getDevModeStatus()){
                System.out.println("Szczegułowy błąd: ");
            e.printStackTrace();}
        }
    }


    /**
     Jest to główna metoda logowania
     */
    public static boolean rejestracja(){
        TextEdit.wyczyscKonsole();
        System.out.println("Witaj w panelu rejestracyjnym");
        System.out.println("Zaraz zostanie wygenerowany twój unikalny id");
        idKeyGenerator(0);
        System.out.println("Oto twój unikalny id: " + idKey);
        System.out.println("Aby dokończyć tworzenie konta ustal hasło: ");
        String haslo = input.nextLine();
        TextEdit.wyczyscKonsole();
        if(!isPasswordStrongEnough(haslo)){
            if (bledneHaslo()) {
                securedTag = true;
                rejestracjaCompleted();
                return true;
            }
            else{
                return false;
            }
        }
        System.out.println("Powtórz hasło: ");
        String powtorzHaslo = input.nextLine();
        if(!haslo.equals(powtorzHaslo))
        {
                TextEdit.wyczyscKonsole();
                System.out.println("Hasła nie są takie same!");
                System.out.println("Spróbuj ponownie");
                if (bledneHaslo()) {
                    securedTag = true;
                    rejestracjaCompleted();
                    return true;
                }
                else return false;

        }
        else
        {
            rejestracjaHaslo=haslo;
            securedTag =true;
            rejestracjaCompleted();
            return true;
        }
    }


    /**
     * Metoda ta wywoływana jest po błędnym wpisaniu hasła
     */
    private static boolean bledneHaslo(){
        System.out.println("Aby dokończyć tworzenie konta ustal hasło: ");
        String haslo = input.nextLine();
        TextEdit.wyczyscKonsole();
        if(!isPasswordStrongEnough(haslo)){
            return false;
        }
        System.out.println("Powtórz hasło: ");
        String powtorzHaslo = input.nextLine();
        if(!haslo.equals(powtorzHaslo))
        {
            System.out.println("Hasła nie są takie same tworzenie konta przerwane");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Eroor code: TS100");
                if(Settings.getDevModeStatus()){
                    System.out.println("Szczegułowy błąd: ");
                    e.printStackTrace();}
            }
            return false;
        }
        else{
            rejestracjaHaslo=haslo;
            return true;
        }
    }

    /**
     * Metoda ta zapisuje dane do bazy danych
     */
    public static void zapisywanie(long id, String haslo){
        String zapytanie = "INSERT INTO konta (idKey, haslo, login, data_utworzenia, saldo) VALUES (?,?,?,?,?)";
        try(Connection con = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
        PreparedStatement preparedStatement = con.prepareStatement(zapytanie))
        {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String data = now.format(formatter);

            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, haslo);
            preparedStatement.setString(3,"");
            preparedStatement.setString(4,data);
            preparedStatement.setInt(5,1000);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Eroor code: DB100");
            if(Settings.getDevModeStatus()){
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();}
        }
    }

    /**
     * Metoda ta jest wywoływana po pomyślnym ukończeniu procesu rejestracji
     */
    private static void rejestracjaCompleted(){
        if(securedTag) {
            TextEdit.wyczyscKonsole();
            System.out.println("Pomyślnie ukończyłeś rejestracje");
            System.out.println("Oto dane do twojego nowego konta");
            System.out.println("Login: " + idKey + " Hasło: " + rejestracjaHaslo);
            System.out.println("Dane te będą potrzebne do zalogowania się!");
            zapisywanie(idKey,Szyfrowanie.szyfrowanieDanych(rejestracjaHaslo));
            try {
                TextEdit.wyczyscKonsole(1);
                System.out.print("Naciśnij enter aby kontynuować");
                System.in.read();
            } catch (IOException e) {
                System.out.println("Eroor code: S100");
                if(Settings.getDevModeStatus()){
                    System.out.println("Szczegułowy błąd: ");
                    e.printStackTrace();}
            }
        }
        else System.out.println("Eroor code: RST100");
    }

    /**
     * Metoda ta sprawdza czy hasło jest zgodne z wymaganiami
     */
        private static boolean isPasswordStrongEnough(String haslo){
        int[] checklist = new int[2];

        int iloscZnakow = haslo.length();
        if(iloscZnakow>=8 && iloscZnakow<=24){
            checklist[0]=1;
        }

        char[] znaki = haslo.toCharArray();
            String wszystkieSymbole = "ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŹŻabcdefghijklmnopqrstuvwxyząćęłńóśźż0123456789!@#$%^&*()_+-={}[]|;:.<>?~";
            char[] alfabet = wszystkieSymbole.toCharArray();
            int poprawneZnaki=0;
        for(int i=0; i<znaki.length;i++){
            for(int j=0; j<alfabet.length; j++){
                if(znaki[i]==alfabet[j]){
                    poprawneZnaki++;
                }
            }
        }

        if(poprawneZnaki==haslo.length()) checklist[1]=1;

        if(checklist[0]==1 && checklist[1]==1) return true;
        else if(checklist[0]==0){
            System.out.println("Twoje hasło nie spełnia wymagań!");
            System.out.println("Hasło musi zawierać co najmniej 8 i maksymalnie 24 znaki");
            return false;
        }
        else if(checklist[1]==0){
            System.out.println("Hasło zawiera niedozwolone znaki");
            return false;
        }
        return false;
        }
}
