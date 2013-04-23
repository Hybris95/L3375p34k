@echo off

javac -cp "./class;./jars/craftbukkit.jar" -d "./class" ./src/com/hybris/bukkit/l3375p34k/*.java

cd ./class

jar cvf "L3375p34k.jar" ./plugin.yml ./com/

move /Y L3375p34k.jar ../jars/

pause
