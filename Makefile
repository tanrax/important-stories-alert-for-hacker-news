.DEFAULT_GOAL := build

build:
	make check
	lein codox 
	lein uberjar

check:
	lein cljfmt check
	clj-kondo --lint src
	lein kibit
