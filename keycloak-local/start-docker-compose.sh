#!/bin/bash
docker compose down
docker compose run --rm start_dependencies
docker compose up keycloak

#docker compose up -d
#
echo 'Build complete'

#docker compose run --rm start_dependencies
#docker compose up keycloak
