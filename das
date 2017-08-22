[1mdiff --git a/app/src/main/java/mantoo/dbcent/mantoo/Activites/MainActivity.java b/app/src/main/java/mantoo/dbcent/mantoo/Activites/MainActivity.java[m
[1mindex 52b20d8..fe057dd 100644[m
[1m--- a/app/src/main/java/mantoo/dbcent/mantoo/Activites/MainActivity.java[m
[1m+++ b/app/src/main/java/mantoo/dbcent/mantoo/Activites/MainActivity.java[m
[36m@@ -209,6 +209,7 @@[m [mpublic class MainActivity extends AppCompatActivity implements NavigationView.On[m
         if (fragment != null) {[m
             FragmentTransaction mfragmentTransaction = getSupportFragmentManager().beginTransaction();[m
             mfragmentTransaction.replace(R.id.content_main, fragment);[m
[32m+[m[32m            mfragmentTransaction.addToBackStack(null);[m
             mfragmentTransaction.commit();[m
         }[m
     }[m
