Java Microservices on Spring Boot and Spring Cloud 
------------------------------------------------------------------

    Pre-Requisite
    -----------------------------
        Java 8 Features
        Spring 5
            Spring Core 
            Spring Context
            Spring Web 
            Spring Rest
            Spring Data
            Spring Boot 

    Lab Setup
    --------------------------------
        Java 8
        STS 4 or above 
        Maven
        MySQL
        Postman

    CaseStudy - BudgetTracker
    ----------------------------------------------------------------------

        1. App is going to be used by multiple users who has to register themselves.
        2. Once a user loging in
            a. must be able to record a transaction (CREDIT/DEBIT).
            b. must be able to generate the statements periodically.
            c. each month is a called a 'book' havign a opening and closing bal.
            d. the clsoginb bal of amont proceeds as opening bal to the next.


            Decompositon By Doamin (Bussienss Capability)
            --------------------------------------------------
                UserManagementService
                TransactionService
                ReportingService

            Decompositon By SubDoamin (DDD)
            -------------------------------------
                UserManagementService
                        UserEntity
                            userId,fullName,email,mobile,password

                TransactionService
                        UserEntity
                            userId
                            currentBalance

                        TransactionEntity
                            txnId,amount,txnType,dateTimeOfTxn,user

                        UserModel
                            userId,fullName,email,mobile,password,currentBalance

                ReportingService
                        MonthlyStatement
                            month,year,totalCredit,totalDebit,monthlyBalance

                        TransactionModel
                            txnId,amount,txnType,dateTimeOfTxn

            Integration Pattern - API Gateway Pattern, Aggregator Patterns
            ------------------------------------------------------------
            
                UserManagementService  <---> |
                TransactionService     <---> |  <----->  EdgeService/GateWayService  <-----> Client
                ReportingService       <---> |           (Zuul / Spring Cloud Gateway)   
                                                            1. Common Entry Point
                                                            2. Proxy and Data Composition Service as per Aggregator Patterns
                                                            3. Authentication and Authorization Service

            Database Per Service  
            ------------------------------------------------------------

            USDb <-->    UserManagementService  <---> |
            TSDb <-->    TransactionService     <---> |  <-->  EdgeService/GateWayService  <--> Client
                         ReportingService       <---> |       (Zuul / Spring Cloud Gateway)   

            Observablity Pattern - Distributed Tracing
            -----------------------------------------------------------------
                                                            
                                             ↑-----------------> DistributedTracingService (Zipkin)
                                             ↑   
            USDb <-->    UserManagementService  <---> |
                                    (Sluath)
            TSDb <-->    TransactionService     <---> |  <-->  EdgeService/GateWayService  <--> Client
                                    (Sluath)                      (Zuul / Spring Cloud Gateway)   
                         ReportingService       <---> |     
                                    (Sluath)
                
            Cross Cutting Concerns -- External Configaration
            -------------------------------------------------------------

            gitRepo 
                user-management-service.properties
                transaction-service.properties
                reporting-service.properties
              ↓
              ↓---->  External Config Service 
                    (Spring Cloud Config Server)
                            ↓
                            ↓                ↑-----------------> DistributedTracingService (Zipkin)
                            ↓                ↑   
            USDb <-->    UserManagementService  <---> |
                                    (Sluath)
            TSDb <-->    TransactionService     <---> |  <-->  EdgeService/GateWayService  <--> Client
                                    (Sluath)                      (Zuul / Spring Cloud Gateway)   
                         ReportingService       <---> |     
                                    (Sluath)

            Cross Cutting Concerns -- Discovery Service
            -------------------------------------------------------------

            gitRepo 
                user-management-service.properties
                transaction-service.properties
                reporting-service.properties
              ↓
              ----->  External Config Service 
                    (Spring Cloud Config Server)
                            ↓        ----register the Service instance url with----->   Discovery Service 
                            ↓        ↑                                                   (Eureka Discovery Server)
                            ↓        ↑            ------> DistributedTracingService (Zipkin)     ↓ 
                            ↓        ↑            ↑                                              ↓ 
                            ↓        ↑            ↑                                              ↓ 
            USDb <-->    UserManagementService (Sluath) <---> |                                  ↓ 
            TSDb <-->    TransactionService    (Sluath) <---> |  <----->  EdgeService/GateWayService  <--> Client  
                         ReportingService      (Sluath) <---> |           (Zuul / Spring Cloud Gateway) 


            Cross Cutting Concerns -- Circuit Breaking
            -------------------------------------------------------------

            gitRepo 
                user-management-service.properties
                transaction-service.properties
                reporting-service.properties
              ↓
              ----->  External Config Service 
                    (Spring Cloud Config Server)
                            ↓        ----register the Service instance url with----->   Discovery Service 
                            ↓        ↑                                                   (Eureka Discovery Server)
                            ↓        ↑            ------> DistributedTracingService (Zipkin)     ↓ 
                            ↓        ↑            ↑                                              ↓ 
                            ↓        ↑            ↑                                              ↓ 
            USDb <-->    UserManagementService (Sluath) <---> |                                  ↓ 
            TSDb <-->    TransactionService    (Sluath) <---> |  <----->  EdgeService/GateWayService  <--> Client  
                    (fall back mechanisim using Netflix Hystrix / Resiliance4j)  (Zuul / Spring Cloud Gateway) 
                         ReportingService      (Sluath) <---> |           
                    (fall back mechanisim using Netflix Hystrix / Resiliance4j)                                
                                    
            Cross Cutting Concerns -- Load Balancing
            -------------------------------------------------------------

            gitRepo 
                user-management-service.properties
                transaction-service.properties
                reporting-service.properties
              ↓
              ----->  External Config Service 
                    (Spring Cloud Config Server)
                            ↓        ----register the Service instance url with----->   Discovery Service 
                            ↓        ↑                                                   (Eureka Discovery Server)
                            ↓        ↑            ------> DistributedTracingService (Zipkin)     ↓ 
                            ↓        ↑            ↑                                              ↓ 
                            ↓        ↑            ↑                                              ↓ 
            USDb <-->    UserManagementService (Sluath) <---> |                                  ↓ 
            TSDb <-->    TransactionService    (Sluath) <---> |  <----->  EdgeService/GateWayService  <--> Client  
                    (fall back mechanisim using Netflix Hystrix / Resiliance4j)  (Zuul / Spring Cloud Gateway) 
                    (Ribbon / Spring Cloud LoadBalancer + Feign Cleint)
                         ReportingService      (Sluath) <---> |                                    
                    (fall back mechanisim using Netflix Hystrix / Resiliance4j)                                
                    (Ribbon / Spring Cloud LoadBalancer + Feign Cleint)

    CaseStudy - BudgetTracker - Implmwentation Step1
    ----------------------------------------------------------------------

     1. in.budgettracker
        user-management-service
            spring boot web
            spring data jpa
            spring boot devtools
            open Feign
            MySQL Driver   

            Method      EndPoint            ReqBody         RespBody
            GET         /users              NA              a json array of users, HttpStatus.OK
            GET         /users/1            NA              a json of user with userId=1, HttpStatus.OK
            GET         /users/a@g.com      NA              a json of user with emailId=a@g.com, HttpStatus.OK
            POST        /users              user json       add the user and return the user json, HttpStatus.CREATED
            PUT         /users              user json       update the user and return the user json,
                                                            HttpStatus.ACCEPTED
            DELETE      /users/1            NA              delete the user with userId=1, HttpStatus.NO_CONTENT
    
     2. in.budgettracker
        txn-management-service
            spring boot web
            spring data jpa
            spring boot devtools
            open Feign
            MySQL Driver   

            Method      EndPoint                     ReqBody         RespBody
            GET         /users/1                     NA              a json of user with userId=1, HttpStatus.OK
            GET         /users/1/txns                NA              a json array of all transaction of that user
            GET         /users/1/txns/MAY/2021       NA              a json array of all transaction of that user 
                                                                     in  that month
            POST        /users                       user json       add the user and return the user json,
                                                                     HttpStatus.CREATED
            GET         /txns/1                      NA              a json of txn with txnId=1, HttpStatus.OK
            DELETE      /txns/1                      NA              delete txn with txnId=1, HttpStatus.NO_CONTENT
            POST        /txns                        txn json        add the txn and return the user json,
                                                                     HttpStatus.CREATED   
     3. in.budgettracker
        reporting-service
            spring boot web
            spring boot devtools
            open Feign

            Method      EndPoint                    RespBody
            GET         /statement/1/MAY/2021       a json monthlyStatement the  user#1
                                                    of  that month, HttpStatus.OK
    
    CaseStudy - BudgetTracker - Implmwentation Step2 - Discovery Service + Client Side Load Balancing
    --------------------------------------------------------------------------------------------------

    A. in.budgettracket
        bt-discovery-service
            spring boot devtools
            spring-cloud-starter-netflix-eureka-server

            @EnableEurekaServer      along with @SpringBootAppliction 
            
            spring.application.name=bt-discovery-service
            server.port=9000
            eureka.instance.hostname= localhost
            eureka.client.registerWithEureka=false
            eureka.client.fetchRegistry=false
            eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
            eureka.server.waitTimeInMsWhenSyncEmpty=0
            eureka.server.response-cache-update-interval-ms=5000

     1. in.budgettracker
        user-management-service
            spring boot web
            spring data jpa
            spring boot devtools
            open Feign
            MySQL Driver   
            spring-cloud-starter-netflix-eureka-client
            spring-cloud-starter-loadbalancer

            @EnableDiscoveryClient      along with @SpringBootAppliction 
            @LoadBalancerClient         along with @FeignClient

            spring.application.name=user-management-service
            #enable random port
            server.port=0

            spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
            spring.datasource.username=root
            spring.datasource.password=root
            spring.datasource.url=jdbc:mysql://localhost:3306/btumsDB?createDatabaseIfNotExist=true
            spring.jpa.hibernate.ddl-auto=update

            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
            eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
            eureka.client.registryFetchIntervalSeconds=5
            eureka.instance.leaseRenewalIntervalInSeconds=5
            eureka.instance.leaseExpirationDurationInSeconds=5

            spring.cloud.loadbalancer.ribbon.enabled=false

     2. in.budgettracker
        txn-management-service
            spring boot web
            spring data jpa
            spring boot devtools
            open Feign
            MySQL Driver   
            spring-cloud-starter-netflix-eureka-client
            spring-cloud-starter-loadbalancer

            @EnableDiscoveryClient      along with @SpringBootAppliction 
            @LoadBalancerClient         along with @FeignClient

                spring.application.name=txn-management-service
                #enable random port
                server.port=0

                spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
                spring.datasource.username=root
                spring.datasource.password=root
                spring.datasource.url=jdbc:mysql://localhost:3306/bttmsDB?createDatabaseIfNotExist=true
                spring.jpa.hibernate.ddl-auto=update

                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
                eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
                eureka.client.registryFetchIntervalSeconds=5
                eureka.instance.leaseRenewalIntervalInSeconds=5
                eureka.instance.leaseExpirationDurationInSeconds=5

                spring.cloud.loadbalancer.ribbon.enabled=false

     3. in.budgettracker
        reporting-service
            spring boot web
            spring boot devtools
            open Feign
            spring-cloud-starter-netflix-eureka-client
            spring-cloud-starter-loadbalancer

            @EnableDiscoveryClient      along with @SpringBootAppliction 
            @LoadBalancerClient         along with @FeignClient

                spring.application.name=reporting-service
                #enable random port
                server.port=0


                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
                eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
                eureka.client.registryFetchIntervalSeconds=5
                eureka.instance.leaseRenewalIntervalInSeconds=5
                eureka.instance.leaseExpirationDurationInSeconds=5

                spring.cloud.loadbalancer.ribbon.enabled=false