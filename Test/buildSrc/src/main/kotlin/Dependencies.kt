
object Versions {
    const val kotlin = "1.5.10"
    const val coil = "1.2.1"
    const val arouter = "1.5.1"
    const val coreKtx = "1.3.2"
    const val appcompat = "1.3.0"
    const val okhttp = "4.9.0"
    const val retrofit = "2.9.0"
    const val constraintlayout = "2.0.4"
    const val utilcodex = "1.30.5"
    const val mmkv = "1.2.7"
    const val material = "1.3.0"
    const val BRVAH = "3.0.6"
    const val junit = "4.12"
    const val junitExt = "1.1.2"
    const val espressoCore = "3.3.0"
    const val BDCLTA = "3.1.1"
    const val extensions = "2.2.0"
    const val lifecycle = "2.2.0"
    const val coroutines_core = "1.4.3"
    const val viewpager = "1.0.0"
    const val leakcanary = "2.5"
    const val navigation = "2.3.5"
    const val autosize = "1.2.1"
    const val coroutines_android = "1.4.3"
    const val pictureselector = "v2.7.3-rc06"
    const val multidex = "2.0.1"
    const val material_dialogs = "3.3.0"
    const val bottom_tab = "2.4.0"
}

object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.extensions}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
}

object Lib {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
}

object Ktx {
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val lifecycleViewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

object OtherDepend {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    //https://github.com/CymChad/BaseRecyclerViewAdapterHelper  强大而灵活的RecyclerView Adapter
    const val BRVAH = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.BRVAH}"

    //安卓工具类库 https://www.jianshu.com/p/013fe78cc37c
    const val utilCode = "com.blankj:utilcodex:${Versions.utilcodex}"

    //https://github.com/evant/binding-collection-adapter
    const val bdclta =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:${Versions.BDCLTA}"
    const val bdcltaRv =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:${Versions.BDCLTA}"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //图片加载
    const val coil = "io.coil-kt:coil:${Versions.coil}"

    //ARouter
    const val arout = "com.alibaba:arouter-api:${Versions.arouter}"
    const val arout_compiler = "com.alibaba:arouter-compiler:${Versions.arouter}"

    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    const val viewpager = "androidx.viewpager2:viewpager2:${Versions.viewpager}"

    const val mmkv = "com.tencent:mmkv-static:${Versions.mmkv}"

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    const val autosize = "me.jessyan:autosize:${Versions.autosize}"

    const val pictureselector = "io.github.lucksiege:pictureselector:${Versions.pictureselector}"
    const val material_dialogs =  "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"
    const val material_lifecycle = "com.afollestad.material-dialogs:lifecycle:${Versions.material_dialogs}"
    const val bottom_tab = "me.majiajie:pager-bottom-tab-strip:${Versions.bottom_tab}"

}