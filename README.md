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
                        UserTSModel
                            userId

                        TransactionEntity
                            txnId,userId,amount,txnType,dateTimeOfTxn

                        MonthlySummaryEntity
                            monthId,opBal,clBal,totalCredit,totalDebit

                ReportingService
                        UserRSModel
                            userId,fullName,email,mobile
                        
                        MonthlySummaryModel
                            monthId,opBal,clBal,totalCredit,totalDebit

                        TransactionModel
                            txnId,userId,amount,txnType,dateTimeOfTxn

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
                    (Load Balancing Ribbon / Fiegn Client)
                         ReportingService      (Sluath) <---> |                                    
                    (fall back mechanisim using Netflix Hystrix / Resiliance4j)                                
                    (Load Balancing Ribbon / Fiegn Client)
                                    





            

    