package teoresiGroup.web.Repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.mapping.AccessOptions.SetOptions.Propagation;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import teoresiGroup.web.model.Logins;


@Repository("persistentTokenRepo")
@Transactional(isolation=Isolation.READ_COMMITTED/*, propagation = Propagation.REQUIRES_NEW*/)
public class PersistentToken implements PersistentTokenRepository {

	@PersistenceContext
	protected EntityManager em;
	
	private void flushAndClear() {
		em.flush();
		em.clear();
	}
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		Logins logins= new Logins();
		logins.setNomeUtente(token.getUsername());
		logins.setSerie(token.getSeries());
		logins.setToken(token.getTokenValue());
		logins.setUsato(token.getDate());
		
		this.em.persist(logins);
		flushAndClear();
		
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		String JPQL ="select a from Logins a where a.serie =:series ";
		Logins logins= (Logins)em.createQuery(JPQL).setParameter("series", series).getSingleResult();
		
		logins.setToken(tokenValue);
		logins.setUsato(lastUsed);
		
		this.em.merge(logins);
		flushAndClear();
		
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		String JPQL ="select a from Logins a where a.serie =:series ";
		Logins logins= (Logins)em.createQuery(JPQL).setParameter("series", seriesId).getSingleResult();
		
		if(logins!=null) {
			return new PersistentRememberMeToken(logins.getNomeUtente(),logins.getSerie(),
					logins.getToken(), logins.getUsato());
					
		}
		
		return null;
	}

	@Override
	public void removeUserTokens(String username) {
		String JPQL ="delete from Logins where nomeUtente=: userId";
		
		em.createQuery(JPQL).setParameter("userdId",username).executeUpdate();
		
		flushAndClear();
		
	}

}
