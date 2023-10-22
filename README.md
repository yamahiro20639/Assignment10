# 映画情報管理アプリケーション

***

## 概要

### 映画情報を管理する機能をもったAPIの作成

* ### Read処理の実装
* ### Create処理の実装(今後)
* ### Update処理の実装(今後)
* ### Delete処理の実装(今後)

***

## Read処理の実装

### 1.テーブルからレコードを全件取得できるように実装

映画情報に関するDBから全ての情報を獲得できるように実装。<br>

#### ◽️テーブル(MySQL)

<img width="818" alt="スクリーンショット 2023-10-21 14 11 19" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/281cc1ef-ad55-49e5-b58f-deae930a9112">

#### ◽️動作確認<br>

全件獲得できている。
<img width="1680" alt="スクリーンショット 2023-10-21 14 14 26" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/3f844818-f3d4-4cec-a0cc-9a3ff4c4b56a">
<img width="1680" alt="スクリーンショット 2023-10-21 14 14 35" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/b03cd404-9703-4852-a57c-bf07e45db561">
<img width="1680" alt="スクリーンショット 2023-10-21 14 14 39" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/c1dcd5f5-9ed1-4897-bd5d-d084f41d46f6">

### 2.ID検索から特定のレコードを取得できるように実装

パスパラメーター部分を`movie_id`に設定して、ID指定する事で該当のレコードを獲得するように実装。<br>
また`movie_id`に存在しない値をリクエストした場合はエラー404で返すように例外処理を実装。

#### ◽️動作確認<br>

MySQLに存在するIDを指定した場合、該当の映画情報を獲得できた。
<img width="1680" alt="スクリーンショット 2023-10-21 14 25 25" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/d441838a-9a13-4a7e-93f3-6f1e0e69352a">
<img width="1680" alt="スクリーンショット 2023-10-21 14 25 33" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/153dc001-7e47-4824-9f8b-b1961a6956cd">

存在しないIDを指定した場合、エラーコード404と関連メッセージが返ってきた。
<img width="1680" alt="スクリーンショット 2023-10-21 14 25 45" src="https://github.com/yamahiro20639/Assignment10/assets/144509349/77759594-7262-45b2-98ed-11647f0feb39">
