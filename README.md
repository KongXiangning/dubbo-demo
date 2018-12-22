# springboot2-dubbo-demo
- Desc:<br>
Spring Boot 2 整合 Dubbo 的一个小 Demo;

## 1. 创建一个Maven父工程springboot2-dubbo-demo：
以Intellij IDEA 为例:
File->New->Project-Maven,直接Next,
![创建Maven](/images/1.png)
填写GroupId,ArtifactId;
![组织信息](/images/2.png)
Next->Finish.
## 2. 创建子模块:
在创建的父工程项目名字上右击New->Module-Maven(或者用Spring Initialize创建一个Spring Boot模块,然后再改);
创建三个Module,分别为:

springboot-dubbo-api|springboot-dubbo-provider|springboot-dubbo-consumer
---|---|---
存放API接口、Entity等|Dubbo服务提供者|Dubbo服务消费者、Web等|

## 3. 修改项目pom文件结构;

## 4. 导入Dubbo依赖:
```xml
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>0.2.0</version>
</dependency>
```
这里要注意版本要求:

versions|	Java|	Spring Boot|	Dubbo
---|---|---|---
0.2.0|	1.8+|	2.0.x|	2.6.2 +
0.1.1|	1.7+|	1.5.x|	2.6.2 +

## 5. 定义API接口:
在springboot-dubbo-api模块中新增一个接口:
```java
public interface DemoService {
    /**
     * say Hello.
     * @param name
     * @return
     */
    String sayHello(String name);
}
```
## 6. 提供Provider服务:
1-实现DemoService接口:
```java
@Service // 注意这个是dubbo的service注解,com.alibaba.dubbo.config.annotation.Service
public class DemoServiceImpl implements DemoService {
    /**
     * say Hello.
     * @param name
     * @return
     */
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
```
2-在xxxApplication的启动类上标注@EnableDubbo,启用Dubbo配置
3-配置dubbo:
```properties
server.port=8081
dubbo.application.name=springboot-dubbo-provider
dubbo.application.version=1.0.0
dubbo.registry.address=zookeeper://localhost:2181 #这个为自己的zookeeper地址,默认开放端口2181
dubbo.provider.timeout=1000
```
配置完成,启动服务;
## 7. 编写消费者Consumer:
1-引入API依赖关系:
```xml
<!-- 引入API -->
<dependency>
    <groupId>com.xudc</groupId>
    <artifactId>springboot-dubbo-api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
2-编写DemoController测试:
```java
@RestController
public class DemoController {

    @Reference //用Dubbo提供的注解@Reference引入Spring Bean,com.alibaba.dubbo.config.annotation.Reference
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello(String name){
        return demoService.sayHello(name);
    }
}
```
3-编写properties配置文件:
```properties
server.port=80
dubbo.application.name=springboot-dubbo-consumer
dubbo.application.version=1.0.0
dubbo.registry.address=zookeeper://localhost:2181
dubbo.consumer.timeout=1000
```
4-启动xxxApplication,浏览器输入http://localhost/hello?name=andy测试;

## 项目地址
- GitHub:https://github.com/xudc0521/springboot2-dubbo-demo
- 码云:https://gitee.com/xudc/springboot2-dubbo-demo
---
因个人能力有限,如有不足或错误,欢迎指正~