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
package cz.wicketstuff.examples.spring.persistence.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import cz.wicketstuff.examples.spring.persistence.PersistenceConfig;
import cz.wicketstuff.examples.spring.persistence.mybatis.dao.MyBatisDaoPackageMarker;

/**
 * @author Martin Strejc (strma17)
 *
 */
@Configuration
@MapperScan(basePackageClasses={MyBatisDaoPackageMarker.class})
public class MyBatisConfig {
	
	@Value("classpath:mybatis-mapperConfig.xml")
	private Resource myBatisConfigResource;
	
	@javax.annotation.Resource(mappedName = PersistenceConfig.JNDI_JDBC_RESOURCE)
	private DataSource dataSource;

	@Autowired(required = false)
	private TransactionFactory transactionFactory;

	@Bean 
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactory;
		sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setTransactionFactory(transactionFactory);
		sqlSessionFactory.setConfigLocation(myBatisConfigResource);
		return sqlSessionFactory;
	}

}
