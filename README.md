# ssm-study
spring+springmvc+mybatis

# spring 学习
* 详细请参考[spring-study](https://github.com/Ashesoft/spring-study.git)这个项目

# 视图解析
* `org.springframework.web.servlet.view.JstlView`使用 JSP 作为页面展示**不推荐**
    * 添加依赖, 虽然 spring 默认使用的是 JSP, 但还要导入 jstl 表达式的标签库 
    ```xml
    <dependency>
        <groupId>javax.servlet.jsp.jstl</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <!-- 或 [二选一] -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    ```
    * 在`application-spring-mvc.xml`中装配 jsp 视图解析器
    ```xml
    <!-- springframework 默认使用 jsp, 而要使用 html 就需要引入第三方开发库来代替它如: freemarker, thymeleaf[推荐, springboot 经常使用] -->
    <!-- 配置视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- jsp 视图 -->
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <!--前缀-->
        <property name="prefix" value="/WEB-INF/views" />
        <!--后缀-->
        <property name="suffix" value=".jsp" />
    </bean>
    ```
* `org.springframework.web.servlet.view.freemarker.FreeMarkerView`使用 HTML 作为页面展示 **不熟悉**
    * 添加依赖
    ```xml
    <!-- https://mvnrepository.com/artifact/org.freemarker/freemarker -->
    <dependency>
        <groupId>org.freemarker</groupId>
        <artifactId>freemarker</artifactId>
        <version>2.3.30</version>
    </dependency>
    ```
    * 在主配置文件`application-spring.xml`中装配`FreeMarker`
    ```xml
    <bean id="freemarkerConfig" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
        <!-- 模板存放的位置 -->
        <property name="templateLoaderPath" value="/WEB-INF/views/" />
        <!-- properties 属性注入 -->
        <property name="freemarkerSettings">
            <props>
                <prop key="template_update_delay">0</prop>
                <prop key="default_encoding">UTF-8</prop>
                <prop key="number_format">0.##########</prop>
                <prop key="datetime_format">yyyy-MM-dd HH:mm:ss</prop>
                <prop key="classic_compatible">true</prop>
                <prop key="template_exception_handler">ignore</prop>
            </props>
        </property>
    </bean>
    ```
    * 接下来在`application-spring-mvc.xml`文件中装配 HTML 视图解析器
    ```xml
     <!-- 设置 freeMarker 的配置文件路径 -->
    <bean id="freemarkerConfiguration" class="org.springframework.beans.factory.config.PropertiesFactoryBean">
        <!--注释掉的下方代码是指引 freemarker 的基本信息的配置位置，因为我已经将配置信息移到了 application-spring.xml 文件下，所以这里就没必要存在了，不注释也不会有问题的 -->
        <!--<property name="location" value="classpath:/WEB-INF/config/freemarker.properties" />-->
    </bean>
    <!-- freemarker 视图解析器装配 -->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
        <property name="exposeRequestAttributes" value="true" />
        <property name="exposeSessionAttributes" value="true" />
        <property name="viewClass" value="org.springframework.web.servlet.view.freemarker.FreeMarkerView" />
        <property name="cache" value="true" />
        <!--这里需要注意一下, 
        我注释了下面这样一行代码, 这行代码的意思就是指引 freemarker 需要解析的文件的位置. 
        注释掉原因是因为 application-spring.xml 文件中已经指定了视图位置.
        如果我们这里依然保留下方代码, 页面会报406的找不到的错误 -->
        <!-- <property name="prefix" value="/WEB-INF/views/" /> -->
        <property name="suffix" value=".html" />
        <property name="contentType" value="text/html; charset=UTF-8" />
    </bean>
    ```
    * 具体代码请[参考](https://blog.csdn.net/RollJay/article/details/97544593)
* `org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver`使用 HTML 作为页面展示 **推荐**
    * 添加依赖
    ```xml
    <!-- 添加 HTML 视图的支持 -->
    <dependency>
      <groupId>org.thymeleaf</groupId>
      <artifactId>thymeleaf-spring5</artifactId>
      <version>3.0.11.RELEASE</version>
    </dependency>
    ```
    * 在`application-spring.xml`或`application-spring-mvc.xml`配置`THYMELEAF`
    ```xml
    <!-- ====================================================== THYMELEAF START ====================================================== -->
    <!-- SpringResourceTemplateResolver会自动与Spring自己的资源解析基础架构集成, 因此强烈建议使用 -->
    <bean id="htmlResolver" class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".html" />
        <!-- HTML是默认值, 为清楚起见在此处添加 -->
        <property name="templateMode" value="HTML" />
        <!-- 默认情况下模板缓存为true. 如果需要，设置为false, 修改后将自动更新的模板 -->
        <property name="cacheable" value="true" />
    </bean>

    <!-- SpringTemplateEngine自动应用SpringStandardDialect并启用Spring自己的MessageSource消息解析机制 -->
    <bean id="htmlEngine" class="org.thymeleaf.spring5.SpringTemplateEngine">
        <property name="templateResolver" ref="htmlResolver" />
        <!-- 在大多数情况下, 使用Spring 4.2.4或更高版本启用SpringEL编译器可以加快执行速度, 
            但是在某些情况下（当一个模板中的表达式在不同的数据类型之间重用时）可能不兼容. 
            因此默认情况下此标志为 false 以确保向后安全兼容性 -->
        <property name="enableSpringELCompiler" value="true" />
    </bean>
    <!-- 解析器 -->
    <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="templateEngine" ref="htmlEngine" />
        <property name="viewNames" value="*.html,*.xhtml" />
    </bean>
    <!-- ====================================================== THYMELEAF END ====================================================== -->
    ```
    * 具体使用请参考[官网](https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html#the-springstandard-dialect)

# 使用 maven 时, 跳过测试
* 命令行加参数
```bat
mav package -DskipTests
```
或
```bat
mav package -Dmaven.test.skip=true
```
* 使用 maven 插件
```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<version>2.18.1</version>
	<configuration>
		<skipTests>true</skipTests>
	</configuration>
</plugin>
```
* 在`pom.xml`文件中定义变量
```xml
  <properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
    <!-- 
      使用 maven 相关命令时, 不执行测试用例
      下面的二选一, 都有效, 却别如下:
      -DskipTests               不执行测试用例, 但会编译测试用例类
      -Dmaven.test.skip=true    不执行测试用例, 也不编译测试用例类
    -->
    <maven.test.skip>true</maven.test.skip>
    <!-- <skipTests>true</skipTests> -->
  </properties>
```