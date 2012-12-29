package cache.regions;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;

import cache.strategy.CoherenceNonStrictReadWriteEntityRegionAccessStrategy;

import com.tangosol.net.NamedCache;

public class CoherenceEntityRegion extends CoherenceTransactionalDataRegion implements EntityRegion {

	public CoherenceEntityRegion(NamedCache cache, CacheDataDescription metadata) {
		super(cache, metadata);
	}

	public EntityRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		switch (accessType) {
		case NONSTRICT_READ_WRITE:
			return new CoherenceNonStrictReadWriteEntityRegionAccessStrategy(this);
		default:
			throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
		}
	}
}