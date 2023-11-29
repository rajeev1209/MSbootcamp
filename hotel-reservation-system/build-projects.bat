call mvn -f ./api-gateway/pom.xml clean install -U
echo on
call mvn -f ./customer-service/pom.xml clean install -U
echo on
call mvn -f ./eureka-registry/pom.xml clean install -U
echo on
call mvn -f ./hotel-management-service/pom.xml clean install -U
echo on
call mvn -f ./notification-service/pom.xml clean install -U
echo on
call mvn -f ./payment-service/pom.xml clean install -U
echo on
call mvn -f ./reservation-service/pom.xml clean install -U