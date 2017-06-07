package com.gulbalasalamov.sharedpreferencesdemo2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {

    TextView tv_Ayarlar, tv_Ses, tv_Seviye;
    SeekBar sb_Ses, sb_Parlaklik;
    RadioGroup rg;
    RadioButton rb_Kolay, rb_Orta, rb_Zor;
    Button btn;

    protected void onStart() {
        super.onStart();
        ayarlariYukle();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        tv_Ayarlar = (TextView) findViewById(R.id.tv_ayarlar);
        tv_Ses = (TextView) findViewById(R.id.tv_ses);
        tv_Seviye = (TextView) findViewById(R.id.tv_seviye);

        sb_Ses = (SeekBar) findViewById(R.id.seekbar_ses);
        sb_Parlaklik = (SeekBar) findViewById(R.id.seekbar_parlaklik);

        rg = (RadioGroup) findViewById(R.id.radiogroup);
        rb_Kolay = (RadioButton) findViewById(R.id.rb_kolay);
        rb_Orta = (RadioButton) findViewById(R.id.rb_orta);
        rb_Zor = (RadioButton) findViewById(R.id.rb_zor);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayarlariKaydet(view);
            }
        });
    }

    private void ayarlariYukle() {
        SharedPreferences sp = getSharedPreferences("oyun_ayarlari", MODE_PRIVATE);
        int parlaklik = sp.getInt("parlaklik", 50);
        int ses = sp.getInt("ses", 90);
        int checkedRadioButtonId = sp.getInt("checkedRadioButtonId", R.id.rb_kolay);
        sb_Parlaklik.setProgress(parlaklik);
        sb_Ses.setProgress(ses);
        rg.check(checkedRadioButtonId);
    }

    private void ayarlariKaydet(View view) {
        SharedPreferences sp = getSharedPreferences("oyun_ayarlari", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("parlaklik", this.sb_Parlaklik.getProgress());
        editor.putInt("ses", this.sb_Ses.getProgress());
        int checkedRadioButtonId = rg.getCheckedRadioButtonId();
        editor.putInt("checkedRadioButtonId", checkedRadioButtonId);
        editor.commit();
        Toast.makeText(this, "Ayarlar Kaydedildi! ", Toast.LENGTH_SHORT).show();
    }


}
