echo "Start GRID script"

start java -jar selenium-server-standalone-2.52.0.jar -role hub -port 1111

java -jar selenium-server-standalone-2.52.0.jar -role webdriver -hub http://localhost:1111/grid/register -port 5556 -browser "browserName=firefox, version=ANY, maxInstances=10, platform=WINDOWS"