# android_ChangeTheme

## アプリ動作
起動後、テーマ変更ボタンを押下し一旦アプリを再起動するとThemeが変わります。

## アプリ実装
SharedPreferencesにて、themeの種別を保持し、  
次回起動時のonCreate()にてthemeの値をセットします。

## Themeの継承について
AppCompatActivityを継承している場合は、ThemeにTheme.AppCompat  
またはTheme.AppCompatを含んだThemeを指定しないとErrorとなる。

## アプリで表示されるバー　その種類  
1.ステータスバー  
2.ツールバー（アクションバー）  
3.ナビゲーションバー  

## ステータスバーについて
次の実装にて変更される。（values/styles.xml）  

    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
         <item name="android:statusBarColor">#000000</item>
    </style>

## ツールバー（アクションバー）について  
ActionBarについて。ActionBarはアプリのテーマとして自動で定義されているものになる。  
変更したい場合は、style.xmlのテーマの要素として、定義を追記したり、属性値を変更すればよい。  
らしいので、以下のように実装してみたが正しく反映されなかった・・・。
（values/styles.xml）
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="actionBarStyle">@style/actionBar</item>
    </style>
    
    <style name="actionBar" parent="Widget.AppCompat.ActionBar">
        <!-- タイトルバーの背景色 -->
        <item name="android:background">#00BFFF</item>
    </style>

### ActionBarの動作の記述
自動で生成されるViewですのでActivity内のコールバックメソッドで動作を記述するものがあります。以下は一例。
(MainActivity.java)
    class MainActivity : AppCompatActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //タイトル名の変更
        supportActionBar?.title = "ActionBar"
        //タイトルラベルの左側のナビゲーションアイテムの設置
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //ナビゲーションアイテムを変更
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.sym_def_app_icon)

        //タイトルラベル右側のメニューアイテムの設置
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            getMenuInflater().inflate(R.menu.menu_item, menu);
        }

        //設置したアイテムのリスナーコールバック
        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    }      
    ※onOptionsItemSelected()はすべてのアイテムからのコールバックとなるので、　　
    　渡された引数MenuItem?からどのアイテムが選択されたかを調べて処理する必要があります。
     
ToolBarは各layoutに記述するViewとなります。  
ほぼActionBarと同等な機能を有しますが、CoordinatorLayoutと組み合わせて動的にlayout制御することができたり、  
カスタマイズしやすいコンポーネント(らしい)です。
（values/styles.xml）
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
                <item name="toolbarStyle">@style/MyApp.Toolbar</item>
    </style>
    <style name="MyApp.Toolbar" parent="Widget.AppCompat.Toolbar">
        <item name="android:background">#00BFFF</item>
    </style>
 と、実装したところ、バーの背景色が意図した色に変更されました。
 
 しかし、なぜこうなるかがわからないところ。  
 ToolBarを独自に追加してないため、表示されているのはActionBarと思っていたため  
 見事にハマってしまった。
     
## ナビゲーションバー について
次の実装にて変更される。（values/styles.xml）  

    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
         <item name="android:navigationBarColor">#ffffff</item>
    </style>
    
## その他いろいろ  
### android:windowBackground  
アプリケーションボディの背景色  
### android:textColorPrimary  
アクションバーのタイトル文字色/edittextの文字色  
### android:textColor  
文字色／ボタン文字色  
### colorAccent  
コントロールの基本色（反映されず・・・）   
