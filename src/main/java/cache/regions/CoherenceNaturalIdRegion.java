package cache.regions;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;

import cache.strategy.CoherenceNonStrictReadWriteNaturalIdRegionAccessStrategy;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceNaturalIdRegion extends CoherenceTransactionalDataRegion implements NaturalIdRegion {

	public CoherenceNaturalIdRegion(NamedCache cache, CacheDataDescription metadata) {
		super(cache, metadata);
	}

	public long nextTimestamp() {
		return CacheFactory.getCluster().getTimeMillis();
	}

	public int getTimeout() {
		return 10000;
	}

	@Override
	public boolean isTransactionAware() {
		return false;
	}

	@Override
	public CacheDataDescription getCacheDataDescription() {
		return metadata;
	}

	@Override
	public NaturalIdRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		switch (accessType) {
		case NONSTRICT_READ_WRITE:
			return new CoherenceNonStrictReadWriteNaturalIdRegionAccessStrategy(this);
		default:
			throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
		}
	}

}
