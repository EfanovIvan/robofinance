#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB_NAME" <<-EOSQL
    CREATE USER postgres;
    CREATE DATABASE robofinance;
    GRANT ALL PRIVILEGES ON DATABASE robofinance TO postgres;
EOSQL