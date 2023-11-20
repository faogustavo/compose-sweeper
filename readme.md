# Compose Sweeper

A minesweeper game fully written in Kotlin and Compose Multiplatform (HTML).

You can play it by accessing this link: https://faogustavo.github.io/compose-sweeper

## How to run

For develoment, you can use the following command:

```shell
./gradlew jsBrowserDevelopmentRun
```

If you plan to make changes to the project, you can use the continuous build mode:

```shell
./gradlew jsBrowserDevelopmentRun --continuous
```

## How to build

For production, you can use the following command:

```shell
./gradlew jsBrowserProductionWebpack
```

It will result in a `build/dist/js/productionExecutable/` folder with the bundled files.
It's a static website, so you can open the `index.html` file in your browser, or host
it in any web server.


## Tooling

- Kotlin 1.9.20
- Compose Multiplatform for HTML 1.5.10
- 98.css for styles
  - https://jdan.github.io/98.css/
- Win98Icons
  - https://win98icons.alexmeub.com