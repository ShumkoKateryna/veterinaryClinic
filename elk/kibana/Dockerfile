FROM docker.elastic.co/kibana/kibana:8.11.0

# Настройки для Render
ENV ELASTICSEARCH_HOSTS=https://wqq66uwm8s:fdb9a8mad5@onpu-search-4730339165.eu-central-1.bonsaisearch.net:443
ENV SERVER_HOST=0.0.0.0
ENV SERVER_PORT=5601
ENV xpack.security.enabled=false

EXPOSE 5601

CMD ["/usr/share/kibana/bin/kibana"]