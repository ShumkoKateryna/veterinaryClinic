FROM docker.elastic.co/logstash/logstash:7.10.2

COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf

ENV LS_JAVA_OPTS="-Xms256m -Xmx512m"

EXPOSE 5000

CMD ["/usr/share/logstash/bin/logstash"]