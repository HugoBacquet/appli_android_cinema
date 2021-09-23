package com.example.applicine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.applicine.R;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextFilmTitle, editTextDate,editTextHour,editTextGradeScenario,editTextGradeRealisation,editTextGradeMusic,editTextCritique;
    String FilmNameHolder, DateHolder,HoursHolder,ScenarioHolder,RealisationHolder,MusicHolder,CritiqueHolder, SQLiteDataBaseQueryHolder;
    Button EnterData;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterData = (Button)findViewById(R.id.button);
        editTextFilmTitle = (EditText)findViewById(R.id.editTextTextPersonName);
        editTextDate = (EditText)findViewById(R.id.editTextDate);
        editTextHour = (EditText)findViewById(R.id.editTextTime);
        editTextGradeScenario = (EditText)findViewById(R.id.editTextTextPersonName3);
        editTextGradeRealisation = (EditText)findViewById(R.id.editTextTextPersonName4);
        editTextGradeMusic = (EditText)findViewById(R.id.editTextTextPersonName2);
        editTextCritique = (EditText)findViewById(R.id.editTextTextPersonName5);

        EnterData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
                EmptyEditTextAfterDataInsert();

            }
        });


    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS AndroidJSonTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, film_title VARCHAR, date VARCHAR, hour VARCHAR, scenario_grade VARCHAR, realisation_grade VARCHAR, music_grade VARCHAR, critique VARCHAR);");

    }

    public void CheckEditTextStatus(){

        FilmNameHolder = editTextFilmTitle.getText().toString();
        DateHolder = editTextDate.getText().toString() ;
        HoursHolder = editTextHour.getText().toString();
        ScenarioHolder = editTextGradeScenario.getText().toString() ;
        RealisationHolder = editTextGradeRealisation.getText().toString();
        MusicHolder = editTextGradeMusic.getText().toString();
        CritiqueHolder = editTextCritique.getText().toString();

        if(TextUtils.isEmpty(FilmNameHolder) || TextUtils.isEmpty(DateHolder) || TextUtils.isEmpty(HoursHolder) || TextUtils.isEmpty(ScenarioHolder) || TextUtils.isEmpty(RealisationHolder) || TextUtils.isEmpty(MusicHolder) || TextUtils.isEmpty(CritiqueHolder)){

            EditTextEmptyHold = false ;

        }
        else {

            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {

            SQLiteDataBaseQueryHolder = "INSERT INTO AndroidJSonTable (film_title, date , hour , scenario_grade , realisation_grade , music_grade , critique ) VALUES('"+FilmNameHolder+"', '"+DateHolder+"', '"+HoursHolder+"','"+ScenarioHolder+"','"+RealisationHolder+"','"+MusicHolder+"','"+CritiqueHolder+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            Toast.makeText(MainActivity.this,"Avis enregistré", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(MainActivity.this,"Veuillez remplir tous les champs.", Toast.LENGTH_LONG).show();

        }

    }

    public void EmptyEditTextAfterDataInsert(){

        editTextFilmTitle.getText().clear();
        editTextDate.getText().clear();
        editTextHour.getText().clear();
        editTextGradeScenario.getText().clear();
        editTextGradeRealisation.getText().clear();
        editTextGradeMusic.getText().clear();
        editTextCritique.getText().clear();

    }

}