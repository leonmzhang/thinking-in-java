#!/usr/bin/env bash

bin=$(dirname $0)
bin=$(cd ${bin}; pwd)

CLASS=$1
shift

java -classpath ${bin}/target/leon-practice-0.1.0-SNAPSHOT.jar ${CLASS}
