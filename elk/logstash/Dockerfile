
FROM docker.elastic.co/logstash/logstash:8.11.0
  
  # Копируем конфигурацию
COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf
COPY logstash.yml /usr/share/logstash/config/logstash.yml
  
  # Настройки для Render
ENV LS_JAVA_OPTS="-Xms256m -Xmx512m"
ENV ELASTICSEARCH_HOST=your-elasticsearch-service-url:9200

EXPOSE 5044 8080 9600

CMD ["/usr/share/logstash/bin/logstash"]