




echo $PATH
DB_PATH=/tmp/applifire/db/OICOT1MV2XY353OZL3MAQG
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'