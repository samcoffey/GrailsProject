dataSource {
	/*pooled = true
	jmxExport = true
	driverClassName = "org.h2.Driver"
	username = "sa"
	password = ""*/
	pooled = true
	//dbCreate = "create-drop" // one of 'create', 'create-drop','update'
	url = "jdbc:postgresql://localhost:5432/grails"
	driverClassName = "org.postgresql.Driver"
	username = "grails"
	password = "grails"
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
	//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
	singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			//url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
			url = "jdbc:postgresql://localhost:5432/grails"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			//url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
			url = "jdbc:postgresql://localhost:5432/grails"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			driverClassName = "org.postgresql.Driver"
			dialect = org.hibernate.dialect.PostgreSQLDialect

			uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")

			url = "jdbc:postgresql://"+uri.host+uri.path
			username = uri.userInfo.split(":")[0]
			password = uri.userInfo.split(":")[1]
		}
	}
}
