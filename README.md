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

### アーキテクチャ

#### パッケージ構成

クリーンアーキテクチャを採用

```
todo-api
  ├─src/gen/kotlin //自動生成ディレクトリ
  │  └─chigirh.app.todo.be.todoapi.infra //Infrastracture Layer
  │     ├─dto //MybatisGeneator Model
  │     └─mapper //MybatisGeneator Mapper interface
  └─src/main
    ├─kotlin
    │  └─chigirh.app.todo.be.todoapi
    │    ├─application //Application Layer
    │    │  ├─repository //Repository intercafe
    │    │  ├─service //Service
    │    │  └─usecase //Usecase
    │    ├─domain //domain Layer
    │    │   ├─constant //const
    │    │  ├─exception //Exception
    │    │  └─model  // EntityModel
    │    ├─infra　//Infrastracture Layer
    │    │  ├─dto.result //Mybatis Model
    │    │  ├─mapper //Mybatis Mapper interface
    │    │  └─repository //Repisitory Impl
    │    └─web //Webレイヤー
    │      ├─api //Controller
    │      ├─common //共通処理
    │      ├─core //configu,aop,handler　
    │      └─converter //modelとentityのコンバーター
    └─resources
      ├─kotlin/chigirh/app/todo/be/todoapi/infr/mapper //Mybatis MapperXML
      ├─db/migration //sql ddl
      ├─static //
      └─templates //html template
```
