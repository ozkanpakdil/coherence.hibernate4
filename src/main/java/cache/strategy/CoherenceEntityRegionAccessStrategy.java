package cache.strategy;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.access.RegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;

import cache.regions.CoherenceEntityRegion;

public class CoherenceEntityRegionAccessStrategy implements RegionAccessStrategy {

	private final CoherenceEntityRegion coherenceEntityRegion;

	public CoherenceEntityRegionAccessStrategy(CoherenceEntityRegion coherenceEntityRegion) {
		this.coherenceEntityRegion = coherenceEntityRegion;
	}

	public CoherenceEntityRegion getCoherenceEntityRegion() {
		return coherenceEntityRegion;
	}

	public Object get(Object key, long transactionTimeStamp) throws CacheException {
		return getCoherenceEntityRegion().get(key);
	}

	public boolean putFromLoad(Object key, Object value, long transactionTimeStamp, Object version) throws CacheException {
		return putFromLoad(key, value, transactionTimeStamp, version, true);
	}

	public boolean putFromLoad(Object key, Object value, long transationTimeStamp, Object version, boolean minimalPutsOverride) throws CacheException {
		if (key == null || value == null) {
			return false;
		}

		// check if item is already in the cache
		if (minimalPutsOverride && getCoherenceEntityRegion().contains(key)) {
			return false;
		}

		// key is cached
		getCoherenceEntityRegion().put(key, value);
		return true;
	}

	public SoftLock lockItem(Object key, Object version) throws CacheException {
		getCoherenceEntityRegion().getCache().lock(key);
		return null;
	}

	public SoftLock lockRegion() throws CacheException {
		return null;
	}

	public void unlockItem(Object key, SoftLock softLock) throws CacheException {
		getCoherenceEntityRegion().getCache().unlock(key);
	}

	public void unlockRegion(SoftLock softLock) throws CacheException {
		evictAll();
	}

	public void remove(Object key) throws CacheException {
		evict(key);
	}

	public void removeAll() throws CacheException {
		evictAll();
	}

	public void evict(Object key) throws CacheException {
		getCoherenceEntityRegion().evict(key);
	}

	public void evictAll() throws CacheException {
		getCoherenceEntityRegion().evictAll();
	}
}