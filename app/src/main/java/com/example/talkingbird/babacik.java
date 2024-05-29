
package com.example.talkingbird;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class babacik extends AppCompatActivity {
    int bugunlukEgitim;
    int baslangicTakvimiint;
    int bugununTarihi;
    String gecenGunString;
    int gecenGun;
    TextView gecenGunText;

    //---------------------
    ImageView check1;
    ImageView check2;
    ImageView check3;
    ImageView check4;
    ImageView emptycheck1;
    ImageView emptycheck2;
    ImageView emptycheck3;
    ImageView emptycheck4;


    //-----------------
    int playCount = 0;
    int playCount2 = 0;
    int playCount3 = 0;
    int playCount4 = 0;

    CountDownTimer countDownTimer;
    CountDownTimer countDownTimer2;
    CountDownTimer countDownTimer3;
    CountDownTimer countDownTimer4;

    long kalanSureInMillis4 = 1092000; // 1092 saniye
    long kalanSureInMillis2 = 728000; // 728 saniye
    long kalanSureInMillis3 = 910000; // 910 saniye
    long kalanSureInMillis = 546000; // 546 saniye

    //-------------
    Button egitimeBasla;
    Button egitimiBitir;
    Button play1;
    Button play2;
    Button play3;
    Button play4;
    Button pause1;
    Button pause2;
    Button pause3;
    Button pause4;

    TextView baslangicTarihiText;
    TextView kalanGun;
    TextView kalansurep1;
    TextView kalansurep2;
    TextView kalansurep3;
    TextView kalansurep4;

    MediaPlayer babacikvoice;
    int kalanGun2;
    int egitimebaslandi = 0;
    boolean isPlaying = false;
    Calendar baslangicTakvimi;


    //------------
    Calendar takvim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babacik);
        gecenGunText = findViewById(R.id.gecenGun);
        takvim = Calendar.getInstance();
        bugununTarihi = takvim.get(Calendar.DAY_OF_MONTH);

        //--------------------
        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        check4 = findViewById(R.id.check4);
        emptycheck1 = findViewById(R.id.emptycheck1);
        emptycheck2 = findViewById(R.id.emptycheck2);
        emptycheck3 = findViewById(R.id.emptycheck3);
        emptycheck4 = findViewById(R.id.emptycheck4);

        check1.setVisibility(View.INVISIBLE);
        check2.setVisibility(View.INVISIBLE);
        check3.setVisibility(View.INVISIBLE);
        check4.setVisibility(View.INVISIBLE);
        emptycheck1.setVisibility(View.VISIBLE);
        emptycheck2.setVisibility(View.VISIBLE);
        emptycheck3.setVisibility(View.VISIBLE);
        emptycheck4.setVisibility(View.VISIBLE);

        //-------------------------------

        egitimeBasla = findViewById(R.id.egitimeBasla);
        egitimiBitir = findViewById(R.id.egitimiBitir);
        baslangicTarihiText = findViewById(R.id.baslangicTarihi);
        kalanGun = findViewById(R.id.kalanGun);
        play1 = findViewById(R.id.play1);
        play2 = findViewById(R.id.play2);
        play3 = findViewById(R.id.play3);
        play4 = findViewById(R.id.play4);

        pause1 = findViewById(R.id.pause1);
        pause2 = findViewById(R.id.pause2);
        pause3 = findViewById(R.id.pause3);
        pause4 = findViewById(R.id.pause4);

        kalansurep1 = findViewById(R.id.kalansurep1);
        kalansurep2 = findViewById(R.id.kalansurep2);
        kalansurep3 = findViewById(R.id.kalansurep3);
        kalansurep4 = findViewById(R.id.kalansurep4);
        if(baslangicTakvimiint!=bugununTarihi){
          bugunlukEgitim =0;
          
        }
        pause1.setEnabled(false);
        pause2.setEnabled(false);
        pause3.setEnabled(false);
        pause4.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);

        egitimiBitir.setEnabled(false);

    }

    public void play1(View view) {
        if (egitimebaslandi == 1) { // Eğitim başladı mı kontrol et
            pause1.setEnabled(true);
            play1.setEnabled(false);
            if (playCount < 3) { // 3 defa çalma sınırını kontrol et
                if (babacikvoice != null && !babacikvoice.isPlaying()) { // Ses çalmıyorsa
                    babacikvoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            playCount++; // Sayaçı arttır
                            if (playCount < 3) { // 3'e ulaşıp ulaşmadığını kontrol et
                                babacikvoice.start(); // Ses çal
                            } else {
                                check1.setVisibility(View.VISIBLE);
                                emptycheck1.setVisibility(View.INVISIBLE);
                                play2.setEnabled(true);
                                playCount = 0; // Sayaçı sıfırla
                                play1.setEnabled(false); // Play butonunu tekrar etkinleştir
                                pause1.setEnabled(false); // Duraklat butonunu devre dışı bırak



                            }
                        }
                    });
                    babacikvoice.start(); // Ses çal
                    isPlaying = true;

                    // Geri sayımı başlat
                    countDownTimer = new CountDownTimer(kalanSureInMillis, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            kalanSureInMillis = millisUntilFinished;
                            updateKalanSureText();
                        }

                        @Override
                        public void onFinish() {
                            // Geri sayım tamamlandığında yapılacak işlemler buraya gelebilir
                        }
                    }.start();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Ses dosyası 5 kez çalındı.", Toast.LENGTH_SHORT).show();
                playCount = 0; // Sayaçı sıfırla
                play1.setEnabled(true); // Play butonunu tekrar etkinleştir
                pause1.setEnabled(false); // Duraklat butonunu devre dışı bırak
            }
        } else {
            Toast.makeText(this, "Lütfen Eğitime Başlayınız", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause1(View view) {
        play1.setEnabled(true);
        pause1.setEnabled(false);
        if (babacikvoice != null && babacikvoice.isPlaying()) {
            babacikvoice.pause(); // Medyayı duraklat
            isPlaying = false;
            // Geri sayımı durdur
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        }
    }

    public void play2(View view) {
        if (egitimebaslandi == 1) { // Eğitim başladı mı kontrol et
            pause2.setEnabled(true);
            play2.setEnabled(false);
            if (playCount2 < 4) { // 6 defa çalma sınırını kontrol et
                if (babacikvoice != null && !babacikvoice.isPlaying()) { // Ses çalmıyorsa
                    babacikvoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            playCount2++; // Sayaçı arttır
                            if (playCount2 < 4) { // 5'e ulaşıp ulaşmadığını kontrol et
                                babacikvoice.start(); // Ses çal
                            } else {
                                play3.setEnabled(true);
                                playCount2 = 0; // Sayaçı sıfırla
                                play2.setEnabled(false); // Play butonunu tekrar etkinleştir
                                pause2.setEnabled(false); // Duraklat butonunu devre dışı bırak

                                check2.setVisibility(View.VISIBLE);
                                emptycheck2.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                    babacikvoice.start(); // Ses çal
                    isPlaying = true;

                    // Bağımsız geri sayımı başlat
                    countDownTimer2 = new CountDownTimer(kalanSureInMillis2, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            kalanSureInMillis2 = millisUntilFinished;
                            updateKalanSureText2();
                        }

                        @Override
                        public void onFinish() {
                            // Geri sayım tamamlandığında yapılacak işlemler buraya gelebilir
                        }
                    }.start();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Ses dosyası 5 kez çalındı.", Toast.LENGTH_SHORT).show();
                playCount2 = 0; // Sayaçı sıfırla
                play2.setEnabled(true); // Play butonunu tekrar etkinleştir
                pause2.setEnabled(false); // Duraklat butonunu devre dışı bırak
            }
        } else {
            Toast.makeText(this, "Lütfen Eğitime Başlayınız", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause2(View view) {
        play2.setEnabled(true);
        pause2.setEnabled(false);
        if (babacikvoice != null && babacikvoice.isPlaying()) {
            babacikvoice.pause(); // Medyayı duraklat
            isPlaying = false;
            // Geri sayımı durdur
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
            }
        }
    }

    public void play3(View view) {
        if (egitimebaslandi == 1) { // Eğitim başladı mı kontrol et
            pause3.setEnabled(true);
            play3.setEnabled(false);
            if (playCount3 < 5) { // 5 defa çalma sınırını kontrol et
                if (babacikvoice != null && !babacikvoice.isPlaying()) { // Ses çalmıyorsa
                    babacikvoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            playCount3++; // Sayaçı arttır
                            if (playCount3 < 5) { // 5'e ulaşıp ulaşmadığını kontrol et
                                babacikvoice.start(); // Ses çal
                            } else {
                                play4.setEnabled(true);
                                playCount3 = 0; // Sayaçı sıfırla
                                play3.setEnabled(false); // Play butonunu tekrar etkinleştir
                                pause3.setEnabled(false); // Duraklat butonunu devre dışı bırak
                                check3.setVisibility(View.VISIBLE);
                                emptycheck3.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                    babacikvoice.start(); // Ses çal
                    isPlaying = true;

                    // Bağımsız geri sayımı başlat
                    countDownTimer3 = new CountDownTimer(kalanSureInMillis3, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            kalanSureInMillis3 = millisUntilFinished;
                            updateKalanSureText3();
                        }

                        @Override
                        public void onFinish() {
                            // Geri sayım tamamlandığında yapılacak işlemler buraya gelebilir
                        }
                    }.start();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Ses dosyası 5 kez çalındı.", Toast.LENGTH_SHORT).show();
                playCount3 = 0; // Sayaçı sıfırla
                play3.setEnabled(true); // Play butonunu tekrar etkinleştir
                pause3.setEnabled(false); // Duraklat butonunu devre dışı bırak
            }
        } else {
            Toast.makeText(this, "Lütfen Eğitime Başlayınız", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause3(View view) {
        play3.setEnabled(true);
        pause3.setEnabled(false);
        if (babacikvoice != null && babacikvoice.isPlaying()) {
            babacikvoice.pause(); // Medyayı duraklat
            isPlaying = false;
            // Geri sayımı durdur
            if (countDownTimer3 != null) {
                countDownTimer3.cancel();
            }
        }
    }

    public void play4(View view) {
        if (egitimebaslandi == 1) { // Eğitim başladı mı kontrol et
            pause4.setEnabled(true);
            play4.setEnabled(false);
            if (playCount4 < 6) { // 5 defa çalma sınırını kontrol et
                if (babacikvoice != null && !babacikvoice.isPlaying()) { // Ses çalmıyorsa
                    babacikvoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            playCount4++; // Sayaçı arttır
                            if (playCount4 < 6) { // 5'e ulaşıp ulaşmadığını kontrol et
                                babacikvoice.start(); // Ses çal
                            } else {
                                Toast.makeText(getApplicationContext(), "Ses dosyası 5 kez çalındı.", Toast.LENGTH_SHORT).show();
                                playCount4 = 0; // Sayaçı sıfırla
                                play4.setEnabled(false); // Play butonunu tekrar etkinleştir
                                pause4.setEnabled(false); // Duraklat butonunu devre dışı bırak
                                check4.setVisibility(View.VISIBLE);
                                emptycheck4.setVisibility(View.INVISIBLE);
                                play1.setEnabled(true);
                                //------------------ kalan günü azalt geçen günü arttır
                                if(bugunlukEgitim<1){
                                    gecenGun++;
                                    gecenGunText.setText("Geçen Gün : " + gecenGun);
                                    kalanGun2--;
                                    kalanGun.setText("Kalan gün : " + kalanGun2);
                                    Toast.makeText(babacik.this, "Tebrikler! Bugünlük eğitimi bitirdiniz", Toast.LENGTH_SHORT).show();
                                }

                                play2.setEnabled(false);
                                play3.setEnabled(false);
                                play4.setEnabled(false);
                                pause1.setEnabled(false);
                                pause2.setEnabled(false);
                                pause3.setEnabled(false);
                                pause4.setEnabled(false);
                                
                                bugunlukEgitim++; 


                            }
                        }
                    });
                    babacikvoice.start(); // Ses çal
                    isPlaying = true;

                    // Bağımsız geri sayımı başlat
                    countDownTimer4 = new CountDownTimer(kalanSureInMillis4, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            kalanSureInMillis4 = millisUntilFinished;
                            updateKalanSureText4();
                        }

                        @Override
                        public void onFinish() {
                            // Geri sayım tamamlandığında yapılacak işlemler buraya gelebilir
                        }
                    }.start();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Ses dosyası 5 kez çalındı.", Toast.LENGTH_SHORT).show();
                playCount4 = 0; // Sayaçı sıfırla
                play4.setEnabled(true); // Play butonunu tekrar etkinleştir
                pause4.setEnabled(false); // Duraklat butonunu devre dışı bırak
            }
        } else {
            Toast.makeText(this, "Lütfen Eğitime Başlayınız", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause4(View view) {
        play4.setEnabled(true);
        pause4.setEnabled(false);
        if (babacikvoice != null && babacikvoice.isPlaying()) {
            babacikvoice.pause(); // Medyayı duraklat
            isPlaying = false;
            // Geri sayımı durdur
            if (countDownTimer4 != null) {
                countDownTimer4.cancel();
            }
        }
    }

    private void updateKalanSureText4() {
        int dakika = (int) (kalanSureInMillis4 / 1000) / 60;
        int saniye = (int) (kalanSureInMillis4 / 1000) % 60;
        String kalanSureText = String.format(Locale.getDefault(), "%02d:%02d", dakika, saniye);
        kalansurep4.setText(kalanSureText);
    }

    private void updateKalanSureText3() {
        int dakika = (int) (kalanSureInMillis3 / 1000) / 60;
        int saniye = (int) (kalanSureInMillis3 / 1000) % 60;
        String kalanSureText = String.format(Locale.getDefault(), "%02d:%02d", dakika, saniye);
        kalansurep3.setText(kalanSureText);
    }


    private void updateKalanSureText2() {
        int dakika = (int) (kalanSureInMillis2 / 1000) / 60;
        int saniye = (int) (kalanSureInMillis2 / 1000) % 60;
        String kalanSureText = String.format(Locale.getDefault(), "%02d:%02d", dakika, saniye);
        kalansurep2.setText(kalanSureText);
    }


    private void updateKalanSureText() {
        int dakika = (int) (kalanSureInMillis / 1000) / 60;
        int saniye = (int) (kalanSureInMillis / 1000) % 60;
        String kalanSureText = String.format(Locale.getDefault(), "%02d:%02d", dakika, saniye);
        kalansurep1.setText(kalanSureText);
    }








    public void egitimeBasla(View view) {

        gecenGun = 0;
        gecenGunString = String.valueOf(gecenGun);
        gecenGunText.setText("Geçen Gün : " + gecenGunString);

        pause1.setEnabled(false);
        pause2.setEnabled(false);
        pause3.setEnabled(false);
        pause3.setEnabled(false);
        play2.setEnabled(false);
        play3.setEnabled(false);
        play4.setEnabled(false);


        //Calendar takvim = Calendar.getInstance();

        egitimebaslandi = 1;
        egitimiBitir.setEnabled(true);
        egitimeBasla.setEnabled(false);
        babacikvoice = MediaPlayer.create(this, R.raw.deneme);
        baslangicTakvimi = Calendar.getInstance();
        baslangicTakvimiint = baslangicTakvimi.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        baslangicTarihiText.setText("Başlangıç Tarihi : " + dateFormat.format(baslangicTakvimi.getTime()));
        kalanGun2 = 30;
        kalanGun.setText("Kalan Gün Sayısı : " + kalanGun2);



        kalanSureInMillis = 546000; // Süreyi 546 saniye olarak ayarla
        updateKalanSureText();
        kalansurep1.setText("Kalan Süre :");

        kalanSureInMillis2 = 728000; // Süreyi 728 saniye olarak ayarla
        updateKalanSureText2();
        kalansurep2.setText("Kalan Süre :");

        updateKalanSureText3();
        kalansurep3.setText("Kalan Süre :");
        kalanSureInMillis3 = 910000; // Süreyi 910 saniye olarak ayarla

        updateKalanSureText4();
        kalansurep4.setText("Kalan Süre :");
        kalanSureInMillis4 = 1092000; // Süreyi 1092 saniye olarak ayarla

    }

    public void egitimiBitir(View view) {

        // Geri sayımı durdur
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }

        if (countDownTimer3 != null) {
            countDownTimer3.cancel();
        }

        if (countDownTimer4 != null) {
            countDownTimer4.cancel();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Eğitimi bitirmek istiyor musunuz?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (babacikvoice != null) {
                    if (isPlaying) {
                        babacikvoice.stop();
                    }
                    babacikvoice.release();
                }

                kalansurep1.setText("Kalan süre : "); // Kalan süreyi sıfırla
                kalansurep2.setText("Kalan süre : "); // Kalan süreyi sıfırla
                kalansurep3.setText("Kalan süre : "); // Kalan süreyi sıfırla
                kalansurep4.setText("Kalan süre : "); // Kalan süreyi sıfırla
                check1.setVisibility(View.INVISIBLE);
                check2.setVisibility(View.INVISIBLE);
                check3.setVisibility(View.INVISIBLE);
                check4.setVisibility(View.INVISIBLE);
                emptycheck1.setVisibility(View.VISIBLE);
                emptycheck2.setVisibility(View.VISIBLE);
                emptycheck3.setVisibility(View.VISIBLE);
                emptycheck4.setVisibility(View.VISIBLE);

                baslangicTarihiText.setText("Başlangıç Tarihi : ");
                kalanGun.setText("Kalan Gün Sayısı : ");
                gecenGunText.setText("Geçen Gün : ");
                egitimebaslandi = 0;
                bugunlukEgitim = 0;
                egitimiBitir.setEnabled(false);
                egitimeBasla.setEnabled(true);
                play1.setEnabled(true);
                pause1.setEnabled(false);
                play2.setEnabled(false);
                pause2.setEnabled(false);
                play3.setEnabled(false);
                pause3.setEnabled(false);
                play4.setEnabled(false);
                pause4.setEnabled(false);
                isPlaying = false; // MediaPlayer'ı durdurduğumuzda bayrağı güncelle
            }
        });
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

