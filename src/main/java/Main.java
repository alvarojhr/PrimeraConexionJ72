import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = DbConnection.connect();

        ResultSet result = null;

        String sql = "SELECT * FROM Productos WHERE Nombre = 'Jean azul'";
        try {
            result = connection.prepareStatement(sql).executeQuery();

            while (result.next()){
                System.out.println(result.getString(1)+". "+result.getString(2)+ " - cant: "+result.getString(3)+" - costo unitario: "+result.getString(4)+"  - valor venta: "+result.getString(5));
            }

        } catch (SQLException e) {
            System.out.println("CONNECTION| "+e);
        }finally {
            try {
                if (result != null) {
                    result.close();
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("CONNECTION| "+e);
            }
        }
    }
}
