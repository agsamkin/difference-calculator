### JSON and YAML differencing calculator

#### CI:

[![my-workflow](https://github.com/agsamkin/java-project-71/actions/workflows/my-workflow.yml/badge.svg)](https://github.com/agsamkin/java-project-71/actions/workflows/my-workflow.yml)

#### Codeclimate:

[![Maintainability](https://api.codeclimate.com/v1/badges/fec0680afba7d70590a0/maintainability)](https://codeclimate.com/github/agsamkin/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/fec0680afba7d70590a0/test_coverage)](https://codeclimate.com/github/agsamkin/java-project-71/test_coverage)

#### Usage:

```
Usage: gendiff [-hV] [-f=format] filepath1 filepath2
Compares two configuration files and shows a difference.
      filepath1         path to first file
      filepath2         path to second file
  -f, --format=format   output format [default: stylish]
  -h, --help            Show this help message and exit.
  -V, --version         Print version information and exit.

```

#### Output formats:

* stylish (default)
* plain
* json

#### Demo:

[![asciicast](https://asciinema.org/a/X40fHIDxK6CJ6WREhuBYV2Usi.svg)](https://asciinema.org/a/X40fHIDxK6CJ6WREhuBYV2Usi)