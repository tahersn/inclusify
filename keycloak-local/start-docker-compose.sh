#!/bin/bash
docker compose down
docker compose run --rm start_dependencies
docker compose up keycloak


echo 'Build complete'
