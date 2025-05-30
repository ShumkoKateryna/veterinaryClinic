FROM docker.elastic.co/logstash/logstash:7.10.2

COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf

ENV LS_JAVA_OPTS="-Xms256m -Xmx512m"

# Порт 5000 для HTTP health checks от Render
EXPOSE 5000
# Порт 5044 для TCP логов от приложения
EXPOSE 5044

CMD ["/usr/share/logstash/bin/logstash"]