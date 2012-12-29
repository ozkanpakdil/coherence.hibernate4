package cache.strategy;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;

import cache.regions.CoherenceEntityRegion;

public class CoherenceNonStrictReadWriteEntityRegionAccessStrategy extends CoherenceEntityRegionAccessStrategy implements EntityRegionAccessStrategy {

	public CoherenceNonStrictReadWriteEntityRegionAccessStrategy(CoherenceEntityRegion region) {
		super(region);
	}

	public EntityRegion getRegion() {
		return getCoherenceEntityRegion();
	}

	public boolean insert(Object key, Object value, Object version) throws CacheException {
		return false;
	}

	public boolean afterInsert(Object key, Object value, Object version) throws CacheException {
		return false;
	}

	public boolean update(Object key, Object value, Object currentVersion, Object previousVersion) throws CacheException {
		return false;
	}

	public boolean afterUpdate(Object key, Object value, Object currentVersion, Object previousVersion, SoftLock softLock) throws CacheException {
		return false;
	}
}