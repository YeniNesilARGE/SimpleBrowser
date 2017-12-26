rm simple-browser.jar

jar cfm simple-browser.jar yeninesilarge/brow/browser.MF
jar uf  simple-browser.jar yeninesilarge/brow/*.class
jar uf  simple-browser.jar yeninesilarge/application/*.class
jar uf  simple-browser.jar yeninesilarge/util/*.class

jar uf  simple-browser.jar yeninesilarge/image/*.class
jar uf  simple-browser.jar yeninesilarge/image/editor/*.class
jar uf  simple-browser.jar yeninesilarge/image/paint/*.class
jar uf  simple-browser.jar yeninesilarge/calendarapp/*.class
jar uf  simple-browser.jar yeninesilarge/videoplayer/*.class
