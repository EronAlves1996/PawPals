FROM mysql:latest
ENV MYSQL_PASSWORD pawpals
ENV MYSQL_ROOT_PASSWORD pawpals
ENV MYSQL_USER pawpals
ENV MYSQL_DATABASE pawpals
ADD migration.sql /docker-entrypoint-initdb.d
EXPOSE 3306  