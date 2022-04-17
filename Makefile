APP_DIR=./app
default: deploy
API_ENDPOINT=https://api.coindirect.com/api/country

require:
	@echo "Checking required commands exists or not ..."
	@which mvn >/dev/null  2>&1 || (echo "Error: maven is required"; exit 1)
	@which npm >/dev/null  2>&1 || (echo "Error: npm is required"; exit 1)
	@which docker >/dev/null  2>&1 || (echo "Error: docker is required"; exit 1)
	@which docker-compose >/dev/null  2>&1 || (echo "Error: docker-compse is required"; exit 1)

install-service: require pom.xml
	mvn clean install

install-app: require app/package.json
	cd ${APP_DIR} && npm install

install: install-service install-app

test-service:
	mvn test
test-app:
	cd $(APP_DIR) && npm run test
test: test-service test-app

start-service: install-service
	 API_ENDPOINT=$(API_ENDPOINT) java -jar  target/reqdoc-1.0-SNAPSHOT-jar-with-dependencies.jar

start-app: app-install
	cd $(APP_DIR) && npm run start

start: start-service start-app

deploy-service: .env docker-compose.yaml Dockerfile  require
	docker-compose up --build reqdoc

deploy-app: .env docker-compose.yaml  app/Dockerfile require deploy-service
	docker-compose up --build app

deploy: .env docker-compose.yaml Dockerfile app/Dockerfile require
	docker-compose up --build

clean:
	mvn clean && npm run clean

.PHONY: install test reqdoc require clean test-service install-app deploy deploy-service deploy-app