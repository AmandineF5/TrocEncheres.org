package fr.eni.project01.trocenchere.dal.enchere;


import java.util.List;

import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public interface EnchereDAO {

	public Enchere selectByUserId(int noUtilisateur) throws BusinessException;
	public List<Enchere> selectByVenteId(int noVente) throws BusinessException;
	public Enchere insert(Enchere newEnchere) throws BusinessException;
	public void delete(int noVente) throws BusinessException;
}