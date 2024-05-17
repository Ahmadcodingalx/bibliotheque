package org.example.livres;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

   static  String host = "localhost";
   static String port = "3306";
   static String user = "root";
   static String password = "";
   static String database = "bibliotheque";
   static String URL = "jdbc:mysql://"+host+":"+port+"/"+database;

    public static Connection connectDB() {
        try{
            return DriverManager.getConnection(URL, user, password);
        } catch (Exception e){
            return null;
        }
    }

//   public static Connection connectDB() {
//        try {
//            Class.forName("com.mysql.jdbc.cj.Driver");
//            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bibliothèque", "root", ""); //adresse, nom de la BD, mot de passe de la BD
//            return connect;
//        } catch (Exception excep) {
//            System.out.println("erreur");
//            excep.printStackTrace();
//            return null;        }
//     // CRÉONS NOTRE BASE DE DONNÉES / bibliothèque est le nom de notre base de donnée
//
//
//   }

}
