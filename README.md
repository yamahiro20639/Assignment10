# 映画情報管理アプリケーション

***

## 概要

<font size="3">映画情報を管理するアプリ<br>
・映画のタイトル名、公開日、監督名、興行収入をまとめたアプリになります。<br>
・映画情報の検索、登録、修正、削除が可能となっています。<br>
・映画をストックすればするほど、オリジナルの映画ライブラリーが構築されていきます。</font>

## 作成背景

<font size="3">私は毎月1回以上は映画館での鑑賞、自宅では週1本以上は鑑賞するほど映画好きです。<br>
そんな私ですが、自分がどれほど映画に費やして、どれくらい映画愛がすごいのか改めて知れたら面白いのではないかと思いました。<br>
そこでこれまで視聴してきた映画をストックして趣味を可視化してみることが上記を証明できる１つの要素だと思いました。<br>
また定期的に映画好きが集まる会に参加していますが、「おすすめ映画は？」とよく聞かれるため
これまで自分が視聴してきた映画をパッと共有できるものが欲しいと思い、作成に至りました。<br></font>

## 使用技術
- バックエンド
    - Java 20.0.2
    - SpringBoot 3.1.5
    - MyBatis
- フロントエンド(実装予定)
    - React (JavaScript)
    - Chakra UI
- その他
    - MySQL 8.0.34
    - Docker 23.0.5
    - 自動テスト
    - CI (Checkstyle, Codecov, Discordへの通知, 自動テストを実行)

## アプリケーション概略図

