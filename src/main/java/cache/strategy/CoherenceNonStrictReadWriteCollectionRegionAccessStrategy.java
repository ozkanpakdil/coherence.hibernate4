package cache.strategy;

import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;

import cache.regions.CoherenceCollectionRegion;

public class CoherenceNonStrictReadWriteCollectionRegionAccessStrategy extends CoherenceCollectionRegionAccessStrategy implements CollectionRegionAccessStrategy {

	public CoherenceNonStrictReadWriteCollectionRegionAccessStrategy(CoherenceCollectionRegion region) {
		super(region);
	}

	@Override
	public CollectionRegion getRegion() {
		return getCoherenceCollectionRegion();
	}

}