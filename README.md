# isahn

Important stories alert for Hacker News.

<p align="center">
  <img src="icon.png">
</p>


<h2 align="center">
  <a href="https://t.me/important_stories_alert_hacknews" src="icon.png" alt="Telegram channel">Telegram channel</a>
</h2>

The entire code in this repository is in charge of feeding the Telegram channel. Every hour check for a new story with more than 600 points in Hacker News.

## Installation

``` sh
cp config-example.edn config.edn
```

## Usage

``` sh
cp config-example.cdn config.cdn
java -jar target/uberjar/isahn-[version]-standalone.jar
```

## Build? Ok

Install [Leingengen](https://leiningen.org/) and then...

``` sh
make
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

### All

``` sh
make check
```