![image](https://github.com/yamahiro20639/Movie-Information-Management-API/assets/144509349/a9bb54a5-9fac-4cac-933c-2d9ad458224d)

## アプリケーション機能一覧

| 機能   | 詳細                | URL                 |
|------|-------------------|---------------------|
| 検索   | IDを指定して検索する       | /movies/{id} |
| 新規登録 | ID及び付随する映画を新規登録する | /movies          |
| 修正   | 指定したIDの映画を修正する    | /movies/{id} |
| 削除   | 指定した映画を削除する       | /movies/{id} |

## DB定義
テーブル名：movie_list

| カラム名          | データ型         | キー          | NOT NULL | 備考                |
|---------------|--------------|-------------|----------|-------------------|
| id            | int          | PRIMARY KEY |          | 映画ID,自動生成         |
| name          | VARCHAR(100) |             | NOT NULL | 映画タイトル            |
| release_date  | DATE         |             | NOT NULL | 公開日,yyyy/mm/ddで入力 |
| director_name | VARCHAR(30)  |             | NOT NULL | 監督名               |
| box_office    | BIGINT       |             | NOT NULL | 興行収入              |

## 各種機能の詳細と動作確認
### 検索機能（Read）
<details>
<summary><font size="3">1.DBテーブルから映画を全件取得</font></summary>

映画情報に関するDBから全ての情報を獲得できるように実装。<br>

#### ◽️テーブル(MySQL)

<img width="710" alt="スクリーンショット 2023-10-21 23 15 28" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/99526dbd-2401-4609-bdf4-4a8829abc606">

#### ◽️動作確認

全件獲得できている。
<img width="1680" alt="スクリーンショット 2023-10-22 15 35 28" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/3da36a39-6fad-4aa3-9e8e-8b54e9ba1108">
<img width="1680" alt="スクリーンショット 2023-10-22 15 35 36" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/39f088c1-0bda-4778-862a-a3be2a13dc53">
</details>

<details>
<summary><font size="3">2.ID検索から特定の映画を取得</font></summary>

パスパラメーター部分を`id`に設定して、ID指定する事で該当のレコードを獲得するように実装。<br>

#### ◽️動作確認

MySQLに存在するIDを指定した場合、該当の映画情報を獲得できる事を確認。
<img width="1680" alt="スクリーンショット 2023-10-22 15 37 03" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/46c478d2-73ed-49f9-abe0-f613b7251f22">
<img width="1680" alt="スクリーンショット 2023-10-22 15 37 11" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/c532f2f0-1c20-4bff-bdac-2c6261dc1e08">
</details>
<details>
<summary><font size="3">3.DBに存在しないIDを検索した場合の例外処理</font></summary>

`id`に存在しない値をリクエストした場合はエラー404とエラーメッセージ(`movie information not found`)を返すように例外処理を実装。
#### ◽️動作確認
存在しないIDを指定した場合、エラーコード404とメッセージ(`movie information not found`)が返ってくる事を確認。
<img width="1680" alt="スクリーンショット 2023-10-22 15 37 18" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/80744da4-6d1c-4218-bdcf-ca72ce125c68">
</details>

***


### 登録機能（Create）
<details>
<summary><font size="3">1.映画情報をDBテーブルに新規登録</font></summary>

映画情報の要素(映画名、公開日、監督名、興行収入)をMySQLのデータベースに登録されるように実装。<br>
登録完了した場合はステータスコード201とメッセージ(`Movie registered`)を表示させ、`ID`と`検索用URL`を発行する仕様。<br>
また適切にリクエストされるように`MovieRegistrationFormクラス`にバリデーションも実装。
```java
<MovieRegistrationFormクラス>

@NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
private String name;

@NotNull //空（null）であるかどうかを検証
private Date releaseDate;

@NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
private String directorName;

@PositiveOrZero //数値が正か 0 であることを検証
private long boxOffice;
```

#### ◽️動作確認

新規の映画情報がMySQLのデータベースに登録できた事を確認。<br>
またステータスコード201とメッセージ(`Movie registered`)が表示され、`ID`と`検索用URL`を発行された。<br>
<img width="1680" alt="スクリーンショット 2023-10-22 18 37 06" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/a53fb9d8-968a-443e-9e4f-410ccafb552c">

<img width="1680" alt="スクリーンショット 2023-10-22 18 15 15" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/23f5a26d-5f35-4ba2-808f-90d35608a194">
<img width="710" alt="スクリーンショット 2023-10-22 18 16 22" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/185c15f3-22db-42c6-826b-b138b7406990">

`MovieRegistrationFormクラス`のバリデーションも期待通りの動作になる事を確認<br>
・nameがnullパターン
<img width="1680" alt="スクリーンショット 2023-10-22 20 25 59" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/264fa1b7-c987-4143-ac4b-f2c40cbf766d">
・releaseDateがnullパターン
<img width="1680" alt="スクリーンショット 2023-10-22 20 26 20" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/f0a0c6ac-a332-42c5-8310-1b765d1816b1">
・directorNameがnullパターン
<img width="1680" alt="スクリーンショット 2023-10-22 20 26 34" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/cf9655bc-0988-4b79-8642-6f5aa8376f87">
・boxOfficeが負の整数パターン
<img width="1680" alt="スクリーンショット 2023-10-22 20 26 53" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/bcabc369-5878-4dda-98c5-5a65e8359b16">
</details>

<details>
<summary><font size="3">2.DBに存在する映画を再度登録する際の例外処理</font></summary>

同じ映画の登録を防ぐために、重複例外の処理を実装。<br>
具体的に同じ映画名の登録をリクエストしてきた場合はステータスコード409とエラーメッセージ(`Already registered data`)を返すように実装。<br>
また文字の間隔は関係なく、純粋に映画名が一致していたら例外が適用される。<br>

#### ◽️動作確認

既にデータベースにある`Episode IV – A New Hope`を登録しようとするとステータスコード409とエラーメッセージ(`Already registered data`)が返ってくる事を確認。
<img width="1680" alt="スクリーンショット 2023-10-22 21 00 51" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/594a8d9c-4942-4e8b-84ea-6c6907550fc4">
</details>

***

### 更新機能（Update）
<details>
<summary><font size="3">1.DBに存在する映画の情報更新</font></summary>

更新したい映画を`ID`で指定して、MySQLのデータベースへ反映させるように実装。<br>
うまく実装できた場合はステータスコード200とメッセージ(`Movie updated`)を返すようにする。<br>
また適切にリクエストされるようにバリデーションも実装。<br>

```java
<MovieUpdateFormクラス>

@NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
private String name;

@NotNull //空（null）であるかどうかを検証
private Date releaseDate;

@NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
private String directorName;

@PositiveOrZero //数値が正か 0 であることを検証
private long boxOffice;
```

#### ◽️動作確認

IDで指定した映画がリクエストされた値で更新される事を確認。<br>
また、ステータスコード200とメッセージ(`Movie updated`)も確認。<br>

『更新前』

<img width="715" alt="スクリーンショット 2023-10-23 19 50 45" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/e1b2774f-fcf2-4f2d-b969-9828e1aa33fb"><br>

『更新後』

<img width="711" alt="スクリーンショット 2023-10-23 21 36 00" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/ae43bf0b-4dd2-48aa-bbac-d8b979ffd265"><br>

<img width="1680" alt="スクリーンショット 2023-10-23 21 36 19" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/db93ef2c-2842-446e-be99-132ef608e3d0"><br>
</details>
<details>
<summary><font size="3">2.情報更新のためにデータベースにない映画を指定した場合の例外処理</font></summary>

データベースにない映画情報を`id`で指定し、更新しようとした場合はエラー404とエラーメッセージ(`Movie not found`)を返すように例外処理を実装。

#### ◽️動作確認

データベースにない`ID44番`を指定した場合、エラー404とエラーメッセージ(`Movie not found`)が返ってくる事を確認。
<img width="1680" alt="スクリーンショット 2023-10-25 0 58 46" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/d7d4f113-020e-4182-b10a-4e309d3ffc99">
</details>

***

### 削除機能（Delete）

<details>
<summary><font size="3"> 1.IDを指定して該当する映画を削除</font></summary>

削除 したい映画を`ID`で指定して、MySQLのデータベースへ反映させるように実装。<br>
うまく実装できた場合はステータスコード200とメッセージ(`Movie deleted`)を返すようにする。

#### ◽️動作確認

IDで指定した映画(26,27,28番)が削除される事を確認。<br>
また、ステータスコード200とメッセージ(`Movie deleted`)も確認。<br>
『削除前』

<img width="724" alt="スクリーンショット 2023-10-26 6 42 42" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/f4e07877-4ce9-448b-b0d3-7d0cbfd1d5cb">

『削除後』
<img width="1680" alt="スクリーンショット 2023-10-26 6 43 32" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/1babc23d-ae1f-4e7b-addd-7cec6d73bb32">
<img width="345" alt="スクリーンショット 2023-10-26 6 45 51" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/f32fcc20-9429-4edf-9bf2-30b3009ed36f">
<img width="334" alt="スクリーンショット 2023-10-26 6 46 37" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/53ed5835-7bce-48a2-b664-270397c8d22f">
<img width="708" alt="スクリーンショット 2023-10-26 6 47 18" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/8de20f00-edf4-4ea8-8b47-c6c333073bed">
</details>
<details>
<summary><font size="3">2.DBに存在しないIDを指定して削除する場合の例外処理</font></summary>

データベースにない映画情報を`id`で指定し、削除しようとした場合はエラー404とエラーメッセージ(`Movie not found`)を返すように例外処理を実装。

#### ◽️動作確認

データベースにない`ID100番`を指定した場合、エラー404とエラーメッセージ(`Movie not found`)が返ってくることを確認。
<img width="1680" alt="スクリーンショット 2023-10-26 7 15 18" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/f6b43261-d9c6-49b1-b5e7-1eae758f1328">
</details>

***
## テストコードの実装
<font size="3">下記テストコードを実装しました。またGitHub Actionsを用いて自動化しました。<br></font>
 * <font size="4"> 単体テスト </font> <br>
   
   * <font size="3"> MovieInformationServiceTest</font> 
   * <font size="3"> MovieInformationMapperTest </font>
 * <font size="4"> 結合テスト </font> <br>
   
   * <font size="3">  MovieInformationRestApiIntegrationTest </font>
     <br>
     <br>
   <font size="3">下記の表の通り、実装したテストは全て通りました。</font>
   <img width="528" alt="スクリーンショット 2024-01-03 16 07 06" src="https://github.com/yamahiro20639/Movie-Information-Management-API/assets/144509349/4c9d1820-04db-4bd9-b241-ab973f64d0cf">

## Codecovの実装
<font size="3">アプリケーションが適切にかつ網羅的にテストできるように、Codecovを実装しました。<br>
下記の図からテストコードのカバレッジ率は87.20%となりました。</font>
<img width="1602" alt="スクリーンショット 2024-01-03 16 09 43" src="https://github.com/yamahiro20639/Movie-Information-Management-API/assets/144509349/d49b5643-a70a-4bff-96e2-bc9045219fd0">

***
## 振り返り
<font size="3">今回、初めてのアプリケーション開発を通して、Java言語の知識だけではなく、フレームワークやデータベース、テストなど様々な知識を学ぶことができました。<br>

また、これまでエラーコードに悩んだり、うまく実装できずかなりの時間を費やしてしまった事がありました。その場合、私は本やネット、他の方のコード等を参考に解決の糸口を探すことを心がけました。<br>
今後、現場に入った場合もたくさんの既存コードや技術に積極的に触れて、技術力を高めていきたいと思います。</font>
## 今後の展望
<font size="3">
・テーブルに「ジャンル」、「未視聴or視聴済み」を追加<br>
・映画情報をカラム別に検索できるように実装して、柔軟に情報確認できるように実装<br>
・Reactなどフロント領域を実装<br>
・AWSへのデプロイ</font>



