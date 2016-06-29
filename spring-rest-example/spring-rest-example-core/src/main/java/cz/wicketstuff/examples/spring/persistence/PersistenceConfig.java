/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.wicketstuff.examples.spring.persistence;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import cz.wicketstuff.examples.spring.persistence.mybatis.MyBatisConfig;
import cz.wicketstuff.examples.spring.persistence.service.impl.PersistenceServicePackageMarker;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Configuration
@EnableAspectJAutoProxy
@Import({MyBatisConfig.class})
@ComponentScan(basePackageClasses = {PersistenceServicePackageMarker.class})
public class PersistenceConfig {
	
	public static final String JNDI_JDBC_RESOURCE = "jdbc/spring_example";
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
	     .generateUniqueName(true)
	     .setType(EmbeddedDatabaseType.H2)
	     .setScriptEncoding("UTF-8")
	     .ignoreFailedDrops(true)
	     .addScript("db-schema-h2.sql")
	     // .addScripts("init_01.sql", "init_02.sql")
	     .build();		
	}

}
