package teoresiGroup.web.model;

/*import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

public class LibriMapper implements RowMapper<Libri> {
private static final Logger log= Logger.getLogger(LibriMapper.class.getName());
	@Override
	public Libri mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Libri lb= new Libri();
		
		
		try {
			
			lb.setAutore(rs.getString("autore").trim());
			lb.setTitolo(rs.getString("titolo").trim());
			lb.setNumeroPezzi(rs.getInt("numeroPezzi"));
			lb.setDataPubblicazione(rs.getDate("dataPubblicazione"));
		}
		catch(SQLException q) {
			log.info(q.getCause());
		}
		
		
		return lb;
	}

}
*/