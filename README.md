# 映画情報管理アプリケーション

***

## 概要

### 映画情報を管理する機能をもったAPIの作成

* ### Read処理の実装
* ### Create処理の実装
* ### Update処理の実装
* ### Delete処理の実装(今後)

***

## Read処理の実装

### 1.テーブルからレコードを全件取得できるように実装

映画情報に関するDBから全ての情報を獲得できるように実装。<br>

#### ◽️テーブル(MySQL)

<img width="710" alt="スクリーンショット 2023-10-21 23 15 28" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/99526dbd-2401-4609-bdf4-4a8829abc606">

#### ◽️動作確認<br>

全件獲得できている。
<img width="1680" alt="スクリーンショット 2023-10-22 15 35 28" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/3da36a39-6fad-4aa3-9e8e-8b54e9ba1108">
<img width="1680" alt="スクリーンショット 2023-10-22 15 35 36" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/39f088c1-0bda-4778-862a-a3be2a13dc53">

### 2.ID検索から特定のレコードを取得できるように実装

パスパラメーター部分を`id`に設定して、ID指定する事で該当のレコードを獲得するように実装。<br>
また`id`に存在しない値をリクエストした場合はエラー404で返すように例外処理を実装。

#### ◽️動作確認<br>

MySQLに存在するIDを指定した場合、該当の映画情報を獲得できた。
<img width="1680" alt="スクリーンショット 2023-10-22 15 37 03" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/46c478d2-73ed-49f9-abe0-f613b7251f22">
<img width="1680" alt="スクリーンショット 2023-10-22 15 37 11" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/c532f2f0-1c20-4bff-bdac-2c6261dc1e08">

存在しないIDを指定した場合、エラーコード404と関連メッセージが返ってきた。
<img width="1680" alt="スクリーンショット 2023-10-22 15 37 18" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/80744da4-6d1c-4218-bdcf-ca72ce125c68">
***

## Create処理の実装

### 1.新規の映画情報をテーブルに登録するように実装

映画情報の要素(映画名、公開日、監督名、興行収入)をMySQLのデータベースに登録されるように実装。<br>
また、登録完了した際にステータスコード201を表示させ、`ID`と`検索用URL`を発行する。<br>
適切にリクエストされるようにバリデーションも実装。

#### ◽️動作確認<br>

新規の映画情報がMySQLのデータベースで東麓できた事を確認。<br>
またステータスコード201で表示され、`ID`と`検索用URL`を発行された。<br>
<img width="1680" alt="スクリーンショット 2023-10-22 18 37 06" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/a53fb9d8-968a-443e-9e4f-410ccafb552c">

<img width="1680" alt="スクリーンショット 2023-10-22 18 15 15" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/23f5a26d-5f35-4ba2-808f-90d35608a194">
<img width="710" alt="スクリーンショット 2023-10-22 18 16 22" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/185c15f3-22db-42c6-826b-b138b7406990">

また、`MovieRegistrationForm`にバリデーションを実装。<br>
具体的に下記の通りになる。また動作確認も下記の通りになる。<br>

```java
MovieRegistrationFormクラス
@NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
private String name;
@NotNull //空（null）であるかどうかを検証
private Date releaseDate;
@NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
private String directorName;
@PositiveOrZero //数値が正か 0 であることを検証
private long boxOffice;
```

・nameがnullパターン
<img width="1680" alt="スクリーンショット 2023-10-22 20 25 59" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/264fa1b7-c987-4143-ac4b-f2c40cbf766d">
・releaseDateがnullパターン
<img width="1680" alt="スクリーンショット 2023-10-22 20 26 20" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/f0a0c6ac-a332-42c5-8310-1b765d1816b1">
・directorNameがnullパターン
<img width="1680" alt="スクリーンショット 2023-10-22 20 26 34" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/cf9655bc-0988-4b79-8642-6f5aa8376f87">
・boxOfficeが負の整数パターン
<img width="1680" alt="スクリーンショット 2023-10-22 20 26 53" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/bcabc369-5878-4dda-98c5-5a65e8359b16">

### 2.重複の例外処理の実装

同じ映画の登録を防ぐために、重複例外の処理を実装。
具体的に同じ映画をリクエストしてきた場合はステータスコード409とエラーメッセージを返すように実装。

#### ◽️動作確認<br>

既にデータベースにある`Episode IV – A New Hope`を登録しようとするとステータスコード409とエラーメッセージが返ってくる。
<img width="1680" alt="スクリーンショット 2023-10-22 21 00 51" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/594a8d9c-4942-4e8b-84ea-6c6907550fc4">
***

## Update処理の実装

### 1.映画の更新情報をテーブルのレコードに反映するように実装

更新したい映画を`ID`で指定して、MySQLのデータベースへ反映させるように実装。<br>
うまく実装できた場合はステータスコード200とメッセージを返すようにする。
また適切にリクエストされるようにバリデーションも実装。

```java
MovieUpdateFormクラス
@NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
private String name;
@NotNull //空（null）であるかどうかを検証
private Date releaseDate;
@NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
private String directorName;
@PositiveOrZero //数値が正か 0 であることを検証
private long boxOffice;
```

#### ◽️動作確認<br>

IDで指定した映画がリクエストされた値で更新される事を確認。<br>
また、ステータスコード200とメッセージも確認。<br>



