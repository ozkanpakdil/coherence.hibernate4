package cache.strategy;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.access.RegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;

import cache.regions.CoherenceCollectionRegion;

public class CoherenceCollectionRegionAccessStrategy implements RegionAccessStrategy {

	private final CoherenceCollectionRegion coherenceCollectionRegion;

	public CoherenceCollectionRegionAccessStrategy(CoherenceCollectionRegion coherenceCollectionRegion) {
		this.coherenceCollectionRegion = coherenceCollectionRegion;
	}

	public CoherenceCollectionRegion getCoherenceCollectionRegion() {
		return coherenceCollectionRegion;
	}

	public Object get(Object key, long transactionTimeStamp) throws CacheException {
		return getCoherenceCollectionRegion().get(key);
	}

	public boolean putFromLoad(Object key, Object value, long transactionTimeStamp, Object version) throws CacheException {
		return putFromLoad(key, value, transactionTimeStamp, version, true);
	}

	public boolean putFromLoad(Object key, Object value, long transationTimeStamp, Object version, boolean minimalPutsOverride) throws CacheException {
		if (key == null || value == null) {
			return false;
		}

		// check if item is already in the cache
		if (minimalPutsOverride && getCoherenceCollectionRegion().contains(key)) {
			return false;
		}

		// key is cached
		getCoherenceCollectionRegion().put(key, value);
		return true;
	}

	public SoftLock lockItem(Object key, Object version) throws CacheException {
		getCoherenceCollectionRegion().getCache().lock(key);
		return null;
	}

	public SoftLock lockRegion() throws CacheException {
		return null;
	}

	public void unlockItem(Object key, SoftLock softLock) throws CacheException {
		getCoherenceCollectionRegion().getCache().unlock(key);
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
		getCoherenceCollectionRegion().evict(key);
	}

	public void evictAll() throws CacheException {
		getCoherenceCollectionRegion().evictAll();
	}
}