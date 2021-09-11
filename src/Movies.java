import java.sql.*;
public class Movies {
	public static void main(String[] args) throws ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
			Statement statement = connection.createStatement();
			
			statement.executeUpdate("DROP TABLE IF EXISTS movies");
			statement.executeUpdate("CREATE TABLE movies(name STRING, actor STRING, actress STRING, release_year STRING, director STRING)");
			
			String[] names = {"Batman Begins", "The Dark Knight", "The Dark Knight Rises","Tenet", "Shutter Island", "Eternal Sunshine Of The Spotless Minds", "Source Code", "The Truman Show"};
			String[] actors = {"Christian Bale", "Christian Bale", "Christian Bale", "John David Washington", "Leonardo DiCaprio", "Jim Carrey", "Jake Gyllenhaal", "Jim Carrey"};
			String[] actresses = {"Katie Holmes", "Maggie Gyllenhaal", "Anne Hathaway", "Elizabeth Debicki", "Michelle Williams", "Kate Winslet", "MIchelle Monaghan", "Laura Linney"};
			String[] release_years = {"2005", "2008", "2012", "2020", "2010", "2004", "2011", "1998"};
			String[] directors = {"Christopher Nolan", "Christopher Nolan", "Christopher Nolan", "Christopher Nolan", "Martin Scorsese", "Michel Gondry", "Duncan Jones", "Peter Weir"};
			
			String query = "";
			for(int i = 0; i < names.length; i++) {
				query = String.format("INSERT INTO movies VALUES('%s','%s','%s','%s','%s')",names[i], actors[i], actresses[i], release_years[i], directors[i]);
				statement.executeUpdate(query);
			}
			query = "SELECT * FROM movies";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				System.out.println("name of the movie: " + resultSet.getString("name"));
				System.out.println("Actor: " + resultSet.getString("actor"));
				System.out.println("Actress: " + resultSet.getString("actress"));
				System.out.println("Year of Release: " + resultSet.getString("release_year"));
				System.out.println("Directed by: " + resultSet.getString("director"));
				System.out.print("\n\n\n");
			}
			query = "SELECT name FROM movies WHERE actor = 'Christian Bale'";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				System.out.println("name of the movie/s played by Christian Bale: " + resultSet.getString("name"));
			}
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		finally {
			try {
				if(connection!=null)
					connection.close();
			}
			catch(SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
