java -Djava.awt.headless=true -Dfile.encoding=UTF8 -XX:MaxPermSize=512m -Xss4M -Xmx1512M -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -server -noverify -javaagent:/Applications/ZeroTurnaround/JRebel/jrebel.jar -Drebel.lift_plugin=true -jar `dirname $0`/sbt-launch.jar "$@"