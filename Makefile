.DEFAULT_GOAL := run-dist

run-dist:
	make -C app run-dist

build:
	make -C app build

test:
	make -C app test

lint:
	make -C app lint

report:
	make -C app report

.PHONY: build