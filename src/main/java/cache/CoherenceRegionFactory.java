package cache;

import java.util.Properties;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cache.spi.RegionFactory;
import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cfg.Settings;

import cache.regions.CoherenceCollectionRegion;
import cache.regions.CoherenceEntityRegion;
import cache.regions.CoherenceNaturalIdRegion;
import cache.regions.CoherenceQueryResultsRegion;
import cache.regions.CoherenceTimestampsRegion;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceRegionFactory implements RegionFactory {
	private static final long serialVersionUID = 1244397177234081244L;

	@Override
	public void start(Settings settings, Properties properties) throws CacheException {
		CacheFactory.ensureCluster().start();
	}

	@Override
	public void stop() {
		CacheFactory.ensureCluster().stop();
	}

	/**
	 * if returns true coherence holds bad cache. should return true.
	 */
	@Override
	public boolean isMinimalPutsEnabledByDefault() {
		return false;
	}

	@Override
	public AccessType getDefaultAccessType() {
		return AccessType.NONSTRICT_READ_WRITE;
	}

	@Override
	public long nextTimestamp() {
		return CacheFactory.getCluster().getTimeMillis();
	}

	@Override
	public EntityRegion buildEntityRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
		NamedCache cache = CacheFactory.getCache(regionName);
		CoherenceEntityRegion cer = new CoherenceEntityRegion(cache, metadata);
		return cer;
	}

	@Override
	public NaturalIdRegion buildNaturalIdRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
		NamedCache cache = CacheFactory.getCache(regionName);
		CoherenceNaturalIdRegion coherenceGeneralDataRegion = new CoherenceNaturalIdRegion(cache, metadata);
		return (NaturalIdRegion) coherenceGeneralDataRegion;
	}

	@Override
	public CollectionRegion buildCollectionRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
		NamedCache cache = CacheFactory.getCache(regionName);
		CoherenceCollectionRegion coherenceGeneralDataRegion = new CoherenceCollectionRegion(cache, metadata);
		return coherenceGeneralDataRegion;
	}

	@Override
	public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
		NamedCache cache = CacheFactory.getCache(regionName);
		CoherenceQueryResultsRegion coherenceGeneralDataRegion = new CoherenceQueryResultsRegion(cache);
		return coherenceGeneralDataRegion;
	}

	@Override
	public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
		NamedCache cache = CacheFactory.getCache(regionName);
		CoherenceTimestampsRegion coherenceGeneralDataRegion = new CoherenceTimestampsRegion(cache);
		return coherenceGeneralDataRegion;
	}
}
