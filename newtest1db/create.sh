




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/OICOT1MV2XY353OZL3MAQG
ART_CREATE_PATH=/tmp/applifire/db/OICOT1MV2XY353OZL3MAQG/art/create
AST_CREATE_PATH=/tmp/applifire/db/OICOT1MV2XY353OZL3MAQG/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=Glass4#21
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=newtest1
USER=algo
PASSWORD=algo
PORT=3306
HOST=localhost


echo 'grant previliges to user starts....'
$MYSQL/mysql -h$APPLFIREHOST -u$APPLFIREUSER -e "SOURCE $DB_PATH/grant_previliges.sql";
echo 'grant previliges to user ends....'

echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'

echo 'create db starts....'
$MYSQL/mysql -h$APPLFIREHOST -u$APPLFIREUSER -e "SOURCE $DB_PATH/create_db.sql";
echo 'create db ends....'

echo 'create Tables starts....'

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $DB_PATH/ddl.sql;"

echo 'create Tables ends....'

