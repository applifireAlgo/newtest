




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


