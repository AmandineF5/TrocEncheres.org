
// JANET ET AMANDINE

package fr.eni.projet01.trocenchere.dal.enchere;


import java.util.List;

import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

//Amandine Janet
public interface EnchereDAO {

	public List<Enchere> selectByUserId (int noUtilisateur) throws BusinessException;
	public List<Enchere> selectByVenteId(int noVente) throws BusinessException;
	public void insert(Enchere newEnchere) throws BusinessException;
	public void delete(int noVente) throws BusinessException;
	public Enchere selectOneByUserIdVenteId (int noUtilisateur, int noVente) throws BusinessException;
	public void updateEnchere(Enchere newEnchere) throws BusinessException;
	
}
