package Database;

public class Animal {
	private String IdAnimal;
	private String NomeComum;
	private String NomeCientifico;
	private String Habitat;
	
	public Animal() {
		IdAnimal = "";
		NomeComum = "";
		NomeCientifico = "";
		Habitat = "";
	}
	
	public String getIdAnimal() {
		return IdAnimal;
	}
	public void setIdAnimal(String idAnimal) {
		IdAnimal = idAnimal;
	}
	public String getNomeComum() {
		return NomeComum;
	}
	public void setNomeComum(String nomeComum) {
		NomeComum = nomeComum;
	}
	public String getNomeCientifico() {
		return NomeCientifico;
	}
	public void setNomeCientifico(String nomeCientifico) {
		NomeCientifico = nomeCientifico;
	}
	public String getHabitat() {
		return Habitat;
	}
	public void setHabitat(String habitat) {
		Habitat = habitat;
	}
	
	public void Limpar() {
		IdAnimal = "";
		NomeComum = "";
		NomeCientifico = "";
		Habitat = "";
	}
			

}
