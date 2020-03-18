# android_ChangeTheme

## Themeの継承について
AppCompatActivityを継承している場合は、ThemeにTheme.AppCompat  
またはTheme.AppCompatを含んだThemeを指定しないとErrorとなる。

## アプリで表示されるバー　その種類  
1.ステータスバー  
2.ツールバー（アクションバー）  
3.ナビゲーションバー  

## ステータスバーについて

## ツールバー（アクションバー）について  
ActionBarについて。ActionBarはアプリのテーマとして自動で定義されているものになる。  
変更したい場合は、style.xmlのテーマの要素として、定義を追記したり、属性値を変更すればよい。  
らしいので、以下のように実装してみたが正しく反映されなかった・・・。

    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="actionBarStyle">@style/actionBar</item>
    </style>
    
    <style name="actionBar" parent="Widget.AppCompat.ActionBar">
        <!-- タイトルバーの背景色 -->
        <item name="android:background">#00BFFF</item>
    </style>

### ActionBarの動作の記述
自動で生成されるViewですのでActivity内のコールバックメソッドで動作を記述するものがあります。以下は一例。

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
