FROM docker.elastic.co/logstash/logstash:7.10.2

COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf

# Включаем мониторинг API
ENV LS_JAVA_OPTS="-Xms256m -Xmx512m"
ENV SERVER_HOST=0.0.0.0
ENV SERVER_PORT=5000

EXPOSE 5000 9600

CMD ["/usr/share/logstash/bin/logstash"]