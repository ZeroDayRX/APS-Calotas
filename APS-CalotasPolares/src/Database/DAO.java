package Database;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAO {
	static String bd = "BancoTeste.db";
	
	
	public static void Buscar(String Coluna, String Tabela,String Condicao) {
			DB.connect(bd);
			if (Coluna == null) {
				Coluna = "*";
			}
			String Query = "SELECT "+Coluna+" FROM "+Tabela;
			if(Condicao != null) {
				Query = Query+" WHERE "+Condicao; 
			}
			
			ResultSet rs = DB.query(Query);
			
			try {
				while(rs.next()) {
					int id = rs.getInt("ID_USER");
					String nome = rs.getString("USER");
					String pass = rs.getString("PASS");
					System.out.println("ID "+id+"\n Nome: "+nome+"\n Password: "+ pass);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}
	
	public static void CadastrarUser(String nome,String user,String password) {
		int id = 0;
		DB.connect(bd);
		ResultSet rs = DB.query("SELECT MAX(ID_USER) FROM LOGIN");
		try {
			while(rs.next()) {
				id = rs.getInt("MAX(ID_USER)") + 1;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.execQuery("INSERT INTO LOGIN VALUES('"+id+"','"+nome+"','"+user+"','"+password+"')");
		
	}
	
	
	
	
	

}
