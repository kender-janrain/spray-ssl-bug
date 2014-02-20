# Spray SSL Timeout Bug #

This project reproduces a bug where spray gets stuck in a "loop" trying to close
an SSL connection after a request timeout.

## Scenario ##

The spray client makes an https call to an SSL server which is not responding.

## Steps ##

1. Install haproxy-1.5 (1.5 is important, since this is the version with SSL support)
2. run: haproxy -f haproxy.conf
3. ^Z the haproxy process
4. run: sbt run

## Observations ##
- after 5 seconds (see application.conf...) the log will begin to emit:

        GET request to '/' timed out after 5 seconds, closing connection


- after 5 more seconds (see askTimeout in SpraySslBug.scala), the ask to io will timeout.
