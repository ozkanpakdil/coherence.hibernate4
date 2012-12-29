package cache.regions;

import org.hibernate.cache.spi.QueryResultsRegion;

import com.tangosol.net.NamedCache;

public class CoherenceQueryResultsRegion extends CoherenceGeneralDataRegion implements QueryResultsRegion {
	public CoherenceQueryResultsRegion(NamedCache cache) {
		super(cache);
	}
}