
FROM docker.elastic.co/logstash/logstash:8.11.0
  
  # Копируем конфигурацию
COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf
  
  # Настройки для Render
ENV LS_JAVA_OPTS="-Xms256m -Xmx512m"
ENV ELASTICSEARCH_HOST=https://wqq66uwm8s:fdb9a8mad5@onpu-search-4730339165.eu-central-1.bonsaisearch.net:443

EXPOSE 5044 8080 9600

CMD ["/usr/share/logstash/bin/logstash"]