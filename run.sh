clear

which curl &> /dev/null

if [[ $? = 0 ]]; then
  curl https://knossys.com/banner.txt
fi

echo "Checks and tests ..."

if [[ ! -f .env ]]
then
    echo ".env does not exist on your filesystem."
    exit 1
fi

echo "Run ..."

export dbHost="127.0.0.1"
export dbPort="43306"
export dbName="knossys"
export dbUsername="knossys"
export dbTable="portal"
export dbPassword="4570WK821X6OiyT508srN09wV"

java -cp "./target/KNode-jar-with-dependencies.jar" com.knossys.rnd.net.KNode -r "node"
