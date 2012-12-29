package cache.regions;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;

import cache.strategy.CoherenceNonStrictReadWriteCollectionRegionAccessStrategy;

import com.tangosol.net.NamedCache;

public class CoherenceCollectionRegion extends CoherenceTransactionalDataRegion implements CollectionRegion {

	public CoherenceCollectionRegion(NamedCache cache, CacheDataDescription metadata) {
		super(cache, metadata);
	}

	public CollectionRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		switch (accessType) {
		case NONSTRICT_READ_WRITE:
			return new CoherenceNonStrictReadWriteCollectionRegionAccessStrategy(this);
		default:
			throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
		}
	}
}