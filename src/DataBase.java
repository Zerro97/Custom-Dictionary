import java.sql.*;
import java.util.*;
/**
 * This class is database using MySQL. It stores the data that the user inputted
 * 
 * @author Kimhuibeom
 *
 */

public class DataBase {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/dictionary";
	private static final String DB_USER = "jordan";
	private static final String DB_PASSWORD = "password";
	
	private String[] definition;
	private String[] word;
	
	public static void push(int primaryIdCount, String defineWord, String definitionOfWord)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT worktables" + "(primaryId, definedWord, definitionOfWord) VALUES" + "(?,?,?)";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			//preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			preparedStatement.setInt(1, primaryIdCount);
			preparedStatement.setString(2, defineWord);
			preparedStatement.setString(3, definitionOfWord);
			// execute insert SQL statement
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return dbConnection;
	}
	
	public static String getWordList() throws SQLException {
		String wordInfo = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary", "jordan",
					"password");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from worktables");
			while (rs.next()) {
				wordInfo += "ID #" + rs.getInt(1) + "    " + rs.getString(2) + "\nDefinition: " + rs.getString(3)
						+ " \n\n";
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return wordInfo;
	}

	// method to return definition for quiz.
	public static Vocabulary getVocabulary(int pID) {
		Vocabulary word = new Vocabulary();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary", "jordan",
					"password");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from worktables where primaryID = " + pID);

			while (rs.next()) {
				word.setPrimaryID(rs.getInt(1));
				word.setWord(rs.getString(2));
				word.setDefinition(rs.getString(3));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return word;
	}
	// method to compare if data for pID is matching for all rows.

	public static void delete(int id) {
		id = 0;
		String wordInfo = "";
		ArrayList<Integer> IDs = new ArrayList<>();
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary", "jordan",
					"password");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from worktables");
			while (rs.next()) {
				IDs.add(rs.getInt(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		for (int i = 0; i<IDs.size(); i++) {
			System.out.println(IDs.get(i));
		}
		
        String sql = "DELETE FROM worktables where primaryID = ?;";
        for (int i = 0; i < IDs.size();i++) {
        try {
        		  Connection conn = getDBConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql) ;
        		  id = IDs.get(i); 
        		// set the corresponding param
              pstmt.setInt(1, id);
             // execute the delete statement
              pstmt.executeUpdate();
        	}
//         
 
         catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
    }

}
