# スキーマ定義
## master
### m_user
| field        | type        | pk  | notnull | description |
| ------------ | ----------- | --- | ------- | ----------- |
| user_id      | varchar(6)  | 1   | Y       | ユーザーID  |
| password     | varchar(30) | -   | Y       | パスワード  |
| user_name    | varchar(20) | -   | Y       | ユーザー名  |
| created_date | timestamp   | -   | N       | 作成日      |
| created_at   | varchar(6)  | -   | N       | 作成者      |
| updated_date | timestamp   | -   | N       | 更新日      |
| updated_at   | varchar(6)  | -   | N       | 更新者      |
| version      | integer     | -   | Y       | バージョン  |

## todo
### t_parent_todo

| field        | type        | pk  | notnull | description |
| ------------ | ----------- | --- | ------- | ----------- |
| p_todo_id    | varchar(36) | 1   | Y       | 親TodoのID  |
| p_todo_name  | varchar(20) | -   | Y       | 親Todo名    |
| is_finished  | boolean     | -   | Y       | 完了フラグ  |
| limit_date   | timestamp   | -   | N       | 期限        |
| finish_date  | timestamp   | -   | N       | 完了日      |
| created_date | timestamp   | -   | N       | 作成日      |
| created_at   | varchar(6)  | -   | N       | 作成者      |
| updated_date | timestamp   | -   | N       | 更新日      |
| updated_at   | varchar(6)  | -   | N       | 更新者    |
| version      | integer     | -   | Y       | バージョン  |

### t_child_todo
| field        | type        | pk  | notnull | description   |
| ------------ | ----------- | --- | ------- | ------------- |
| c_todo_id    | varchar(36) | 1   | Y       | 子TodoのID    |
| c_todo_name  | varchar(20) | -   | Y       | 子Todo名      |
| is_finished  | boolean     | -   | Y       | 完了フラグ    |
| p_todo_id    | varchar(36) | 1   | Y       | 親TodoのID:FK |
| limit_date   | timestamp   | -   | N       | 期限          |
| finish_date  | timestamp   | -   | N       | 完了日        |
| created_date | timestamp   | -   | N       | 作成日        |
| created_at   | varchar(6)  | -   | N       | 作成者        |
| updated_date | timestamp   | -   | N       | 更新日        |
| updated_at   | varchar(6)  | -   | N       | 更新者      |
| version      | integer     | -   | Y       | バージョン    |