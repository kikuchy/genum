# Genum - Generate Enum from YAML

Genum is the code generator that generate Java enum class file from YAML array.

You can share the definition between server sile and client application!

For example, here is a array written in YAML format.

```yaml
- Tokyo
- NewYork
- London
- Beijing
- Paris
- Roma
```

Genum turns it into Enum java file.

```java
package com.example;

public enum City {
  TOKYO,

  NEW_YORK,

  LONDON,

  BEIJING,

  PARIS,

  ROMA
}
```

## Usage


### CLI tool

Download [latest cli tool](https://github.com/kikuchy/genum/releases) and unzip it.

```
$ ./bin/genus-cli -c City -p com.example -s cities.yml -o your/project/src/main/java
```
Run without any option to show command line option discription.



### Library

Download [latest library](https://github.com/kikuchy/genum/releases) and unzip it.

_Maybe, Genum will be registed to Maven Central._


## It's inspired from...

- [jsonschema2pojo](http://www.jsonschema2pojo.org)


## License

```
Copyright 2015 kikuchy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```