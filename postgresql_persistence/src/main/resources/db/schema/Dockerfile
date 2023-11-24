FROM postgres:13.2-alpine

ENV POSTGRES_USERNAME="gubee"
ENV POSTGRES_PASSWORD="gubee"

EXPOSE 5432
COPY init_schema.sql /docker-entrypoint-initdb.d/init_schema.sql:z