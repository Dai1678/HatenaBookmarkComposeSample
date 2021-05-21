# Android App Compose Template

## SetUp

### パッケージ名の変更

まず初めに必ず ① `app/build.gradle` と ② `app/src/main/AndroidManifest.xml` にある `dev.dai.sample` を適切なパッケージ名に変更してください。

① app/build.gradle

```
  defaultConfig {
    applicationId "dev.dai.sample" // ← 修正
    ...
  }
```

② app/src/main/AndroidManifest.xml

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
  package="dev.dai.sample"> //  ← 修正
```

### Github Actionsのセットアップ

それぞれ次の環境変数をGithubに設定しましょう。

| 環境変数名 | 説明 | 取得方法 |
| --- | --- | --- |
| DEPLOY_GATE_API_KEY | DeployGateで使用するToken | [DeployGate設定画面 API key](https://deploygate.com/settings) |
| DEPLOY_GATE_USER_NAME | デプロイ先のユーザー名 | [DeployGate設定画面 プロフィール](https://deploygate.com/settings) |

### Github Pages のセットアップ

* Settings -> Github Pages で Branch を `main` にして ディレクトリを `docs` を選べば、ドキュメントが公開されます。

## Special Thanks
* [kgmyshin android-app-template](https://github.com/kgmyshin/android-app-template)
