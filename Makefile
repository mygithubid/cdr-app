jar:
	mvn package -DskipTests

build:
	docker compose build

up:
	docker compose up
