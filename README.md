# acme-backend
# bash
mvn clean package -DskipTests

docker build -t acme-api .
docker run -p 8080:8080 acme-api