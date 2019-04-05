# tomcat-java8
nodejs x64
git config --local user.email harvyso@yahoo.com
git config --local user.name harvyso

mvn archetype:generate -DgroupId={project-packaging} 
   -DartifactId={project-name} 
   -DarchetypeArtifactId=maven-archetype-quickstart
   
   
# to check code with default style then see target/site/checkstyle.html
mvn -Dhttps.protocols=TLSv1.2 checkstyle:checkstyle
