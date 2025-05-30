FROM docker.elastic.co/elasticsearch/elasticsearch:8.11.0

# Настройки для Render
ENV discovery.type=single-node
ENV ES_JAVA_OPTS="-Xms256m -Xmx512m"
ENV xpack.security.enabled=false
ENV network.host=0.0.0.0

# Создаем директории
USER root
RUN mkdir -p /usr/share/elasticsearch/data && \
    chown -R elasticsearch:elasticsearch /usr/share/elasticsearch/data

USER elasticsearch

EXPOSE 9200

CMD ["/usr/share/elasticsearch/bin/elasticsearch"]