package teoresiGroup.web.Repository;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

/*<I extends Serializable, E extends Serializable>  creazione di due generici*/

public interface RepoGenerica <I extends Serializable, E extends Serializable> {
	@NotNull
	List<I> selezionaTutti();
	void inserisci(@NotNull I entity);
	void aggiorna(@NotNull I entity);
	void elimina(@NotNull I entity);
	void eliminaById(@NotNull E Id);
	I selezionaById(@NotNull E Id);

}
