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
                ReportingService       <---> |              1. Common Entry Point
                                                            2. Proxy and Data Composition Service as per Aggregator Patterns
                                                            3. Authentication and Authorization Service

            Database Per Service  
            ------------------------------------------------------------

            USDb <-->    UserManagementService  <---> |
            TSDb <-->    TransactionService     <---> |  <-->  EdgeService/GateWayService  <--> Client
                         ReportingService       <---> |              
                


            

    