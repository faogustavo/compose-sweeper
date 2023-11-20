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

## License

    Copyright 2023 Gustavo Fão Valvassori
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
           http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.