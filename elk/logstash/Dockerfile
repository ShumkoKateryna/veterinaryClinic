FROM docker.elastic.co/logstash/logstash:7.10.2

COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf

# Включаем мониторинг API
ENV LS_JAVA_OPTS="-Xms256m -Xmx512m"

# Настройки для Render
ENV ELASTICSEARCH_HOSTS=https://wqq66uwm8s:fdb9a8mad5@onpu-search-4730339165.eu-central-1.bonsaisearch.net:443
ENV SERVER_HOST=0.0.0.0
ENV SERVER_PORT=5000
ENV xpack.security.enabled=false

EXPOSE 5000

CMD ["/usr/share/logstash/bin/logstash"]