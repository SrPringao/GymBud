[1mdiff --git a/.idea/misc.xml b/.idea/misc.xml[m
[1mindex f707e28..0e1bfc5 100644[m
[1m--- a/.idea/misc.xml[m
[1m+++ b/.idea/misc.xml[m
[36m@@ -28,7 +28,7 @@[m
         <entry key="..\:/Users/franc/GymBud/app/src/main/res/layout/activity_fragment_container.xml" value="0.3009833262077811" />[m
         <entry key="..\:/Users/franc/GymBud/app/src/main/res/layout/activity_infopersonal.xml" value="0.25" />[m
         <entry key="..\:/Users/franc/GymBud/app/src/main/res/layout/activity_intro.xml" value="0.3009833262077811" />[m
[31m-        <entry key="..\:/Users/franc/GymBud/app/src/main/res/layout/activity_main.xml" value="0.13157894736842105" />[m
[32m+[m[32m        <entry key="..\:/Users/franc/GymBud/app/src/main/res/layout/activity_main.xml" value="0.25" />[m
         <entry key="..\:/Users/franc/GymBud/app/src/main/res/layout/activity_olvidos.xml" value="0.33" />[m
         <entry key="..\:/Users/franc/GymBud/app/src/main/res/layout/activity_registro.xml" value="0.22" />[m
         <entry key="..\:/Users/franc/GymBud/app/src/main/res/layout/fragment_detalles_sucursal.xml" value="0.3009833262077811" />[m
[1mdiff --git a/app/build.gradle b/app/build.gradle[m
[1mindex 7edb4d0..fa2740a 100644[m
[1m--- a/app/build.gradle[m
[1m+++ b/app/build.gradle[m
[36m@@ -57,6 +57,7 @@[m [mdependencies {[m
     androidTestImplementation 'androidx.test.ext:junit:1.1.5'[m
     androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'[m
 [m
[32m+[m[32m    implementation 'io.alterac.blurkit:blurkit:1.1.0'[m
 [m
 [m
 }[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/gymbud/infopersonal.java b/app/src/main/java/com/example/gymbud/infopersonal.java[m
[1mindex 67b11b6..f28d5a2 100644[m
[1m--- a/app/src/main/java/com/example/gymbud/infopersonal.java[m
[1m+++ b/app/src/main/java/com/example/gymbud/infopersonal.java[m
[36m@@ -3,6 +3,7 @@[m [mpackage com.example.gymbud;[m
 import android.database.sqlite.SQLiteDatabase;[m
 import android.os.Bundle;[m
 [m
[32m+[m[32mimport androidx.cardview.widget.CardView;[m
 import androidx.core.widget.NestedScrollView;[m
 import androidx.fragment.app.Fragment;[m
 [m
[36m@@ -13,6 +14,8 @@[m [mimport android.view.ViewGroup;[m
 import android.widget.ScrollView;[m
 [m
 import eightbitlab.com.blurview.BlurView;[m
[32m+[m[32mimport io.alterac.blurkit.BlurKit;[m
[32m+[m[32mimport io.alterac.blurkit.BlurLayout;[m
 [m
 import android.widget.Button;[m
 import android.widget.EditText;[m
[36m@@ -43,6 +46,7 @@[m [mpublic class infopersonal extends Fragment {[m
     private String mParam2;[m
     Button botonson;[m
     EditText editadon;[m
[32m+[m[32m    CardView cardRachaDeAsistencias;[m
 [m
     public infopersonal() {[m
         // Required empty public constructor[m
[36m@@ -67,6 +71,7 @@[m [mpublic class infopersonal extends Fragment {[m
     }[m
 [m
     NestedScrollView scroll;[m
[32m+[m[32m    BlurLayout blur;[m
 [m
     @Override[m
     public void onCreate(Bundle savedInstanceState) {[m
[36m@@ -76,6 +81,18 @@[m [mpublic class infopersonal extends Fragment {[m
             mParam2 = getArguments().getString(ARG_PARAM2);[m
 [m
             scroll = scroll.findViewById(R.id.scroll);[m
[32m+[m[32m            cardRachaDeAsistencias = cardRachaDeAsistencias.findViewById(R.id.cardRachaDeAsistencias);[m
[32m+[m[32m//            blur = blur.findViewById(R.id.blur);[m
[32m+[m
[32m+[m
[32m+[m
[32m+[m[32m            cardRachaDeAsistencias.setOnClickListener(new View.OnClickListener() {[m
[32m+[m[32m                @Override[m
[32m+[m[32m                public void onClick(View view) {[m
[32m+[m
[32m+[m[32m                }[m
[32m+[m[32m            });[m
[32m+[m
         }[m
 [m
 [m
[1mdiff --git a/app/src/main/res/layout/fragment_infopersonal.xml b/app/src/main/res/layout/fragment_infopersonal.xml[m
[1mindex 9d31df1..2c9bcfb 100644[m
[1m--- a/app/src/main/res/layout/fragment_infopersonal.xml[m
[1m+++ b/app/src/main/res/layout/fragment_infopersonal.xml[m
[36m@@ -1,4 +1,5 @@[m
 <?xml version="1.0" encoding="utf-8"?>[m
[32m+[m
 <androidx.constraintlayout.widget.ConstraintLayout[m
     xmlns:android="http://schemas.android.com/apk/res/android"[m
     xmlns:app="http://schemas.android.com/apk/res-auto"[m
[36m@@ -12,308 +13,310 @@[m
     style="@style/parent.contentLayout">[m
 [m
 [m
[31m-    <androidx.core.widget.NestedScrollView[m
[31m-        android:id="@+id/scroll"[m
[31m-        android:layout_width="match_parent"[m
[31m-        android:layout_height="match_parent">[m
 [m
[31m-        <LinearLayout[m
[31m-            android:layout_width="match_parent"[m
[31m-            android:layout_height="wrap_content"[m
[31m-            android:orientation="vertical">[m
 [m
[31m-            <TextView[m
[31m-                android:text="Informacion Personal"[m
[31m-                android:textSize="30sp"[m
[31m-                android:textStyle="bold"[m
[31m-                android:textColor="@color/bottomNavigationTintColor"[m
[31m-                android:paddingTop="5dp"[m
[31m-                android:layout_height="50dp"[m
[31m-                android:fontFamily="@font/lato"[m
[31m-                android:layout_width="wrap_content"/>[m
[32m+[m[32m        <androidx.core.widget.NestedScrollView[m
 [m
[32m+[m[32m            android:id="@+id/scroll"[m
[32m+[m[32m            android:layout_width="match_parent"[m
[32m+[m[32m            android:layout_height="match_parent">[m
 [m
[31m-            <androidx.cardview.widget.CardView[m
[32m+[m
[32m+[m[32m            <LinearLayout[m
                 android:layout_width="match_parent"[m
                 android:layout_height="wrap_content"[m
[31m-                app:cardCornerRadius="15dp"[m
[31m-                app:cardPreventCornerOverlap="true"[m
[31m-                android:layout_marginTop="10dp">[m
[32m+[m[32m                android:orientation="vertical">[m
 [m
[31m-                <net.colindodd.gradientlayout.GradientRelativeLayout[m
[31m-                    android:layout_width="match_parent"[m
[31m-                    android:layout_height="match_parent"[m
[31m-                    android:minHeight="100dp"[m
[31m-                    gl:start_color="@color/colorIdentidad5"[m
[31m-                    gl:end_color="@color/colorIdentidad6"[m
[31m-                    gl:orientation="LEFT_RIGHT"[m
[32m+[m[32m                <TextView[m
[32m+[m[32m                    android:text="Informacion Personal"[m
[32m+[m[32m                    android:textSize="30sp"[m
[32m+[m[32m                    android:textStyle="bold"[m
[32m+[m[32m                    android:textColor="@color/bottomNavigationTintColor"[m
[32m+[m[32m                    android:paddingTop="5dp"[m
[32m+[m[32m                    android:layout_height="50dp"[m
[32m+[m[32m                    android:fontFamily="@font/lato"[m
[32m+[m[32m                    android:layout_width="wrap_content"/>[m
 [m
 [m
[31m-                    android:padding="10dp">[m
[32m+[m[32m                <androidx.cardview.widget.CardView[m
[32m+[m[32m                    android:id="@+id/cardRachaDeAsistencias"[m
[32m+[m[32m                    android:layout_width="match_parent"[m
[32m+[m[32m                    android:layout_height="wrap_content"[m
[32m+[m[32m                    app:cardCornerRadius="15dp"[m
[32m+[m[32m                    app:cardPreventCornerOverlap="true"[m
[32m+[m[32m                    android:layout_marginTop="10dp">[m
 [m
[32m+[m[32m                    <net.colindodd.gradientlayout.GradientRelativeLayout[m
[32m+[m[32m                        android:layout_width="match_parent"[m
[32m+[m[32m                        android:layout_height="match_parent"[m
[32m+[m[32m                        android:minHeight="100dp"[m
[32m+[m[32m                        gl:start_color="@color/colorIdentidad5"[m
[32m+[m[32m                        gl:end_color="@color/colorIdentidad6"[m
[32m+[m[32m                        gl:orientation="LEFT_RIGHT"[m
 [m
[31m-                    <TextView[m
[31m-                        android:id="@+id/textViewSub1Title"[m
[31m-                        style="@style/viewParent.headerText.HomeCardTitle"[m
[31m-                        android:paddingBottom="10dp"[m
[31m-                        android:text="Racha de asistencias" />[m
 [m
[31m-                    <TextView[m
[31m-                        android:id="@+id/Texto1Dias"[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/textViewSub1Title"[m
[31m-                        android:paddingEnd="5dp"[m
[31m-                        android:text="Llevas un racha de" />[m
[32m+[m[32m                        android:padding="10dp">[m
 [m
[31m-                    <TextView[m
[31m-                        android:id="@+id/diasn"[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/textViewSub1Title"[m
[31m-                        android:layout_toEndOf="@+id/Texto1Dias"[m
[31m-                        android:text="N"[m
[31m-                        android:textStyle="bold" />[m
 [m
[31m-                    <TextView[m
[31m-                        android:id="@+id/Texto2Dias"[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/textViewSub1Title"[m
[31m-                        android:layout_toEndOf="@+id/diasn"[m
[31m-                        android:paddingStart="5dp"[m
[31m-                        android:text="dias consecutivos!"[m
[31m-                        tools:ignore="RtlSymmetry" />[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/textViewSub1Title"[m
[32m+[m[32m                            style="@style/viewParent.headerText.HomeCardTitle"[m
[32m+[m[32m                            android:paddingBottom="10dp"[m
[32m+[m[32m                            android:text="Racha de asistencias" />[m
 [m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/Texto1Dias"[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/textViewSub1Title"[m
[32m+[m[32m                            android:paddingEnd="5dp"[m
[32m+[m[32m                            android:text="Llevas un racha de" />[m
 [m
[31m-                    <ImageView[m
[31m-                        style="@style/homeCardImage"[m
[31m-                        android:layout_toEndOf="@+id/Texto2Dias"[m
[31m-                        android:maxHeight="90dp"[m
[31m-                        android:paddingStart="20dp"[m
[31m-                        android:src="@drawable/otisparado"[m
[31m-                        tools:ignore="RtlSymmetry" />[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/diasn"[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/textViewSub1Title"[m
[32m+[m[32m                            android:layout_toEndOf="@+id/Texto1Dias"[m
[32m+[m[32m                            android:text="N"[m
[32m+[m[32m                            android:textStyle="bold" />[m
 [m
[31m-                </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[31m-            </androidx.cardview.widget.CardView>[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/Texto2Dias"[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/textViewSub1Title"[m
[32m+[m[32m                            android:layout_toEndOf="@+id/diasn"[m
[32m+[m[32m                            android:paddingStart="5dp"[m
[32m+[m[32m                            android:text="dias consecutivos!"[m
[32m+[m[32m                            tools:ignore="RtlSymmetry" />[m
 [m
 [m
[32m+[m[32m                        <ImageView[m
[32m+[m[32m                            style="@style/homeCardImage"[m
[32m+[m[32m                            android:layout_toEndOf="@+id/Texto2Dias"[m
[32m+[m[32m                            android:maxHeight="90dp"[m
[32m+[m[32m                            android:paddingStart="20dp"[m
[32m+[m[32m                            android:src="@drawable/otisparado"[m
[32m+[m[32m                            tools:ignore="RtlSymmetry" />[m
 [m
[32m+[m[32m                    </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[32m+[m[32m                </androidx.cardview.widget.CardView>[m
 [m
[31m-            <androidx.cardview.widget.CardView[m
[31m-                android:layout_width="match_parent"[m
[31m-                android:layout_height="wrap_content"[m
[31m-                app:cardCornerRadius="15dp"[m
[31m-                app:cardPreventCornerOverlap="true"[m
[31m-                android:layout_marginTop="10dp">[m
 [m
 [m
 [m
[31m-                <net.colindodd.gradientlayout.GradientRelativeLayout[m
[32m+[m[32m                <androidx.cardview.widget.CardView[m
                     android:layout_width="match_parent"[m
[31m-                    android:layout_height="match_parent"[m
[31m-                    android:minHeight="100dp"[m
[31m-                    gl:start_color="@color/colorIdentidad5"[m
[31m-                    gl:end_color="@color/colorIdentidad6"[m
[31m-                    gl:orientation="LEFT_RIGHT"[m
[31m-                    android:padding="10dp">[m
[31m-[m
[32m+[m[32m                    android:layout_height="wrap_content"[m
[32m+[m[32m                    app:cardCornerRadius="15dp"[m
[32m+[m[32m                    app:cardPreventCornerOverlap="true"[m
[32m+[m[32m                    android:layout_marginTop="10dp">[m
 [m
[31m-                    <TextView[m
[31m-                        android:id="@+id/textViewSub2Title"[m
[31m-                        style="@style/viewParent.headerText.HomeCardTitle"[m
[31m-                        android:text="Rutina de hoy" />[m
 [m
 [m
[31m-                    <ImageView[m
[31m-                        android:layout_width="230dp"[m
[31m-                        android:layout_height="131dp"[m
[31m-                        android:layout_below="@+id/textViewSub2Title"[m
[31m-                        android:layout_centerInParent="true"[m
[31m-                        android:src="@drawable/gblogo">[m
[31m-[m
[31m-                    </ImageView>[m
[31m-[m
[31m-                </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[31m-            </androidx.cardview.widget.CardView>[m
[31m-[m
[31m-[m
[31m-            <androidx.cardview.widget.CardView[m
[31m-                android:layout_width="match_parent"[m
[31m-                android:layout_height="wrap_content"[m
[31m-                app:cardCornerRadius="15dp"[m
[31m-                app:cardPreventCornerOverlap="true"[m
[31m-                android:layout_marginTop="10dp">[m
[31m-[m
[31m-                <net.colindodd.gradientlayout.GradientRelativeLayout[m
[31m-                    android:layout_width="match_parent"[m
[31m-                    android:layout_height="match_parent"[m
[31m-                    android:minHeight="100dp"[m
[31m-                    gl:start_color="@color/colorIdentidad5"[m
[31m-                    gl:end_color="@color/colorIdentidad6"[m
[31m-                    gl:orientation="LEFT_RIGHT"[m
[31m-                    android:padding="10dp">[m
[31m-[m
[31m-                    <TextView[m
[31m-                        android:id="@+id/textViewSub3Title"[m
[31m-                        style="@style/viewParent.headerText.HomeCardTitle"[m
[31m-                        android:layout_centerInParent="false"[m
[31m-                        android:paddingBottom="10dp"[m
[31m-                        android:text="Peso" />[m
[31m-[m
[31m-                    <TextView[m
[31m-                        android:id="@+id/Texto1Peso"[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/textViewSub3Title"[m
[31m-                        android:text="Clickea la tarjeta para personalizar tus valores!" />[m
[31m-[m
[31m-[m
[31m-                    <ProgressBar[m
[31m-                        style="@style/Widget.AppCompat.ProgressBar.Horizontal"[m
[31m-                        android:id="@+id/progress"[m
[32m+[m[32m                    <net.colindodd.gradientlayout.GradientRelativeLayout[m
                         android:layout_width="match_parent"[m
[31m-                        android:layout_height="wrap_content"[m
[31m-                        android:layout_below="@id/Texto1Peso"[m
[31m-                        android:layout_centerHorizontal="true"[m
[31m-                        android:layout_marginStart="50dp">[m
[32m+[m[32m                        android:layout_height="match_parent"[m
[32m+[m[32m                        android:minHeight="100dp"[m
[32m+[m[32m                        gl:start_color="@color/colorIdentidad5"[m
[32m+[m[32m                        gl:end_color="@color/colorIdentidad6"[m
[32m+[m[32m                        gl:orientation="LEFT_RIGHT"[m
[32m+[m[32m                        android:padding="10dp">[m
 [m
 [m
[31m-                    </ProgressBar>[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/textViewSub2Title"[m
[32m+[m[32m                            style="@style/viewParent.headerText.HomeCardTitle"[m
[32m+[m[32m                            android:text="Rutina de hoy" />[m
 [m
[31m-                    <TextView[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/progress"[m
[31m-                        android:layout_centerHorizontal="true"[m
[31m-                        android:text="Peso actual: X | Meta de peso: N" />[m
 [m
[31m-                </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[31m-            </androidx.cardview.widget.CardView>[m
[32m+[m[32m                        <ImageView[m
[32m+[m[32m                            android:layout_width="230dp"[m
[32m+[m[32m                            android:layout_height="131dp"[m
[32m+[m[32m                            android:layout_below="@+id/textViewSub2Title"[m
[32m+[m[32m                            android:layout_centerInParent="true"[m
[32m+[m[32m                            android:src="@drawable/gblogo">[m
 [m
[32m+[m[32m                        </ImageView>[m
 [m
[31m-            <androidx.cardview.widget.CardView[m
[31m-                android:layout_width="match_parent"[m
[31m-                android:layout_height="wrap_content"[m
[31m-                app:cardCornerRadius="15dp"[m
[31m-                app:cardPreventCornerOverlap="true"[m
[31m-                android:layout_marginTop="10dp">[m
[31m-[m
[31m-                <net.colindodd.gradientlayout.GradientRelativeLayout[m
[31m-                    android:layout_width="match_parent"[m
[31m-                    android:layout_height="match_parent"[m
[31m-                    android:minHeight="100dp"[m
[31m-                    gl:start_color="@color/colorIdentidad5"[m
[31m-                    gl:end_color="@color/colorIdentidad6"[m
[31m-                    gl:orientation="LEFT_RIGHT"[m
[31m-                    android:padding="10dp">[m
[31m-[m
[31m-                    <TextView[m
[31m-                        android:id="@+id/textViewSub4Title"[m
[31m-                        style="@style/viewParent.headerText.HomeCardTitle"[m
[31m-                        android:layout_centerInParent="false"[m
[31m-                        android:paddingBottom="10dp"[m
[31m-                        android:text="IMC" />[m
[31m-[m
[31m-                    <TextView[m
[31m-                        android:id="@+id/dias"[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/textViewSub4Title"[m
[31m-                        android:text="Clickea la tarjeta para personalizar tus valores!" />[m
[31m-[m
[31m-[m
[31m-                    <ProgressBar[m
[31m-                        style="@style/Widget.AppCompat.ProgressBar.Horizontal"[m
[31m-                        android:id="@+id/progress2"[m
[31m-                        android:layout_width="match_parent"[m
[31m-                        android:layout_height="wrap_content"[m
[31m-                        android:layout_below="@id/dias"[m
[31m-                        android:layout_centerHorizontal="true"[m
[31m-                        android:layout_marginStart="50dp">[m
[31m-[m
[32m+[m[32m                    </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[32m+[m[32m                </androidx.cardview.widget.CardView>[m
 [m
[31m-                    </ProgressBar>[m
[31m-[m
[31m-                    <TextView[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/progress2"[m
[31m-                        android:layout_centerHorizontal="true"[m
[31m-                        android:text="IMC: X | IMC ideal: N" />[m
[31m-[m
[31m-                </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[31m-            </androidx.cardview.widget.CardView>[m
[31m-[m
[31m-            <androidx.cardview.widget.CardView[m
[31m-                android:layout_width="match_parent"[m
[31m-                android:layout_height="wrap_content"[m
[31m-                app:cardCornerRadius="15dp"[m
[31m-                app:cardPreventCornerOverlap="true"[m
[31m-                android:layout_marginTop="10dp">[m
 [m
[31m-                <net.colindodd.gradientlayout.GradientRelativeLayout[m
[32m+[m[32m                <androidx.cardview.widget.CardView[m
                     android:layout_width="match_parent"[m
[31m-                    android:layout_height="match_parent"[m
[31m-                    android:minHeight="100dp"[m
[31m-                    gl:start_color="@color/colorIdentidad5"[m
[31m-                    gl:end_color="@color/colorIdentidad6"[m
[31m-                    gl:orientation="LEFT_RIGHT"[m
[31m-                    android:padding="10dp">[m
[31m-[m
[31m-                    <TextView[m
[31m-                        android:id="@+id/textViewSub5Title"[m
[31m-                        style="@style/viewParent.headerText.HomeCardTitle"[m
[31m-                        android:paddingBottom="10dp"[m
[31m-                        android:text="Tasa de grasa" />[m
[31m-[m
[31m-                    <TextView[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/textViewSub5Title"[m
[31m-                        android:text="Tu tasa de grasa es de el X%" />[m
[31m-[m
[31m-                    <ImageView[m
[31m-                        style="@style/homeCardImage"[m
[31m-                        android:maxHeight="90dp"[m
[31m-                        android:src="@drawable/gblogo" />[m
[31m-[m
[31m-                </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[31m-            </androidx.cardview.widget.CardView>[m
[31m-[m
[31m-            <androidx.cardview.widget.CardView[m
[31m-                android:layout_width="match_parent"[m
[31m-                android:layout_height="wrap_content"[m
[31m-                app:cardCornerRadius="15dp"[m
[31m-                app:cardPreventCornerOverlap="true"[m
[31m-                android:layout_marginTop="10dp">[m
[32m+[m[32m                    android:layout_height="wrap_content"[m
[32m+[m[32m                    app:cardCornerRadius="15dp"[m
[32m+[m[32m                    app:cardPreventCornerOverlap="true"[m
[32m+[m[32m                    android:layout_marginTop="10dp">[m
 [m
[31m-                <net.colindodd.gradientlayout.GradientRelativeLayout[m
[32m+[m[32m                    <net.colindodd.gradientlayout.GradientRelativeLayout[m
[32m+[m[32m                        android:layout_width="match_parent"[m
[32m+[m[32m                        android:layout_height="match_parent"[m
[32m+[m[32m                        android:minHeight="100dp"[m
[32m+[m[32m                        gl:start_color="@color/colorIdentidad5"[m
[32m+[m[32m                        gl:end_color="@color/colorIdentidad6"[m
[32m+[m[32m                        gl:orientation="LEFT_RIGHT"[m
[32m+[m[32m                        android:padding="10dp">[m
[32m+[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/textViewSub3Title"[m
[32m+[m[32m                            style="@style/viewParent.headerText.HomeCardTitle"[m
[32m+[m[32m                            android:layout_centerInParent="false"[m
[32m+[m[32m                            android:paddingBottom="10dp"[m
[32m+[m[32m                            android:text="Peso" />[m
[32m+[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/Texto1Peso"[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/textViewSub3Title"[m
[32m+[m[32m                            android:text="Clickea la tarjeta para personalizar tus valores!" />[m
[32m+[m
[32m+[m
[32m+[m[32m                        <ProgressBar[m
[32m+[m[32m                            style="@style/Widget.AppCompat.ProgressBar.Horizontal"[m
[32m+[m[32m                            android:id="@+id/progress"[m
[32m+[m[32m                            android:layout_width="match_parent"[m
[32m+[m[32m                            android:layout_height="wrap_content"[m
[32m+[m[32m                            android:layout_below="@id/Texto1Peso"[m
[32m+[m[32m                            android:layout_centerHorizontal="true"[m
[32m+[m[32m                            android:layout_marginStart="50dp">[m
[32m+[m
[32m+[m
[32m+[m[32m                        </ProgressBar>[m
[32m+[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/progress"[m
[32m+[m[32m                            android:layout_centerHorizontal="true"[m
[32m+[m[32m                            android:text="Peso actual: X | Meta de peso: N" />[m
[32m+[m
[32m+[m[32m                    </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[32m+[m[32m                </androidx.cardview.widget.CardView>[m
[32m+[m
[32m+[m
[32m+[m[32m                <androidx.cardview.widget.CardView[m
                     android:layout_width="match_parent"[m
[31m-                    android:layout_height="match_parent"[m
[31m-                    android:minHeight="100dp"[m
[31m-                    gl:start_color="@color/colorIdentidad5"[m
[31m-                    gl:end_color="@color/colorIdentidad6"[m
[31m-                    gl:orientation="LEFT_RIGHT"[m
[31m-                    android:padding="10dp">[m
[32m+[m[32m                    android:layout_height="wrap_content"[m
[32m+[m[32m                    app:cardCornerRadius="15dp"[m
[32m+[m[32m                    app:cardPreventCornerOverlap="true"[m
[32m+[m[32m                    android:layout_marginTop="10dp">[m
 [m
[31m-                    <TextView[m
[31m-                        android:id="@+id/textViewSub6Title"[m
[31m-                        style="@style/viewParent.headerText.HomeCardTitle"[m
[31m-                        android:paddingBottom="10dp"[m
[31m-                        android:text="Frase del dia" />[m
[32m+[m[32m                    <net.colindodd.gradientlayout.GradientRelativeLayout[m
[32m+[m[32m                        android:layout_width="match_parent"[m
[32m+[m[32m                        android:layout_height="match_parent"[m
[32m+[m[32m                        android:minHeight="100dp"[m
[32m+[m[32m                        gl:start_color="@color/colorIdentidad5"[m
[32m+[m[32m                        gl:end_color="@color/colorIdentidad6"[m
[32m+[m[32m                        gl:orientation="LEFT_RIGHT"[m
[32m+[m[32m                        android:padding="10dp">[m
[32m+[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/textViewSub4Title"[m
[32m+[m[32m                            style="@style/viewParent.headerText.HomeCardTitle"[m
[32m+[m[32m                            android:layout_centerInParent="false"[m
[32m+[m[32m                            android:paddingBottom="10dp"[m
[32m+[m[32m                            android:text="IMC" />[m
[32m+[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/dias"[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/textViewSub4Title"[m
[32m+[m[32m                            android:text="Clickea la tarjeta para personalizar tus valores!" />[m
[32m+[m
[32m+[m
[32m+[m[32m                        <ProgressBar[m
[32m+[m[32m                            style="@style/Widget.AppCompat.ProgressBar.Horizontal"[m
[32m+[m[32m                            android:id="@+id/progress2"[m
[32m+[m[32m                            android:layout_width="match_parent"[m
[32m+[m[32m                            android:layout_height="wrap_content"[m
[32m+[m[32m                            android:layout_below="@id/dias"[m
[32m+[m[32m                            android:layout_centerHorizontal="true"[m
[32m+[m[32m                            android:layout_marginStart="50dp">[m
[32m+[m
[32m+[m
[32m+[m[32m                        </ProgressBar>[m
[32m+[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/progress2"[m
[32m+[m[32m                            android:layout_centerHorizontal="true"[m
[32m+[m[32m                            android:text="IMC: X | IMC ideal: N" />[m
[32m+[m
[32m+[m[32m                    </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[32m+[m[32m                </androidx.cardview.widget.CardView>[m
[32m+[m
[32m+[m[32m                <androidx.cardview.widget.CardView[m
[32m+[m[32m                    android:layout_width="match_parent"[m
[32m+[m[32m                    android:layout_height="wrap_content"[m
[32m+[m[32m                    app:cardCornerRadius="15dp"[m
[32m+[m[32m                    app:cardPreventCornerOverlap="true"[m
[32m+[m[32m                    android:layout_marginTop="10dp">[m
 [m
[31m-                    <TextView[m
[31m-                        android:id="@+id/frase"[m
[31m-                        android:text="El dolor que sientes hoy es la fuerza que sentiras mañana\n\n-Natanael Cano 2024"[m
[31m-                        style="@style/viewParent.headerText.homeCardContent"[m
[31m-                        android:layout_below="@id/textViewSub6Title"/>[m
[32m+[m[32m                    <net.colindodd.gradientlayout.GradientRelativeLayout[m
[32m+[m[32m                        android:layout_width="match_parent"[m
[32m+[m[32m                        android:layout_height="match_parent"[m
[32m+[m[32m                        android:minHeight="100dp"[m
[32m+[m[32m                        gl:start_color="@color/colorIdentidad5"[m
[32m+[m[32m                        gl:end_color="@color/colorIdentidad6"[m
[32m+[m[32m                        gl:orientation="LEFT_RIGHT"[m
[32m+[m[32m                        android:padding="10dp">[m
[32m+[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/textViewSub5Title"[m
[32m+[m[32m                            style="@style/viewParent.headerText.HomeCardTitle"[m
[32m+[m[32m                            android:paddingBottom="10dp"[m
[32m+[m[32m                            android:text="Tasa de grasa" />[m
[32m+[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/textViewSub5Title"[m
[32m+[m[32m                            android:text="Tu tasa de grasa es de el X%" />[m
[32m+[m
[32m+[m[32m                        <ImageView[m
[32m+[m[32m                            style="@style/homeCardImage"[m
[32m+[m[32m                            android:maxHeight="90dp"[m
[32m+[m[32m                            android:src="@drawable/gblogo" />[m
[32m+[m
[32m+[m[32m                    </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[32m+[m[32m                </androidx.cardview.widget.CardView>[m
[32m+[m
[32m+[m[32m                <androidx.cardview.widget.CardView[m
[32m+[m[32m                    android:layout_width="match_parent"[m
[32m+[m[32m                    android:layout_height="wrap_content"[m
[32m+[m[32m                    app:cardCornerRadius="15dp"[m
[32m+[m[32m                    app:cardPreventCornerOverlap="true"[m
[32m+[m[32m                    android:layout_marginTop="10dp">[m
 [m
[32m+[m[32m                    <net.colindodd.gradientlayout.GradientRelativeLayout[m
[32m+[m[32m                        android:layout_width="match_parent"[m
[32m+[m[32m                        android:layout_height="match_parent"[m
[32m+[m[32m                        android:minHeight="100dp"[m
[32m+[m[32m                        gl:start_color="@color/colorIdentidad5"[m
[32m+[m[32m                        gl:end_color="@color/colorIdentidad6"[m
[32m+[m[32m                        gl:orientation="LEFT_RIGHT"[m
[32m+[m[32m                        android:padding="10dp">[m
 [m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/textViewSub6Title"[m
[32m+[m[32m                            style="@style/viewParent.headerText.HomeCardTitle"[m
[32m+[m[32m                            android:paddingBottom="10dp"[m
[32m+[m[32m                            android:text="Frase del dia" />[m
 [m
[31m-                </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[31m-            </androidx.cardview.widget.CardView>[m
[32m+[m[32m                        <TextView[m
[32m+[m[32m                            android:id="@+id/frase"[m
[32m+[m[32m                            android:text="El dolor que sientes hoy es la fuerza que sentiras mañana\n\n-Natanael Cano 2024"[m
[32m+[m[32m                            style="@style/viewParent.headerText.homeCardContent"[m
[32m+[m[32m                            android:layout_below="@id/textViewSub6Title"/>[m
 [m
 [m
 [m
[32m+[m[32m                    </net.colindodd.gradientlayout.GradientRelativeLayout>[m
[32m+[m[32m                </androidx.cardview.widget.CardView>[m
 [m
[31m-        </LinearLayout>[m
[31m-    </androidx.core.widget.NestedScrollView>[m
 [m
 [m
 [m
[32m+[m[32m            </LinearLayout>[m
[32m+[m[32m        </androidx.core.widget.NestedScrollView>[m
 [m
 </androidx.constraintlayout.widget.ConstraintLayout>[m
 [m
