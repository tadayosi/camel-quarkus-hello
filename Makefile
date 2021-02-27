ORG = tadayosi
APP = camel-quarkus-hello
VERSION = 1.0.0-SNAPSHOT

quarkus-dev:
	mvn compile quarkus:dev

docker-build:
	mvn package -DskipTests -Dquarkus.container-image.build=true

docker-run:
	docker run -it --rm $(ORG)/$(APP):$(VERSION)

docker-push:
	mvn package -DskipTests -Dquarkus.container-image.push=true

k8s-run:
	kubectl run $(APP) --labels="app=camel" --image=$(ORG)/$(APP):$(VERSION)

k8s-deploy:
	mvn package -DskipTests -Dquarkus.kubernetes.deploy=true -Dquarkus.kubernetes-client.trust-certs=true

itests:
	mvn verify -P itests
