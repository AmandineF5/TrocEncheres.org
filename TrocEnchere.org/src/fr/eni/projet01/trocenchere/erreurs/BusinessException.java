
// AMANDINE

package fr.eni.projet01.trocenchere.erreurs;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<String> BusinessCodesErreurs;
	
	public BusinessException() {
		super();
		this.BusinessCodesErreurs=new ArrayList<>();
	}
	
	
	public void ajouterErreur(String err){
		if(!this.BusinessCodesErreurs.contains(err))
		{
			this.BusinessCodesErreurs.add(err);
		}
	}
	
	public boolean verifieErreurs(){
		return this.BusinessCodesErreurs.size()>0;
	}
	
	public List<String> getListeErreur(){
		return this.BusinessCodesErreurs;
	}

}
