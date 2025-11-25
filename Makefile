compose:
	@docker-compose build
	@docker-compose up

tag:
	@docker tag zaikota-backend:latest thedoliver/zaipraxis-backend:1.0.0

dockerpush:
	@docker push thedoliver/zaipraxis-backend:1.0.0