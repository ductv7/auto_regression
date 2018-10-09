echo "Start GRID script"

start java -jar selenium-server-standalone-2.52.0.jar -role hub -port 1111

java -jar selenium-server-standalone-2.52.0.jar -role webdriver -hub http://192.168.1.34:1111/grid/register -port 5556 -browser "browserName=chrome, version=ANY, maxInstances=10, platform=WINDOWS" -Dwebdriver.chrome.driver="E:\workspace1\maven\driver\chromedriver.exe" -maxSession 10