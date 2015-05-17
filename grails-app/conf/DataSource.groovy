import groovy.json.JsonSlurper

dataSource {
    pooled = true
    jmxExport = true
//    driverClassName = "org.h2.Driver"
    driverClassName = "org.postgresql.Driver"
//    username = "sa"

    if (System.env.VCAP_SERVICES) {
        def vcapServices = new JsonSlurper().parseText(System.env.VCAP_SERVICES)
        def settingsJson = vcapServices['postgresql-9.1'][0]
        def credentials = settingsJson.credentials

        url = "jdbc:postgresql://${credentials.host}:${credentials.port}/${credentials.name}"
        username = credentials.username
        password = credentials.password

    } else if (System.env.DATABASE_URL || System.env.OPENSHIFT_POSTGRESQL_DB_URL) {
        if (System.env.DATABASE_URL) {
            uri = new URI(System.env.DATABASE_URL)
        } else {
            uri = new URI(System.env.OPENSHIFT_POSTGRESQL_DB_URL)
        }

        url = "jdbc:postgresql://"+uri.host+uri.path
        username = uri.userInfo.split(":")[0]
        password = uri.userInfo.split(":")[1]
    } else {
        url = "jdbc:postgresql://localhost:5432/lwm"
        username = "sonerik"
        password = ""
    }


    dialect = "org.hibernate.dialect.PostgreSQLDialect"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3

    // FIXME: Workaround for https://jira.grails.org/browse/GPSPRINGSECURITYCORE-318
    cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory'

//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4

    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
//            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
//            url = "jdbc:postgresql://localhost:5432/lwm"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
//            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            properties {
               // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
               jmxEnabled = true
               initialSize = 5
               maxActive = 50
               minIdle = 5
               maxIdle = 25
               maxWait = 10000
               maxAge = 10 * 60000
               timeBetweenEvictionRunsMillis = 5000
               minEvictableIdleTimeMillis = 60000
               validationQuery = "SELECT 1"
               validationQueryTimeout = 3
               validationInterval = 15000
               testOnBorrow = true
               testWhileIdle = true
               testOnReturn = false
               jdbcInterceptors = "ConnectionState"
               defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
            }
        }
    }
}
