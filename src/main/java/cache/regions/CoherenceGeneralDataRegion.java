package cache.regions;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.GeneralDataRegion;

import com.tangosol.net.NamedCache;

public class CoherenceGeneralDataRegion extends CoherenceRegion implements GeneralDataRegion {

	public CoherenceGeneralDataRegion(NamedCache cache) {
		super(cache);
	}

	public Object get(Object key) throws CacheException {
		return getCache().get(key);
	}

	public void put(Object key, Object value) throws CacheException {
		getCache().put(key, value);
	}

	public void evict(Object key) throws CacheException {
		getCache().remove(key);
	}

	public void evictAll() throws CacheException {
		getCache().clear();
	}
}