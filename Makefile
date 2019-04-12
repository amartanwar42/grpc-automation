createProtoFolder:
#	mkdir -p src/main/proto && cp $(protoPath) src/main/proto
	cp -r $(protoPath) src/main

buildProject:
	mvn clean install