d:
cd k2016\workspace\abwesenheit
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1045 -jar target/abwesenheit-hollow-swarm.jar target/abwesenheit.war