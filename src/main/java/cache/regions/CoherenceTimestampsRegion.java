package cache.regions;

import org.hibernate.cache.spi.TimestampsRegion;

import com.tangosol.net.NamedCache;

public class CoherenceTimestampsRegion extends CoherenceGeneralDataRegion implements TimestampsRegion {
	public CoherenceTimestampsRegion(NamedCache cache) {
		super(cache);
	}
}