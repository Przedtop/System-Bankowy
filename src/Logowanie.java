import org.w3c.dom.Text;

import java.sql.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Klasa ta zawiera metody logowania i wszysto co z nim związane
 */

public class Logowanie {
    static Scanner input = new Scanner(System.in);
    
    private static int user_id=0;
    private static String user_login="";


    public static int getUserId(){
        return user_id;
    }
    public static void resetUserId(){user_id=0;}

    public static String getUserLogin(){
        return user_login;
    }
    public static void resetUserLogin(){user_login="";}

    public static boolean logowanie(){
        TextEdit.wyczyscKonsole();
        System.out.println("Witaj w panelu logowania!");
        System.out.println("Podaj swój login lub idKey: ");
        String login = input.nextLine();
        System.out.println("Podaj swoje hasło: ");
        String haslo = input.nextLine();
        if(sprawdzenieDancyhLogowania(haslo,login)){
            if(pierwszeZalogowanie(login)){
                Logowanie.dodajLogin(login);
            }
            stworzSesje(login);
            user_login=login;
            return true;
        }
        else {
            System.out.println("Błędny login i/lub hasło");
            return false;
        }
    }


    /**
     * Metoda ta sprawdza czy dane podane podczas logowanie są poprawne w momencie w którym użytkownik podał login
     */
    private static boolean sprawdzenieDancyhLogowania(String haslo, String login){
        String zapytanie = "SELECT COUNT(id) FROM konta WHERE idKey=? OR login=? AND haslo=?;";
        try(Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(zapytanie))
        {
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,login);
            preparedStatement.setString(3,Szyfrowanie.szyfrowanieDanych(haslo));
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) == 1;
            }
        }
        catch(SQLException e){
            System.out.println("Eroor code: DB100");
            if(Settings.getDevModeStatus()){
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();}
        }
        return false;
    }

    /**
     * Metoda ta sprawdza czy użytkownik loguje się na konto po raz 1
     */
    private static boolean pierwszeZalogowanie(String login){
        String zapytanie = "SELECT COUNT(id) FROM konta WHERE idKey=? AND login='';";
        try(Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(zapytanie))
        {
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) == 1;
            }
        }
        catch(SQLException e){
            System.out.println("Eroor code: DB100");
            if(Settings.getDevModeStatus()){
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();}
        }
        return false;
    }


    private static boolean isLoginAvailable(String login){
        String zapytanie = "SELECT COUNT(id) FROM konta WHERE login=?;";
        try(Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(zapytanie))
        {
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) == 1;
            }
        }
        catch(SQLException e){
            System.out.println("Eroor code: DB100");
            if(Settings.getDevModeStatus()){
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();}
        }
        return false;
    }


    /**
     * Metoda ta ustawia login dla nowego konta (podczas 1 logowania)
     */
    private static void dodajLogin(String iD){
        TextEdit.wyczyscKonsole();
        System.out.println("Logujesz się na to konto po raz 1, aby w pełni korzystać z konta należy dodać login");
        System.out.print("Podaj twój wybrany login (należy go zapamiętać ponieważ będzie on potrzebny do przysłego logowania): ");
        String login = input.nextLine();
        System.out.println("Czy napewno chcesz ustawić swój nowy login jako: "+ login + "(y/n) ?");
        String wybor = input.nextLine();

        if(login.length()<5){
            System.out.println("Login musi zawierać co najmniej 5 znaków");

            try {
                Thread.sleep(2000);
                dodajLogin(iD);
            } catch (InterruptedException e) {
                System.out.println("Eroor code: TS100");
                if(Settings.getDevModeStatus()){
                    System.out.println("Szczegułowy błąd: ");
                    e.printStackTrace();}
            }
        }
        if(wybor.equalsIgnoreCase("y"))
        {
            if(isLoginAvailable(login))
            {
                System.out.println("Login " + login + " jest już zajęty wybierz inny");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Eroor code: TS100");
                    if(Settings.getDevModeStatus()){
                        System.out.println("Szczegułowy błąd: ");
                        e.printStackTrace();}
                }
                dodajLogin(iD);
                return;
            }
            zapisywanie(iD,login);
        }
        else if (wybor.equalsIgnoreCase("n")) {
            dodajLogin(iD);
        }
        else
        {
            System.out.println("Błędna opcja");
        }
    }

    /**
     * Metoda ta zapisuje nowy login podany w dodajLogin do bazy danych
     */
    private static void zapisywanie(String iD, String login){
        String zapytanie = "UPDATE konta SET login=? WHERE idKey=?;";
        try(Connection con = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
            PreparedStatement preparedStatement = con.prepareStatement(zapytanie))
        {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, iD);

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
     * Metoda ta tworzy sesje i zapisuje ją do bazy dancyh po zalogowaniu się
     */
    private static void stworzSesje(String login){
        String zapytanie = "INSERT INTO sesje(konta_id, data) VALUES(?,?)";
        String getId = "SELECT id FROM konta WHERE idKey=? OR login=?";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String data = now.format(formatter);

        int id = 0;
        try (Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(getId))
        {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    user_id = id;
                }
            }
        }
        catch(SQLException e){
            System.out.println("Eroor code: DB100");
            if(Settings.getDevModeStatus()){
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();}
        }

        try(Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(zapytanie))
        {
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,data);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Eroor code: DB100");
            if(Settings.getDevModeStatus()){
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();
            }
        }

    }


    public static void logout(){
        TextEdit.wyczyscKonsole();
        resetUserId();
        resetUserLogin();
        System.out.println("Nastąpiło wylogowanie");
        Menu.menuLogowanie();
    }
}
