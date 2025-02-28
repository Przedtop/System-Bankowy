import java.sql.*;

public class SprawdzSaldo {

    private static double saldo = 0;

    public static double getSaldo(int id) {
        String zapytanie = "SELECT saldo FROM konta WHERE id=?;";
        try (Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(zapytanie)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    saldo = resultSet.getDouble("saldo");
                }
            }
        } catch (SQLException e) {
            System.out.println("Eroor code: DB100");
            if (Settings.getDevModeStatus()) {
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();
            }
        }
        return saldo;
    }

    public static void setSaldo(int id, double dodajDoSalda, double odejmijOdSalda) {
        String zapytanie = "UPDATE konta SET saldo=? WHERE id=? ;";
        double noweSaldo = getSaldo(id) + dodajDoSalda - odejmijOdSalda;
        try (Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(zapytanie)) {
            preparedStatement.setDouble(1, noweSaldo);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Eroor code: DB100");
            if (Settings.getDevModeStatus()) {
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();
            }
        }
    }

    public static int checkId(long idKey) {
        String zapytanie = "SELECT id FROM konta WHERE idKey=?;";
        try (Connection connection = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(zapytanie)) {
            preparedStatement.setLong(1, idKey);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            System.out.println("Eroor code: DB100");
            if (Settings.getDevModeStatus()) {
                System.out.println("Szczegułowy błąd: ");
                e.printStackTrace();
            }
        }
        return 0;
    }
}
