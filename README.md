# TodoAPI

試験未実施。  
疎通のみの確認

### 起動方法

#### Docker起動

> cd docker  
> docker-compose up-d

#### DB初期化

> ./gradlew flywayMigrate

#### OpenAPI自動生成

> ./gradlew openApiGenerate

#### 起動

> ./gradlew bootRun

### API仕様書

> http://localhost:8080/swagger-ui.html

### クラス構成

- chigirh.app.todo.be.todoapi.web:web layer
- chigirh.app.todo.be.todoapi.applicationapplication layer
- chigirh.app.todo.be.todoapi.domain:domain layer
- chigirh.app.todo.be.todoapi.infra:infrastracture layer

#### 以下自動生成(コミット禁止)

- 起動クラス - chigirh.app.todo.be.todoapi.Application
- OpenAPI自動生成 - chigirh.app.todo.be.todoapi.oas3
- MybatisGenerator自動生成 - /todo-api/src/gen
