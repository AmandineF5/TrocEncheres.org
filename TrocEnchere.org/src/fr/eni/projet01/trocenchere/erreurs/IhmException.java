
//AMANDINE

package fr.eni.projet01.trocenchere.erreurs;

import java.util.ArrayList;
import java.util.List;

public class IhmException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<String> ServletCodesErreurs;
	
	public IhmException() {
		super();
		this.ServletCodesErreurs=new ArrayList<>();
	}
	
	
	public void ajouterErreur(String err){
		if(!this.ServletCodesErreurs.contains(err))
		{
			this.ServletCodesErreurs.add(err);
		}
	}
	
	public boolean verifieErreurs(){
		return this.ServletCodesErreurs.size()>0;
	}
	
	public List<String> getListeErreur(){
		return this.ServletCodesErreurs;
	}
}
