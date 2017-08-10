FROM mysql

COPY dump.sql /tmp/dump.sql
RUN /bin/bash -c "/usr/bin/mysqld_safe &" && \
      sleep 5 && \
      mysql -u root -e "CREATE DATABASE ProjectFarm" && \
      mysql -u root ProjectFarm < /tmp/dump.sql
