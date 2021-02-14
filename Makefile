quarkus-dev:
	mvn compile quarkus:dev

docker-build:
	mvn package -DskipTests -Dquarkus.container-image.build=true

docker-run:
	docker run -it --rm tadayosi/camel-quarkus-hello:1.0.0-SNAPSHOT
