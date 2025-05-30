FROM docker.elastic.co/logstash/logstash:7.10.2

COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf

# Включаем мониторинг API
ENV LS_JAVA_OPTS="-Xms256m -Xmx512m"
ENV http.host="0.0.0.0"
ENV http.port=9600

# Порт 8080 будет основным для Render
EXPOSE 8080
EXPOSE 5000 9600

CMD ["/usr/share/logstash/bin/logstash"]