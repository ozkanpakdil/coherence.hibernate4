coherence.hibernate4
====================

hibernate 4 second level cache library for oracle coherence.

examples:

persistence.xml for jpa
<property name="hibernate.cache.region.factory_class" value="cache.CoherenceRegionFactory"/>

applicationContext.xml for spring
<bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="packagesToScan" value="*" />
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
				<prop key="hibernate.show_sql">false</prop>
				<prop key="hibernate.current_session_context_class">org.springframework.orm.hibernate4.SpringSessionContext</prop>
				<prop key="hibernate.cache.use_second_level_cache">true</prop>
				<prop key="hibernate.cache.use_query_cache">true</prop>
				<prop key="hibernate.cache.region.factory_class">cache.CoherenceRegionFactory</prop>
			</props>
		</property>
	</bean>
