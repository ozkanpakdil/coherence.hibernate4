package cache.regions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.Region;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceRegion implements Region {

	protected final NamedCache cache;

	public CoherenceRegion(NamedCache cache) {
		this.cache = cache;
	}

	public NamedCache getCache() {
		return cache;
	}

	public String getName() {
		return cache.getCacheName();
	}

	public void destroy() throws CacheException {
		// fix for 19:18:32.134 [http-thread-pool-8080(2)] ERROR com.mascix.provider.DaoUtil - from Category where parent.id =24196 order by name
		//java.lang.IllegalStateException: SafeNamedCache was explicitly released

		//cache.release();
	}

	public boolean contains(Object key) {
		return cache.containsKey(key);
	}

	public long getSizeInMemory() {
		return -1;
	}

	public long getElementCountInMemory() {
		long size = 0;
		try {
			size = cache.size();
		} catch (CacheException ex) {
			throw new CacheException(ex);
		}
		return size;
	}

	public long getElementCountOnDisk() {
		return -1;
	}

	public Map<Object, Object> toMap() {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			Iterator<?> iterator = cache.keySet().iterator();
			while (iterator.hasNext()) {
				Object key = iterator.next();
				result.put(key, cache.get((Serializable) key));
			}
		} catch (Exception ex) {
			throw new CacheException(ex);
		}
		return result;
	}

	public long nextTimestamp() {
		return CacheFactory.getCluster().getTimeMillis();
	}

	public int getTimeout() {
		return 10000;
	}
}