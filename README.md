ICGC DCC Data Submission Application
===

Instructions
---

Clone the repository

	git clone https://github.com/icgc-dcc/data-submission.git

Start the server

	cd data-submission/server
	mvn exec:java

Start the client (in another console)

	cd data-submission/client
	mvn jetty:run

Point your browser to http://localhost:8080/
