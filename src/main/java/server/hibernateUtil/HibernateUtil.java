package server.hibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import server.entity.Anime;
import server.entity.Episode;
import server.entity.User;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
				
				Map<String, Object> settings = new HashMap<>();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/jersey");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				settings.put(Environment.SHOW_SQL, "false");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				//settings.put(Environment.VALIDATE_QUERY_PARAMETERS, "SELECT 1");
				// Enable second level cache (default value is true)
				settings.put(Environment.USE_SECOND_LEVEL_CACHE, true);
				settings.put(Environment.USE_QUERY_CACHE, true);
				//settings.put(Environment.JPA_SHARED_CACHE_MODE, "ALL");
				settings.put(Environment.STATEMENT_FETCH_SIZE, 10);
				
				// settings.put(Environment.GENERATE_STATISTICS, true);
				
				// Specify cache region factory class
				settings.put(Environment.CACHE_REGION_FACTORY,
						org.hibernate.cache.redis.hibernate52.SingletonRedisRegionFactory.class.getName());
				settings.put(Environment.CACHE_REGION_PREFIX, "hibernate");
				
				// Specify cache provider
				settings.put(Environment.CACHE_PROVIDER_CONFIG, "hibernate-redis.properties");
				
				// HikariCP settings
				settings.put("cachePrepStmts", "true");
				settings.put("prepStmtCacheSize", "250");
				settings.put("prepStmtCacheSqlLimit", "2048");
				settings.put("useServerPrepStmts", "true");
				settings.put("cacheServerConfiguration", "true");
				settings.put("cacheResultSetMetadata", "true");
				// Maximum waiting time for a connection from the pool
				settings.put("hibernate.hikari.connectionTimeout", "10000");
				// Minimum number of ideal connections in the pool
				settings.put("hibernate.hikari.minimumIdle", "2");
				// Maximum number of actual connection in the pool
				settings.put("hibernate.hikari.maximumPoolSize", "4");
				// Maximum time that a connection is allowed to sit ideal in the pool
				settings.put("hibernate.hikari.idleTimeout", "30000");
				
				registryBuilder.applySettings(settings);
				
				registry = registryBuilder.build();
				MetadataSources sources = new MetadataSources(registry)
						.addAnnotatedClass(Anime.class)
						.addAnnotatedClass(Episode.class)
						.addAnnotatedClass(User.class);
				Metadata metadata = sources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
				
			}catch (Exception e) {
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}