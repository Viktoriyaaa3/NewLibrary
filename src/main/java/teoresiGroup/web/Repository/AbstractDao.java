package teoresiGroup.web.Repository;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

/*generico I identifica la classe di entity, il generico Id indica la chiave primaria che identifica quella stessa entity*/
/*public abstract class AbstractDao <I extends Serializable,Id extends Serializable> 
implements RepoGenerica<I,Id>{
	@PersistenceContext
	protected EntityManager em;
	
	protected final Class<I> entityClass;*/
	
	/*criteria api per fare le query*/
	/*CriteriaBuilder builder;

	@SuppressWarnings("unchecked")
	public AbstractDao()
	{
		this.entityClass = (Class<I>) ((ParameterizedType) 
				this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	private CriteriaQuery<I> init(){
		builder= this.em.getCriteriaBuilder();
		return builder.createQuery(this.entityClass);
	}
	
	
	@Override
	public @NotNull List<I> selezionaTutti() {
		 CriteriaQuery<I> query = this.init();

	        return this.em.createQuery(
	        		query.select(query.from(this.entityClass))).getResultList();
	}
	@Override
	public void inserisci(@NotNull I entity) {
		this.em.persist(entity);
		flushAndClear();
	}
	@Override
	public void aggiorna(@NotNull I entity) {
		this.em.merge(entity); 
		flushAndClear();
		
	}
	@Override
	public void elimina(@NotNull I entity) {
		this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
		flushAndClear();
		
	}
	@Override
	public void eliminaById(Id id) {
CriteriaQuery<I> query = this.init();
		
		this.em.createQuery(
				query.where(
						builder.equal(
								query.from(this.entityClass)
								.get("id"), id)
        )).executeUpdate();
		
		flushAndClear();
		
	}
	@Override
	public I selezionaById(Id id) {
		CriteriaQuery<I> query = this.init();
		
		return this.em.createQuery(
					query.where(
							builder.equal(
									query.from(this.entityClass).
									get("id"), id))).
					getSingleResult();
		
	}
	
	
	private void flushAndClear() 
	{
	    em.flush();
	    em.clear();
	}
	
}*/
