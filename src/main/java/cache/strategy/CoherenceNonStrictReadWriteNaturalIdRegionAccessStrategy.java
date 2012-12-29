package cache.strategy;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;

import cache.regions.CoherenceNaturalIdRegion;

public class CoherenceNonStrictReadWriteNaturalIdRegionAccessStrategy extends CoherenceNaturalIdRegionAccessStrategy implements NaturalIdRegionAccessStrategy {

	public CoherenceNonStrictReadWriteNaturalIdRegionAccessStrategy(CoherenceNaturalIdRegion region) {
		super(region);
	}

	public NaturalIdRegion getRegion() {
		return getCoherenceNaturalIdRegion();
	}

	@Override
	public boolean insert(Object key, Object value) throws CacheException {
		return false;
	}

	@Override
	public boolean afterInsert(Object key, Object value) throws CacheException {
		return false;
	}

	@Override
	public boolean update(Object key, Object value) throws CacheException {
		return false;
	}

	@Override
	public boolean afterUpdate(Object key, Object value, SoftLock lock) throws CacheException {
		return false;
	}
}