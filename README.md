# isahn

## Installation

``` sh
cp config-example.edn config.edn
```

## Usage

``` sh
java -jar isahn-[version]-standalone.jar
```

## Development

### Check format

``` sh
lein cljfmt check
```

### Linter

``` sh
clj-kondo --lint src
```

### Check idiomatic

``` sh
lein auto kibit
```

### Generate doc

``` sh
lein codox 
```
