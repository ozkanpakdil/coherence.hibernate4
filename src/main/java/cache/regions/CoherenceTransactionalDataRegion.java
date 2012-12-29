package cache.regions;

import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.TransactionalDataRegion;

import com.tangosol.net.NamedCache;

public class CoherenceTransactionalDataRegion extends CoherenceGeneralDataRegion implements TransactionalDataRegion {

	protected final CacheDataDescription metadata;

	public CoherenceTransactionalDataRegion(NamedCache cache, CacheDataDescription metadata) {
		super(cache);
		this.metadata = metadata;
	}

	public boolean isTransactionAware() {
		return false;
	}

	public CacheDataDescription getCacheDataDescription() {
		return metadata;
	}
}