FROM docker.elastic.co/logstash/logstash:7.10.2



COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf

# Оптимізація пам’яті
ENV LS_JAVA_OPTS="-Xms256m -Xmx512m"

# Відкриті порти
EXPOSE 5044 8080 514 5000 9600

# Запуск Logstash
CMD ["/usr/share/logstash/bin/logstash"]
