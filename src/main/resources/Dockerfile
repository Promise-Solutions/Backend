FROM mysql:latest

WORKDIR /app

ADD . /app

ENV MYSQL_ROOT_PASSWORD=${DB_PASSWORD}

COPY ./schema.sql /docker-entrypoint-initdb.d/

EXPOSE 3307

CMD ["mysqld"]