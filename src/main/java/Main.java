import Model.DbConnection;
import View.Info;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Info.showProducts();
        Info.newProduct();
    }
}
