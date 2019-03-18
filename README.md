### REQUIREMENTS
Using best practice coding methods, build a Java console application that scrapes the Sainsbury’s grocery site’s - Berries, Cherries, Currants page and returns a JSON array of all the products on the page.

#### PACKAGE AND RUN APPLICATION LOCALLY

## Clone repository locally :
# git clone git@github.com:henryWorld/webScraper.git

## Go to project directory on your command line
# package it
mvn package
# Run it 
java -jar  target/WebScraper-1.0-SNAPSHOT.jar

# Run it with an argument
java -jar  target/WebScraper-1.0-SNAPSHOT.jar "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html"

